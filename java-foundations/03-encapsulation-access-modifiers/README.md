# Encapsulation and Access Modifiers

This beginner mini-project uses a bank account to demonstrate how a class can
protect its state and expose only controlled operations.

## Encapsulation

Encapsulation keeps an object's data and the methods that work with that data
together, while controlling how other code can access or change the data. A
`BankAccount` owns its account details and validates each balance-changing
transaction.

## Private fields and getters

`accountHolder`, `accountNumber` and `balance` are `private`. Code outside
`BankAccount` cannot access or change these fields directly. For example,
`Main` cannot read or assign `firstAccount.balance`; attempting direct access
would be a compile-time error.

The public getters provide read-only access to the account holder, account
number and current balance. There is deliberately no `setBalance()` method.

## Business methods instead of an unrestricted setter

A setter would let a caller replace the balance with any value, without
explaining or validating why the balance changed. Methods such as
`deposit()` and `withdraw()` represent actual account operations: they check
the amount, enforce the available-balance rule, update the balance only for a
valid transaction, and report success or rejection. The methods return
`true` for an accepted transaction and `false` for a rejected one.

The constructor also rejects a negative or non-finite initial balance.

## Access modifier basics

- `public` members can be accessed by code from any package.
- `private` members can be accessed only inside the class that declares them.
- `protected` members can be accessed in the same package and by subclasses.
- A member with no modifier has package-private access and is available only
  within its package.

The fields are private, while the constructor, getters and account operations
are public so callers can create and use an account through its supported API.

## Test scenarios

The `Main` class prints `PASS` or `FAIL` for each scenario:

- A positive deposit increases the balance by the expected amount.
- A valid withdrawal decreases the balance by the expected amount.
- A negative deposit is rejected and leaves the balance unchanged.
- A withdrawal greater than the available balance is rejected without a
  balance change.
- A zero-value deposit and withdrawal are rejected without changing the
  balance.
- Depositing into one account does not change the other account's balance.
- Constructing an account with a negative initial balance is rejected.

The demo also displays two accounts and their final details, and reads the
balance using `getBalance()`.

## Compile and run on Windows CMD

From the project root, compile this lesson into the ignored `out` directory:

```cmd
javac -d out java-foundations\03-encapsulation-access-modifiers\*.java
```

Run the demo:

```cmd
java -cp out Main
```
