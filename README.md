# 🎵 VinylVerse – Vinyl Collector Platform

**VinylVerse** is a web platform for vinyl collectors to manage their record collections, buy and sell records, and interact with a community of collectors. The application features **role-based access control**, allowing collectors to manage their own collections while admins oversee and manage the entire system.

---

## 🛠️ Technologies

- **Backend:** Spring Boot, JPA/Hibernate, MySQL/PostgreSQL, Spring Security, JWT Authentication  
- **Frontend:** React, Redux/Context API (optional), TailwindCSS / Material-UI  
- **Build & Deployment:** Maven/Gradle, npm, Docker (optional)  
- **Version Control:** Git / GitHub  

---

## 👥 User Roles

| Role       | Permissions |
|------------|-------------|
| **Collector** | Create account, manage personal collection, add/edit/remove records, buy/sell records |
| **Admin**     | Manage all users, records, collections, and oversee marketplace operations |

---

## 🔑 Features

### Collector
- Account Management: Sign up, login, edit profile  
- Collection Management: Add, update, delete vinyl records in personal collection  
- Marketplace: List vinyl records for sale, browse available records  
- Search & Filter: Search records by artist, genre, label, year  

### Admin
- Manage users and roles  
- Approve or remove vinyl records from marketplace  
- Monitor sales and collection activities  

---

## 🗂️ Database / Entities (Example)
- **User:** `userId`, `firstName`, `lastName`, `email`, `password`, `role`, `createdAt`, `updatedAt`  
- **Record:** `recordId`, `title`, `artist`, `label`, `year`, `genre`, `price`, `ownerId`  
- **Transaction/Sale:** `transactionId`, `recordId`, `buyerId`, `sellerId`, `status`, `date`  

> Enum types used: `RolesEnum { COLLECTOR, ADMIN }` for role management.

---

## 🏗️ Project Structure

### Backend (Spring Boot)