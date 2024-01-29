# Banking project with Spring Framework
Master's backend project designed to demonstrate basic banking features using Spring Framework.
## Spring concepts used
- request mapping (controller, service, model, repository)
- JDBC configuration (hibernate, data validation)
- entity relationships mapping
- DTOs, mappers
- exception handling
## Features
1. **Bank branch management**
  - add new bank branch identified by name, city, address
  - list bank branch details 
  - list all the bank branches
  - update city of the bank branch
  - list all the customers from a given bank branch
2. **Customer management**
  - add new customer identified by first name, last name, phone, date of birth, cnp, status
  - list customer details
  - list customers with name starting with a given prefix
3. **Account management**
  - add new account for a customer
  - list account details (creation date, balance, type, status)
  - list all the accounts for a customer
  - deactivate account
  - delete account
  - update balance for account
4. **Transactions management**
  - add new transaction identified by date, amount, status (PENDING, SETTLED), account, details (product name, quantity)
  - list transaction details
  - list all the transactions
  - list the pending transactions
  - list transactions for a given customer
  - settle transaction

