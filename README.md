# Polynomial Constant Recovery using Lagrange Interpolation

## 📌 Problem Overview

The task is to reconstruct a polynomial using the minimum number of given roots and determine its **constant term**.
The roots are provided in a JSON file where each entry represents a point **(x, y)**.
The value **y** is encoded in different numerical bases and must be converted to decimal before processing.

This problem closely resembles the reconstruction phase of **Shamir Secret Sharing**, where the secret corresponds to the constant term of the polynomial.

---

## ⚙️ Approach / Process

### 1. JSON Parsing

* The input JSON file is read from disk.
* The value **k** determines the minimum number of points required to reconstruct the polynomial.

### 2. Base Conversion

* Each root contains a base and encoded value.
* The encoded value is converted into decimal using `BigInteger` to support very large numbers.

### 3. Point Construction

* Each JSON key is treated as the **x-coordinate**.
* The decoded value becomes the **y-coordinate**.
* The first **k points** are selected for reconstruction.

### 4. Polynomial Reconstruction

* The polynomial is reconstructed using **Lagrange Interpolation**.
* Instead of building the full polynomial, interpolation is evaluated directly at **x = 0**.

### 5. Secret / Constant Term

* Evaluating the polynomial at **x = 0** yields the constant term.
* This constant is printed as the final output.

---

## 🔐 Algorithm Insight

The implementation simulates the reconstruction phase of **Shamir Secret Sharing**, where:

* The polynomial constant represents the hidden secret
* Each (x, y) pair represents a share
* Any **k shares** can reconstruct the original secret

Thus, the solution demonstrates threshold-based polynomial recovery.

---

## 🚀 How to Run

1. Place the input JSON as `input.json` in the project directory
2. Compile the program

   ```bash
   javac SecretPolynomial.java
   ```
3. Run the program

   ```bash
   java SecretPolynomial
   ```

---

## 🧠 Key Highlights

* Handles extremely large values using `BigInteger`
* Supports mixed-base numeric decoding
* Uses threshold-based polynomial reconstruction
* Works for both small and large test cases

---

## 📎 Output

The program prints the recovered constant term (secret) of the polynomial.

---
