# Plataformas_20261_Monorepo
Repositorios con todos los ejercicios de clase

## Datos 
* **Nombre completo:** María Fernanda Muñoz Acosta
* **Variables asignadas (#11):**
  * Puerto de Hash: CredentialHasher
  * Base Path: /api/token
  * Claim JWT: "authorities"
  * Propiedad YAML: jwt.hmac.secret / jwt.hmac.expiry

## Comandos cURL de Prueba

### 1. Registro
curl -X POST http://localhost:6001/api/token/register \
-H "Content-Type: application/json" \
-d '{ "name": "Maria Buyer", "email": "maria@upb.edu.co", "password": "MiPassword123", "role": "BUYER" }'

### 2. Login
curl -X POST http://localhost:6001/api/token/login \
-H "Content-Type: application/json" \
-d '{ "email": "maria@upb.edu.co", "password": "MiPassword123" }'

### 3. Acceso con token (listar eventos)
curl http://localhost:6001/api/events \
-H "Authorization: Bearer <PEGA_AQUI_TU_TOKEN>"

### 4. Acceso denegado (403) - Buyer intentando crear evento
curl -X POST http://localhost:6001/api/events \
-H "Authorization: Bearer <PEGA_AQUI_TU_TOKEN_DE_BUYER>" \
-H "Content-Type: application/json" \
-d '{ "name": "Mi evento" }'