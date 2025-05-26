# 🛴 Tuul Scooter Frontend (React)

This is the frontend application for the **Tuul scooter-sharing platform**, built with **React + TypeScript**. It covers core functionality such as user authentication, scooter pairing, ON/OFF commands, and displaying vehicle details like location, battery status, range, and more.

---

## 🚀 Getting Started

1. **Install dependencies:**

```bash
npm install
```

2. **Prepare environment variables:**

```bash
npm run prepare:env
# or manually:
# cp .env.example .env
```

3. **Run the development server:**

```bash
npm run dev:withMock     # uses mocked data for unstable APIs
npm run dev:withoutMock  # uses real Firebase APIs
```

---

## ⚠️ Why Mock Mode?

The public API is partially unstable or restricted:

- `MAX_PAIRED_USERS_EXCEEDED` when pairing scooters
- Firestore security rules block access to `vehicles` collection
- Some endpoints fail or require unknown `vehicleId`

To ensure a smooth developer experience, mock mode (`VITE_USE_FIREBASE_MOCK=true`) is supported. It simulates:

- a logged-in user with a predefined `activeVehicle`
- static vehicle data for dashboard display

---

## 🧱 Project Architecture

We follow a **feature-based layered architecture**:

```
src/
├── shared/        // Reusable UI components, types, utils
├── pages/         // Route-specific pages (Login, Dashboard)
├── features/      // Self-contained business logic (e.g. pairScooter)
├── entities/      // Domain-specific data access (User, Vehicle)
├── config/        // Firebase and environment configuration
```

This structure ensures:

- 📦 high modularity
- 🧼 clean separation of concerns
- ♻️ scalable and maintainable codebase

---

## 🔗 Available Routes

After running the app at `http://localhost:5173/`, the following routes are available:

| Path               | Description                 |
|--------------------|-----------------------------|
| `/`                | Dashboard (requires auth)   |
| `/login`           | Login page                  |
| `/register`        | Registration page           |
| `/dashboard`       | Alias for dashboard         |
| Any invalid path   | 404 Not Found               |

---

## 🧰 Stack

- **React 19 + TypeScript**
- **Vite** for fast builds
- **Firebase (Auth + Firestore)**
- **Tailwind CSS**
- **React Router v7**
- **Google Maps via `@react-google-maps/api`**
- **ESLint + Prettier** for consistent code

---