class Account:
    def __init__(self, account_number, balance=0):
        self.account_number = account_number
        self.balance = balance

    def deposit(self, amount):
        self.balance += amount
        return f"Deposit successful. New balance: ${self.balance}."

    def withdraw(self, amount):
        if amount > self.balance:
            return "Insufficient funds."
        else:
            self.balance -= amount
            return f"Withdrawal successful. Remaining balance: ${self.balance}."

    def get_balance(self):
        return f"Current balance: ${self.balance}."

class CheckingAccount(Account):
    pass

class SavingsAccount(Account):
    pass

# Simulating a database for card and PIN authentication
card_and_pin = {"123456789": "1234", "987654321": "4321"}  # Example card numbers and PINs

def authenticate(card_number, pin, attempts=3):
    while attempts > 0:
        if card_number in card_and_pin and card_and_pin[card_number] == pin:
            print("Authentication successful.")
            return True
        else:
            attempts -= 1
            print(f"Authentication failed. Attempts left: {attempts}.")
            if attempts == 0:
                print("Account locked. Please contact administrator.")
                return False
            pin = input("Enter PIN: ")

def main_menu():
    print("\nMain Menu:")
    print("1. Deposit to Checking Account")
    print("2. Withdraw from Checking Account")
    print("3. Get Checking Account Balance")
    print("4. Deposit to Savings Account")
    print("5. Withdraw from Savings Account")
    print("6. Get Savings Account Balance")
    print("7. Transfer between Accounts")
    print("8. Exit")
    choice = input("Choose an option: ")
    return choice

def transfer_funds(source_account, target_account, amount):
    if source_account.withdraw(amount) != "Insufficient funds.":
        target_account.deposit(amount)
        return "Transfer successful."
    else:
        return "Transfer failed. Insufficient funds in source account."

def atm_application():
    checking_account = CheckingAccount("111", 1000)  # Example checking account
    savings_account = SavingsAccount("222", 500)     # Example savings account

    card_number = input("Enter Card Number: ")
    pin = input("Enter PIN: ")
    if not authenticate(card_number, pin):
        return

    while True:
        choice = main_menu()
        if choice == "1":
            amount = float(input("Enter deposit amount for Checking Account: "))
            print(checking_account.deposit(amount))
        elif choice == "2":
            amount = float(input("Enter withdrawal amount from Checking Account: "))
            print(checking_account.withdraw(amount))
        elif choice == "3":
            print(checking_account.get_balance())
        elif choice == "4":
            amount = float(input("Enter deposit amount for Savings Account: "))
            print(savings_account.deposit(amount))
        elif choice == "5":
            amount = float(input("Enter withdrawal amount from Savings Account: "))
            print(savings_account.withdraw(amount))
        elif choice == "6":
            print(savings_account.get_balance())
        elif choice == "7":
            direction = input("Transfer from Checking to Savings (C2S) or Savings to Checking (S2C)?: ")
            amount = float(input("Enter transfer amount: "))
            if direction.upper() == "C2S":
                print(transfer_funds(checking_account, savings_account, amount))
            elif direction.upper() == "S2C":
                print(transfer_funds(savings_account, checking_account, amount))
            else:
                print("Invalid transfer direction. Please enter 'C2S' or 'S2C'.")
        elif choice == "8":
            print("Exiting.")
            break
        else:
            print("Invalid choice. Please try again.")

if __name__ == "__main__":
    atm_application()
