# ⚽ Football Stats

Futbol istatistikleri için geliştirilmiş full-stack bir web uygulaması. Spring Boot backend, React + Vite frontend ve PostgreSQL veritabanından oluşur.

Kullanıcılar JWT ile giriş yaptıktan sonra oyuncu, kulüp, transfer ve maç istatistiklerini görüntüleyebilir.

## Özellikler

- JWT tabanlı kimlik doğrulama (kayıt / giriş)
- Oyuncu liderlik tabloları: gol, asist, kırmızı kart, piyasa değeri artışı
- Kulüp istatistikleri: en çok gol atan kulüpler, kulübün golcüleri, kulüp transferleri
- Transfer istatistikleri: yıla göre en değerli transferler
- Maç istatistikleri: lig ve sezona göre maç listesi
- Swagger / OpenAPI dokümantasyonu
- Docker Compose ile tek komutta çalıştırma

## Teknolojiler

| Katman | Teknolojiler |
|---|---|
| Backend | Java 17, Spring Boot 3.5, Spring Web, Spring Data JPA, Spring Security, JJWT, Lombok, springdoc-openapi |
| Frontend | React 19, Vite 7, React Router 7, Axios, Bootstrap 5 |
| Veritabanı | PostgreSQL |
| Altyapı | Docker, Docker Compose, Nginx |

## Proje Yapısı

```
.
├── demo/                       # Spring Boot backend
│   └── src/main/java/com/football/demo/
│       ├── config/             # CORS vb. konfigürasyon
│       ├── controller/         # REST uç noktaları
│       ├── dto/                # Veri transfer nesneleri
│       ├── entity/             # JPA entity'leri
│       ├── exception/          # Global hata yönetimi
│       ├── repository/         # Spring Data JPA repository'leri
│       ├── security/           # JWT ve Spring Security
│       ├── service/            # İş mantığı
│       └── util/               # DTO dönüştürücüleri
├── frontent/                   # React + Vite frontend
│   └── src/
│       ├── components/
│       ├── pages/
│       └── services/
├── docker-compose.yml
└── .env.example
```

> Not: Frontend klasörünün adı projede `frontent` olarak geçmektedir.

## Hızlı Başlangıç (Docker)

1. Örnek ortam dosyasını kopyalayın:

   ```bash
   cp .env.example .env
   ```

2. `.env` içindeki `DB_PASSWORD` ve `JWT_SECRET` değerlerini değiştirin.

3. Servisleri başlatın:

   ```bash
   docker compose up --build
   ```

4. Uygulamaya erişin:

   - Frontend: http://localhost:3000
   - Backend API: http://localhost:8080
   - Swagger UI: http://localhost:8080/swagger-ui/index.html

## Local Geliştirme

### Gereksinimler

- JDK 17+
- Node.js 20.19+ (veya 22.12+)
- PostgreSQL 14+

### Veritabanı

PostgreSQL üzerinde `football` adında bir veritabanı oluşturun. Bağlantı bilgilerini ortam değişkenleriyle verin:

**PowerShell:**

```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/football"
$env:DB_USERNAME="football"
$env:DB_PASSWORD="your-password"
$env:JWT_SECRET="your-long-random-secret"
```

### Backend

```bash
cd demo
./mvnw spring-boot:run
```

Windows:

```powershell
cd demo
.\mvnw.cmd spring-boot:run
```

### Frontend

```bash
cd frontent
npm install
npm run dev
```

Frontend varsayılan olarak http://localhost:5173 adresinde çalışır ve backend'e http://localhost:8080 üzerinden bağlanır. Farklı bir adres kullanmak için `frontent/.env` dosyasına şunu ekleyin:

```env
VITE_API_URL=http://localhost:8080
```

## Ortam Değişkenleri

| Değişken | Açıklama | Varsayılan |
|---|---|---|
| `DB_URL` | PostgreSQL JDBC adresi | `jdbc:postgresql://localhost:5432/postgres` |
| `DB_USERNAME` | Veritabanı kullanıcısı | `postgres` |
| `DB_PASSWORD` | Veritabanı şifresi | `postgres` |
| `JWT_SECRET` | JWT imzalama anahtarı (en az 32 karakter) | Geliştirme amaçlı varsayılan |
| `VITE_API_URL` | Frontend'in bağlanacağı backend adresi | `http://localhost:8080` |

> `JWT_SECRET` ve veritabanı şifresi production ortamında mutlaka değiştirilmelidir. `.env` dosyası git'e eklenmez.

## API Uç Noktaları

`/authenticate` ve `/register` dışındaki tüm uç noktalar `Authorization: Bearer <token>` başlığı gerektirir.

### Kimlik Doğrulama

| Metot | Yol | Açıklama |
|---|---|---|
| `POST` | `/register` | Yeni kullanıcı kaydı |
| `POST` | `/authenticate` | Giriş; JWT döner |
| `POST` | `/register/admin` | Admin kullanıcı kaydı (ADMIN yetkisi gerekir) |

Örnek:

```bash
curl -X POST http://localhost:8080/register \
  -H "Content-Type: application/json" \
  -d '{"username":"demo","password":"demo123"}'

curl -X POST http://localhost:8080/authenticate \
  -H "Content-Type: application/json" \
  -d '{"username":"demo","password":"demo123"}'
```

### Oyuncular

| Metot | Yol | Parametreler |
|---|---|---|
| `GET` | `/player/scorers` | `year`, `size` |
| `GET` | `/player/assisters` | `year`, `size` |
| `GET` | `/player/reds` | `year`, `size` |
| `GET` | `/player/rising` | `year`, `size` |

### Kulüpler

| Metot | Yol | Parametreler |
|---|---|---|
| `GET` | `/club/most-scorer-clubs-by-year` | `year`, `size` |
| `GET` | `/club/most-scorer-players/{clubId}` | `year`, `size` |
| `GET` | `/club/transfers/{clubId}` | `year` |

### Transferler

| Metot | Yol | Parametreler |
|---|---|---|
| `GET` | `/transfer/most-valuable` | `year`, `size` |

### Müsabakalar

| Metot | Yol | Parametreler |
|---|---|---|
| `GET` | `/competition/{competitionId}/games` | `season`, `page`, `size` |

## Veri Modeli

Başlıca entity'ler:

- `Player`, `Club`, `Competition`
- `Transfer`, `Game`
- `Appearance`, `GameEvent`, `GameLineup`
- `PlayerValuation`
- `User` (kimlik doğrulama)

Uygulama, mevcut bir futbol veri setinin (oyuncu/kulüp/maç/transfer tabloları) PostgreSQL'e yüklenmiş olmasını bekler.

## Notlar ve Sınırlamalar

- `spring.jpa.hibernate.ddl-auto=update` geliştirme kolaylığı için açıktır; production'da `validate` veya migration aracı (Flyway/Liquibase) tercih edilmelidir.
- Otomatik test (`DemoApplicationTests`) çalışan bir PostgreSQL bağlantısı gerektirir.
- CORS yalnızca `http://localhost:5173` ve `http://localhost:3000` adreslerine izin verir.
- Uygulama eğitim/portfolyo amaçlıdır.

## Lisans

Bu proje eğitim ve portfolyo amaçlı paylaşılmıştır.
