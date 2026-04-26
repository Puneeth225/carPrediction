# 🚗 Car Recommendation Assistant

## 📌 Overview

Buying a car can be overwhelming due to the number of options available.
This project simplifies that process by helping users go from *“I don’t know what to buy”* to a **clear shortlist of cars** based on their preferences.

The app takes user inputs like budget, fuel type, and priority (mileage/safety), and returns the **top recommended cars along with reasoning**.

---

## 🎯 What I Built

A lightweight **full-stack web application** that:

* Accepts user preferences via a simple UI
* Processes them using a backend recommendation engine
* Returns a shortlist of cars with explanations

👉 Focus was on **decision-making**, not just displaying data.

---

## ✂️ What I Deliberately Cut

I intentionally skipped:

* Database (used in-memory dataset instead)
* Authentication
* Advanced filters & sorting UI
* Complex UI frameworks (React, etc.)

This allowed me to focus on the **core problem: helping users decide**.

---

## 🛠️ Tech Stack

### Backend

* Core Java using `HttpServer`
* No frameworks (Spring avoided intentionally)
* Handles routing, parsing, and recommendation logic

### Frontend

* HTML + CSS + Vanilla JavaScript
* Simple and functional UI

### Data

* Hardcoded dataset of cars (varied across budget, fuel, and safety)

---

## ⚙️ How to Run

### 1. Clone the repository

```bash
git clone https://github.com/your-username/carPrediction.git
cd src
```

### 2. Compile and run backend

```bash
javac Main.java
java Main
```

### 3. Open in browser

```
http://localhost:8080
```

---

## 🧠 How It Works

### Input

* Budget (optional)
* Fuel type
* Priority (Mileage / Safety)

### Logic

* Cars are scored based on:

  * Budget match
  * Fuel preference
  * Priority weighting (mileage/safety)

### Output

* Top 3 recommendations
* Each includes a short explanation

### Special Case

If budget is not provided:

> The system still returns recommendations and clearly indicates that budget was not considered.

---

## ✨ Key Features

* ✅ End-to-end working full-stack app
* ✅ Recommendation engine (not just filtering)
* ✅ Handles missing inputs gracefully
* ✅ Clean UI with structured layout
* ✅ Lightweight and fast (no heavy dependencies)

---

## 🤖 AI Usage

### Used AI for:

* Initial scaffolding of backend and frontend
* Generating UI structure
* Debugging edge cases

### Done manually:

* Recommendation logic design
* Debugging routing issues
* Handling input validation and edge cases
* Improving UX decisions

### Observations:

* AI was great for speed
* Required careful review to avoid incorrect logic or overengineering

---

## ⚠️ Challenges Faced

* Avoiding crashes due to invalid input (e.g., empty budget)
* Designing a simple yet meaningful recommendation system

---

## 📹 Screen Recording

https://drive.google.com/file/d/13TKOm3CjXcDKNHfW0Y9vWcbq4U6PwKBL/view?usp=sharing

---

## 🌐 Live Demo

<img width="1137" height="655" alt="image" src="https://github.com/user-attachments/assets/330db87a-3f3f-45ba-ad38-54a17c50dfea" />
<img width="1669" height="661" alt="image" src="https://github.com/user-attachments/assets/37975eb3-59a3-4578-b9e3-98d8dbd11cb4" />


---

## 🙌 Final Thought

This project prioritizes **clarity, speed, and decision-making value** over feature bloat — aligning with real-world product development constraints.
