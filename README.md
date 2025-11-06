## Structure Project

```bash
emr/
├── adapter/                     # Layer luar (I/O)
│   ├── controller/              # REST controllers
│   └── repository/              # Adapter antara domain repo & JPA repo
│       ├── CustomerRepositoryAdapter.java
│       └── JpaCustomerRepository.java
│
├── config/                      # Konfigurasi (Spring, Flyway, dll)
│
├── domain/                      # Core domain logic
│   ├── entity/                  # Domain entities (JPA)
│   ├── repository/              # Port untuk data access
│   └── service/                 # Domain services / business logic
│
├── api/                         # Data Transfer Objects (untuk controller I/O)
│   ├── request/
│   └── response/
│
├── mapper/                      # Konversi antar layer (Entity <-> DTO)
│   └── CustomerMapper.java
│
├── exception/                   # Custom exception, global handler, dll
│
└── JasperApplication.java       # Main entry point

```

## Example Implementation

### CustomerRepository.java
```java
// domain/repository/CustomerRepository.java
public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
}
```
### JpaCustomerRepository.java
```java
// adapter/repository/JpaCustomerRepository.java
public interface JpaCustomerRepository extends JpaRepository<Customer, Long> {}
```
### CustomerRepositoryAdapter.java
```java
// adapter/repository/CustomerRepositoryAdapter.java
@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {
    private final JpaCustomerRepository jpaRepository;

    @Override
    public Customer save(Customer customer) {
        return jpaRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return jpaRepository.findById(id);
    }
}
```
### CustomerService.java
```java
// domain/service/CustomerService.java
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }
}
```

### CustomerController.java
```java
// adapter/controller/CustomerController.java
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;
    private final CustomerMapper mapper;

    @PostMapping
    public ResponseEntity<CustomerResponseDto> create(@RequestBody CustomerRequestDto dto) {
        Customer entity = mapper.toEntity(dto);
        Customer saved = service.create(entity);
        return ResponseEntity.ok(mapper.toResponseDto(saved));
    }
}
```

### Mapper
```java
@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CustomerRequestDto dto);
    CustomerResponseDto toResponseDto(Customer entity);
}
```