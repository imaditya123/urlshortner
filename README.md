# URL Shortener

A simple and efficient URL shortener that converts long URLs into short, easy-to-share links. This project helps users generate short links and track their usage.

## Features

- 🔗 Shorten long URLs instantly
- 🔐 Custom alias support (optional)
- 🚀 Fast and scalable solution
---
## Installation

### Prerequisites

Ensure you have the following installed:

- **Java 11+**
- **Maven**

### Steps to Run Locally

1. **Clone the repository:**
   ```bash
   git clone https://github.com/imaditya123/urlshortner.git
   cd urlshortner
  
2. **Build the project:**
   ```bash
   ./mvnw clean install

3. **Run the application:**
   ```bash
   ./mvnw clean install  

---
## Usage

Once the application is running:

1. Open your browser and navigate to `http://localhost:8080`
2. Enter a long URL and click "Shorten"
3. Copy and share the generated short URL
4. (Optional) Check analytics for usage statistics
---
## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/` | Shortens a long URL |
| `GET`  | `/{shortUrl}` | Redirects to the original URL |

---
## Contributing

Contributions are welcome! To contribute:

1. Fork the repository
2. Create a new branch (`git checkout -b feature-name`)
3. Commit your changes (`git commit -m 'Add new feature'`)
4. Push to the branch (`git push origin feature-name`)
5. Create a Pull Request
---
Made with ❤️ by Adi
