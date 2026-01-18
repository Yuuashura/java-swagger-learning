# 🛒 REST API Toko Paduka Yudis

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.1-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Supabase-blue)
![Railway](https://img.shields.io/badge/Deploy-Railway-white)
![Instagram](https://img.shield.io/badge/@Yudis.Ashura-Instagram-orange)

Project ini adalah **Backend REST API** untuk manajemen produk toko, dibuat menggunakan Java Spring Boot. Project ini mendukung fitur CRUD lengkap, Pagination, Pencarian, dan dokumentasi otomatis via Swagger UI.

---

## 🚀 Live Demo
Aplikasi sudah di-deploy dan bisa diakses secara publik:

- **Base URL:** `https://java-swagger-learning-production.up.railway.app`
- **Swagger UI (Dokumentasi Interaktif):** [Klik Disini untuk Membuka Swagger](https://java-swagger-learning-production.up.railway.app/swagger-ui/index.html)

---

## 🛠️ Teknologi yang Digunakan
* **Language:** Java (JDK 21)
* **Framework:** Spring Boot 3
* **Database:** PostgreSQL (via Supabase)
* **Deployment:** Railway
* **Build Tool:** Maven
* **Architecture:** Controller - Service - Repository (with DTO Pattern)

---

## 📚 Dokumentasi Endpoint (API)

Berikut adalah daftar endpoint yang tersedia di `/api`.

### 1. Ambil Data (Read)
| Method | Endpoint | Deskripsi | Contoh Parameter |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/getData` | Mengambil **semua** data produk (tanpa paging) | - |
| `GET` | `/api/getDataPage` | Mengambil data per halaman (Pagination) | `?page=1` |
| `GET` | `/api/searchByName` | Mencari produk berdasarkan **Nama** | `?keyword=laptop` |
| `GET` | `/api/searchByCategory` | Mencari produk berdasarkan **Kategori** | `?keyword=elektronik` |

### 2. Manipulasi Data (CUD)
> **Catatan:** Endpoint ini menggunakan format `multipart/form-data` atau `x-www-form-urlencoded` (Bukan Raw JSON).

| Method | Endpoint | Deskripsi | Field yang Dibutuhkan |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/addData` | Menambah produk baru | `name`, `price`, `description`, `category`, `stockQuantity`, `pictureUrl` |
| `PUT` | `/api/updateData` | Mengupdate produk (Wajib kirim ID) | `id`, `name`, `price`, ... (data baru) |
| `DELETE` | `/api/deleteData` | Menghapus produk | `id` (via Query Param) |

---

## ⚙️ Cara Menjalankan di Lokal (Localhost)

1.  **Clone Repository**
    ```bash
    git clone [https://github.com/username-anda/repo-anda.git](https://github.com/username-anda/repo-anda.git)
    ```

2.  **Setting Database**
    Buka `src/main/resources/application.properties` dan pastikan konfigurasi DB sudah benar atau set Environment Variables:
    * `DB_URL`
    * `DB_USERNAME`
    * `DB_PASSWORD`

3.  **Build & Run**
    ```bash
    mvn clean package -DskipTests
    java -jar target/*.jar
    ```

4.  **Akses Swagger Local**
    Buka browser: `http://localhost:8080/swagger-ui.html`

---

## ☁️ Deployment (Railway)

Project ini dikonfigurasi untuk deploy otomatis di Railway.

**Environment Variables yang Wajib di-set di Railway:**
* `DB_URL` : URL koneksi JDBC (jdbc:postgresql://...)
* `DB_USER` : Username database (biasanya `postgres`)
* `DB_PASSWORD` : Password database
* `PORT` : (Otomatis diisi Railway)

**Build Command:**
```bash
mvn clean package -DskipTests