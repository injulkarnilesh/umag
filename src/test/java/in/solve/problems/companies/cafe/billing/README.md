# Cafe Billing

We have a cafe with few items, each with it's billing code.
| Code          | Item           |Price           |
| ------------- |:-------------:|:-------------|
| NT      | Normal Tea | 10.00 |
| ST      | Special Tea      |20.00 |
| CCF | Cold Coffee      |40.00 |
| HCF | Hot Coffee      |20.00 |
| TK | Masala Tak      |20.00 |
| LS | Plain Lassi      |30.00 |
| MLS | Mango Plain Lassi | 40.00 |

Create a program which calculates and prints the bill of items of purchased.
Bill also considers the discounts applied.
For simple example consider following two discounts.
  - If total bill is greater than 100 then give 5% discount.
  - If total bill is greater than 200 then give 10% discount.
  - If three or more Cold Coffees are included, then each cold coffee gets 5 Rs discount.

Example output :
| Item          | Price      | Count           |Total Price          |
| ------------- |:-------------:|:-------------:|:-------------:|
| Hot Coffee      | 20       | 2                | 40.00        |
| Plain Lassi      | 30       | 2                | 60.00         |
| Cold Coffee      | 40       | 4                | 160.00         |
| Total     |        |               |    260.00    |
| Discounts      |        |               |        |
| Rs 5 Off on each Cold Coffees for more than 3 |        |               | -20.00       |
| 10% for Bill More than 200      |        |               |    -26.00    |
| Effective Total     |        |               |    214.00    |

Please consider ways to incorporate future possible updates like:
  - Bill could be printed in text format for now, but shuold be able to print in HTML.
  - Should have a ability to choose a discount if multiple applicable to choose whichever gives maximum discount.


