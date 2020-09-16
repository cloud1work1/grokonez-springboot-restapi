# grokonez-springboot-restapi
- ## Controller
  - ```
    List<Customer> customers = new ArrayList<>();
    customerRepository.findAll().forEach(customers::add);
    ```
  - ```
    try{
    }catch(Exception ex) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
    ```
