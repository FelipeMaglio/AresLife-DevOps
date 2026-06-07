# 🚀 AresLife — API

Projeto desenvolvido para a **Global Solution 2026** utilizando **Java + Spring Boot**.

Plataforma integrada de simulação de colonização e turismo espacial que utiliza IoT e Cloud para monitorar habitats espaciais, gerenciar recursos críticos, ocupantes e operações relacionadas à colonização da Lua e de Marte.

---

# 👥 Equipe

| Nome | RM |
|---|---|
| Felipe Maglio | RM563512 |
| Vitória Valentina Maglio | RM563509 |
| Mateus Granja dos Santos | RM564930 |
| João Pedro Bitencourt Goldoni | RM564339 |
| Marina Tamagnini Magalhães | RM561786 |

---

# 📑 Índice

- [📌 Descrição do Projeto](#-descrição-do-projeto)
- [🎯 Objetivo da API](#-objetivo-da-api)
- [💼 Benefícios para o Negócio](#-benefícios-para-o-negócio)
- [🛠️ Tecnologias Utilizadas](#️-tecnologias-utilizadas)
- [☁️ Arquitetura Cloud](#️-arquitetura-cloud)
- [🚀 Como Instalar e Rodar (How To)](#-como-instalar-e-rodar-how-to)
- [📜 Script Azure CLI](#-script-azure-cli)
- [🐳 Dockerfile](#-dockerfile)
- [🐳 Docker Compose](#-docker-compose)
- [📌 Funcionalidades da API](#-funcionalidades-da-api)
- [⭐ Diferenciais da API](#-diferenciais-da-api)
- [📂 Estrutura do Projeto](#-estrutura-do-projeto)

---

# 📌 Descrição do Projeto

O **AresLife** é uma API REST desenvolvida em **Java com Spring Boot** com o objetivo de simular e gerenciar habitats espaciais para colonização e turismo, através de:

- monitoramento de recursos críticos em tempo real (O₂, água, energia, temperatura)
- alertas automáticos para situações de risco
- histórico longitudinal de eventos e ocupantes

A plataforma organiza de forma completa o ecossistema de habitats espaciais, incluindo:

- cadastro e gestão de turistas espaciais e habitantes (astronautas e turistas)
- gestão de colônias na Terra, Lua e Marte
- controle de recursos críticos com simulação de consumo e projeção de autonomia
- sistema de alertas automáticos com thresholds configuráveis
- monitoramento de saúde dos habitantes com geração automática de alertas críticos
- gestão de treinamentos e viagens turísticas com controle de pacotes
- autenticação JWT com registro e login de usuários

A solução foi criada com base no desafio proposto pela **Global Solution FIAP 2026**, focando em:

- segurança humana em ambientes espaciais hostis
- operação autônoma com latência de até 22 minutos (Marte)
- tomada de decisão baseada em dados em tempo real
- gestão de turistas civis e astronautas no mesmo habitat
- missões alinhadas com NASA Artemis · SpaceX Starship · ESA Moon Village

---

# 🎯 Objetivo da API

O sistema possui funcionalidades inteligentes capazes de:

- simular habitats espaciais (colônias) com consumo diário de recursos via `/simular-dia`
- monitorar recursos vitais (O₂, água, energia, alimentação) em tempo real com cálculo de autonomia
- aumentar a segurança operacional com thresholds configuráveis e alertas automáticos
- apoiar missões espaciais futuras com histórico completo de eventos e saúde dos habitantes
- melhorar a gestão de turistas com perfis diferenciados, treinamentos obrigatórios e viagens com pacotes
- automatizar alertas 24/7 — inclusive disparados por sinais vitais críticos dos habitantes

---

# 💼 Benefícios para o Negócio

| Benefício | Impacto |
|---|---|
| Alertas automáticos de risco crítico | Detecção imediata — protege vidas em ambientes hostis |
| Simulação de consumo diário | Permite planejamento logístico de missões com dados reais |
| Gestão de turistas e astronautas | Protocolos de segurança distintos para civis e profissionais |
| Treinamento obrigatório antes de viagem | Garante preparo mínimo antes do embarque espacial |
| Histórico de saúde dos habitantes | Rastreabilidade completa de sinais vitais por habitante |
| Dashboard consolidado | Dá visibilidade gerencial para operadores tomarem decisões rápidas |
| Infraestrutura em nuvem Azure | Alta disponibilidade e escalabilidade sem servidor físico |
| Banco Oracle em container | Dados persistidos com segurança e fácil restauração |

---

# 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 4.0.6**
- **Spring Data JPA**
- **Spring Validation**
- **Spring HATEOAS**
- **Spring Security + JWT**
- **Oracle Database**
- **Swagger / OpenAPI (springdoc 2.5.0)**
- **Maven**
- **Lombok**
- **Docker**
- **Docker Compose**
- **Microsoft Azure**
- **Azure CLI**

---

# ☁️ Arquitetura Cloud

A infraestrutura do projeto foi construída utilizando recursos da **Microsoft Azure**, garantindo:

- escalabilidade
- disponibilidade
- persistência de dados
- automação de deploy
- facilidade de gerenciamento

### Componentes utilizados

- Azure Virtual Machine (Ubuntu)
- Oracle Database em container Docker
- API Spring Boot containerizada
- Azure Network Security Group (NSG)
- Docker Compose para orquestração

---

# 🚀 Como Instalar e Rodar (How To)

## ✅ Pré-requisitos

- Conta Microsoft Azure com créditos ativos
- Acesso ao Azure Cloud Shell

Acesse:

```bash
https://shell.azure.com
```

---

## ☁️ Passo 1 — Acesse o Azure Cloud Shell

Acesse:

```bash
https://shell.azure.com
```

Faça login com sua conta Azure e selecione **Bash** quando solicitado.

---

## 📤 Passo 2 — Faça upload do script

O arquivo `deploy.sh` está disponível dentro do repositório:

```bash
https://github.com/FelipeMaglio/AresLife-DevOps.git
```

No Azure Cloud Shell:

1. Clique em:

```text
Manage Files → Upload
```

2. Faça upload do arquivo:

```text
deploy.sh
```

3. Execute os comandos:

```bash
chmod +x deploy.sh

sed -i 's/\r$//' deploy.sh

./deploy.sh
```

---

## ⏳ Passo 3 — Aguarde o deploy

O processo leva aproximadamente:

```text
15 minutos
```

---

# ✅ Evidências do funcionamento da infraestrutura

Após executar o `deploy.sh`, rode os comandos abaixo para validar que toda a infraestrutura foi criada corretamente.

---

## 🐳 Evidência 1 — Containers rodando em background

```bash
az vm run-command invoke \
--resource-group rg-areslife \
--name vm-areslife \
--command-id RunShellScript \
--scripts 'cd /home/azureuser/AresLife-DevOps && sudo docker compose ps'
```

### Resultado esperado

Os dois containers devem aparecer com status:

```text
Up
```

---

## 👤 Evidência 2 — Usuário não-root

```bash
az vm run-command invoke \
--resource-group rg-areslife \
--name vm-areslife \
--command-id RunShellScript \
--scripts 'sudo docker exec app-rm564339 whoami'
```

### Resultado esperado

```text
appuser
```

---

## 💾 Evidência 3 — Volume nomeado

```bash
az vm run-command invoke \
--resource-group rg-areslife \
--name vm-areslife \
--command-id RunShellScript \
--scripts 'sudo docker volume ls'
```

### Resultado esperado

```text
areslife-devops_oracle-data
```

---

## 📜 Evidência 4 — Logs da aplicação funcionando

```bash
az vm run-command invoke \
--resource-group rg-areslife \
--name vm-areslife \
--command-id RunShellScript \
--scripts 'cd /home/azureuser/AresLife-DevOps && sudo docker compose logs --tail=20 app-rm564339'
```

### Resultado esperado

Deve aparecer algo semelhante a:

```text
Started Application in X seconds
```

Confirmando que o Spring Boot iniciou corretamente.

---

## 🗂️ Evidência 5 — docker exec no container da API

```bash
az vm run-command invoke \
--resource-group rg-areslife \
--name vm-areslife \
--command-id RunShellScript \
--scripts 'sudo docker exec app-rm564339 sh -c "whoami && pwd && ls -l"'
```

### Resultado esperado

```text
appuser
/app
total XX
-rw-r--r-- 1 appuser appuser ... app.jar
```

---

## 🗂️ Evidência 6 — docker exec no container do Oracle

```bash
az vm run-command invoke \
--resource-group rg-areslife \
--name vm-areslife \
--command-id RunShellScript \
--scripts 'sudo docker exec oracle-rm564339 sh -c "whoami && pwd && ls -l /opt/oracle"'
```

### Resultado esperado

```text
oracle
/
drwxr-xr-x ... oradata
drwxr-xr-x ... product
```

---

## 🗄️ Evidência 7 — SELECT direto dentro do container Oracle (via sysdba)

```bash
az vm run-command invoke \
--resource-group rg-areslife \
--name vm-areslife \
--command-id RunShellScript \
--scripts 'sudo docker exec oracle-rm564339 bash -c "ORACLE_SID=XE ORACLE_HOME=/opt/oracle/product/21c/dbhomeXE PATH=/opt/oracle/product/21c/dbhomeXE/bin:\$PATH sqlplus -s / as sysdba << EOF
ALTER SESSION SET CONTAINER=XEPDB1;
SELECT table_name FROM all_tables WHERE owner='"'"'ARESLIFE'"'"' ORDER BY table_name;
EXIT;
EOF"'
```

### Resultado esperado

```text
Session altered.

TABLE_NAME
------------------------------
ALERTAS
COLONIAS
HABITANTES
RECURSOS
SAUDE_HABITANTES
TREINAMENTOS
TURISTAS
VIAGENS
```

> Conectado diretamente no container Oracle via `docker exec` usando `sqlplus` como sysdba, sem nenhuma ferramenta externa.

---

Após finalizar, acesse:

### Swagger

```bash
http://IP_DA_VM:8080/swagger-ui.html
```

### API

```bash
http://IP_DA_VM:8080
```

---

# 🗄️ Passo 3.1 — Conectar no banco via SQL Developer (Opcional)

Após o deploy, é possível conectar diretamente no banco Oracle utilizando o **SQL Developer**.

### Download do SQL Developer

```bash
https://www.oracle.com/tools/downloads/sqldev-downloads.html
```

---

## 🔗 Dados de conexão

| Campo | Valor |
|---|---|
| Name | AresLife Azure |
| Username | areslife |
| Password | Ares@2026 |
| Connection Type | Basic |
| Hostname | IP_DA_VM |
| Port | 1521 |
| Service Name | XEPDB1 |

⚠️ **IMPORTANTE:**  
Marcar **Service Name** e NÃO **SID**.

---

## 📋 Passo a passo

### 1️⃣ Abrir o SQL Developer

Abra o aplicativo normalmente.

---

### 2️⃣ Criar nova conexão

Clique em:

```text
Connections → New Connection
```

ou no botão:

```text
+
```

verde no canto esquerdo.

---

### 3️⃣ Preencher os dados

Utilize os dados informados anteriormente.

---

### 4️⃣ Testar conexão

Clique em:

```text
Test
```

O resultado esperado:

```text
Status: Success
```

---

### 5️⃣ Conectar

Clique em:

```text
Connect
```

---

## 📂 Estrutura esperada no painel

Após conectar, expanda:

```text
AresLife Azure
 └── Other Users
     └── ARESLIFE
         └── Tables
             ├── TURISTAS
             ├── COLONIAS
             ├── RECURSOS
             ├── HABITANTES
             ├── SAUDE_HABITANTES
             ├── TREINAMENTOS
             ├── VIAGENS
             └── ALERTAS
```

---

## 🔎 Consultar dados via SQL

Abra um SQL Worksheet e execute:

```sql
SELECT * FROM turistas;

SELECT * FROM colonias;

SELECT * FROM recursos;

SELECT * FROM habitantes;

SELECT * FROM alertas;
```

---

## ⚠️ Atenção sobre IP dinâmico

O IP da VM muda sempre que o deploy é executado novamente.

Antes de conectar:

1. Verifique o IP exibido no resumo final do script
2. Atualize o Hostname no SQL Developer

Caminho:

```text
Botão direito na conexão → Properties
```

Depois atualize o campo:

```text
Hostname
```

---

# 🔐 Passo 4 — Conectar na VM via SSH (Opcional)

```bash
ssh azureuser@IP_DA_VM
```

Senha:

```text
Fiap@Cloud2026
```

---

# 🛠️ Comandos úteis dentro da VM

## 📦 Ver status dos containers

```bash
docker compose ps
```

---

## 📜 Ver logs da aplicação

```bash
docker compose logs -f app-rm564339
```

---

## 🗄️ Ver logs do Oracle

```bash
docker compose logs -f oracle-rm564339
```

---

## 👤 Verificar usuário não-root

```bash
docker exec areslife-api whoami
```

---

## 💾 Ver volumes Docker

```bash
docker volume ls
```

# 🗑️ Passo 5 — Deletar tudo no final

```bash
az group delete --name rg-areslife --yes --no-wait
```

---

## ✅ Verificar se os recursos foram removidos

Após alguns minutos, execute:

```bash
az group list --output table
```

O Resource Group:

```text
rg-areslife
```

não deve mais aparecer na lista.

---

# 📜 Script Azure CLI

O projeto utiliza automação via **Azure CLI** para:

- criação da infraestrutura
- configuração da VM
- instalação do Docker
- deploy da API
- deploy do Oracle Database

---

# 🐳 Dockerfile

A API é executada em container Docker para garantir:

- padronização do ambiente
- facilidade de deploy
- portabilidade
- escalabilidade

---

# 🐳 Docker Compose

O Docker Compose é utilizado para orquestrar:

- API Spring Boot
- Banco Oracle
- Redes Docker
- Volumes persistentes

---

# 📌 Funcionalidades da API

> ⚠️ A API utiliza autenticação JWT. Antes de usar os endpoints abaixo, realize o **registro e login** para obter o token.

---

# 🔐 Autenticação

### Registrar usuário

```http
POST /auth/register
```

```json
{
  "email": "vi@teste.com",
  "senha": "123456"
}
```

### Login (obter token JWT)

```http
POST /auth/login
```

```json
{
  "email": "vi@teste.com",
  "senha": "123456"
}
```

> O token retornado deve ser enviado no header `Authorization: Bearer {token}` nas demais requisições.

---

# ✅ Turistas Espaciais

### Permite

- cadastrar turista com destino (Marte ou Lua) e status
- listar todos os turistas
- buscar turista por ID
- atualizar dados do turista
- deletar turista

### Endpoints

```http
GET    /api/turistas
GET    /api/turistas/{id}
POST   /api/turistas
PUT    /api/turistas/{id}
DELETE /api/turistas/{id}
```

### Exemplo de body (POST/PUT)

```json
{
  "nome": "Marina Magalhães",
  "idade": 19,
  "pais": "Brasil",
  "destino": "Marte",
  "status": "Em treinamento"
}
```

> Destinos válidos: `Marte`, `Lua` — idade mínima obrigatória.

---

# ✅ Colônias

### Permite

- criar colônias em Marte ou Lua com capacidade máxima
- listar e buscar colônias
- atualizar dados da colônia
- deletar colônia
- **simular consumo de recursos de um dia**

### Endpoints

```http
GET    /api/colonias
GET    /api/colonias/{id}
POST   /api/colonias
PUT    /api/colonias/{id}
DELETE /api/colonias/{id}
POST   /api/colonias/{id}/simular-dia
```

### Exemplo de body (POST/PUT)

```json
{
  "nome": "Base Alpha",
  "localizacao": "MARTE",
  "capacidadeMax": 50,
  "dataFundacao": "2035-03-15",
  "descricao": "Primeira base humana permanente em Marte"
}
```

> Localizações válidas: `MARTE`, `LUA`

---

# ✅ Recursos Críticos

### Permite

- criar recursos (OXIGENIO, AGUA, ENERGIA, ALIMENTACAO) vinculados a uma colônia
- listar e buscar recursos por colônia
- abastecer recurso com quantidade específica
- calcular autonomia do recurso com base no consumo histórico
- deletar recurso

### Endpoints

```http
GET    /api/colonias/{coloniaId}/recursos
GET    /api/colonias/{coloniaId}/recursos/{recursoId}
POST   /api/colonias/{coloniaId}/recursos
POST   /api/colonias/{coloniaId}/recursos/{recursoId}/abastecer
GET    /api/colonias/{coloniaId}/recursos/{recursoId}/autonomia?tipo=OXIGENIO
DELETE /api/colonias/{coloniaId}/recursos/{recursoId}
```

### Exemplo de body (POST recurso)

```json
{
  "coloniaId": 1,
  "tipoRecurso": "OXIGENIO",
  "quantidade": 500.00,
  "unidade": "kg",
  "nivelCritico": 50.00,
  "nivelMaximo": 1000.00
}
```

> Tipos válidos: `OXIGENIO`, `AGUA`, `ENERGIA`, `ALIMENTACAO`

---

# ✅ Habitantes (Astronautas e Turistas na Colônia)

### Permite

- criar astronauta ou turista dentro de uma colônia
- listar todos os habitantes ou filtrar por colônia e tipo
- buscar habitante por ID
- registrar saída do habitante
- transferir habitante para outra colônia
- deletar habitante

### Endpoints

```http
GET    /api/habitantes
GET    /api/habitantes?coloniaId={id}
GET    /api/habitantes?coloniaId={id}&tipo=TURISTA
GET    /api/habitantes?coloniaId={id}&tipo=ASTRONAUTA
GET    /api/habitantes/{id}
POST   /api/habitantes/astronautas
POST   /api/habitantes/turistas
PATCH  /api/habitantes/{id}/saida
PATCH  /api/habitantes/{id}/transferir?coloniaDestinoId={id}
DELETE /api/habitantes/{id}
```

### Exemplo de body (POST astronauta)

```json
{
  "coloniaId": 1,
  "nome": "Neil Armstrong Jr",
  "nacionalidade": "Americana",
  "dataChegada": "2035-06-01",
  "especialidade": "Geologia Marciana",
  "missaoAtual": "Exploração do Vale Marineris"
}
```

### Exemplo de body (POST turista habitante)

```json
{
  "coloniaId": 1,
  "nome": "Yuki Tanaka",
  "nacionalidade": "Japonesa",
  "dataChegada": "2035-07-15",
  "agenciaTurismo": "SpaceX Travel",
  "pacoteSelecionado": "VIP"
}
```

---

# ✅ Saúde dos Habitantes

### Permite

- registrar sinais vitais do habitante (gera alerta automático se crítico)
- consultar histórico de saúde paginado
- consultar último registro de saúde

### Endpoints

```http
GET  /api/habitantes/{id}/saude?page=0&size=10
GET  /api/habitantes/{id}/saude/ultimo
POST /api/habitantes/{id}/saude
```

### Exemplo de body (POST sinais vitais)

```json
{
  "pressaoArterial": "120/80",
  "frequenciaCardiaca": 72,
  "saturacaoOxigenio": 98.0,
  "temperaturaCorporal": 36.5,
  "observacoes": "Checkup de rotina - todos os parâmetros normais"
}
```

> ⚠️ Sinais vitais críticos (ex: saturação abaixo de 90, temperatura acima de 40) **geram alertas automáticos**.

---

# ✅ Treinamentos

### Permite

- criar e gerenciar treinamentos
- listar treinamentos por habitante
- concluir treinamento (necessário antes de reservar viagem)

### Endpoints

```http
GET    /api/treinamentos
GET    /api/treinamentos/{id}
POST   /api/treinamentos
PUT    /api/treinamentos/{id}
DELETE /api/treinamentos/{id}
```

---

# ✅ Viagens Turísticas

### Permite

- reservar viagem com pacote BASICO, PREMIUM ou VIP
- listar viagens por habitante ou status
- iniciar, concluir e cancelar viagens

### Endpoints

```http
GET   /api/viagens
GET   /api/viagens?habitanteId={id}
GET   /api/viagens?status=RESERVADA
GET   /api/viagens/{id}
POST  /api/viagens
PATCH /api/viagens/{id}/iniciar
PATCH /api/viagens/{id}/concluir
PATCH /api/viagens/{id}/cancelar
```

### Exemplo de body (POST reserva)

```json
{
  "habitanteId": 7,
  "coloniaId": 1,
  "dataPartida": "2035-09-01",
  "dataRetorno": "2035-09-15",
  "pacote": "BASICO"
}
```

> Pacotes válidos: `BASICO`, `PREMIUM`, `VIP`  
> ⚠️ O habitante precisa ter treinamento concluído para reservar viagem.

---

# ✅ Alertas

### Permite

- listar todos os alertas ou filtrar por severidade e status
- buscar alerta por ID
- resolver alerta individual com observação
- resolver alertas em lote por tipo para uma colônia

### Endpoints

```http
GET   /api/alertas
GET   /api/alertas?severidade=CRITICA&status=ABERTO
GET   /api/alertas/{id}
PATCH /api/alertas/{id}/resolver
PATCH /api/alertas/colonias/{coloniaId}/resolver-lote?tipoAlerta=RECURSO_CRITICO
```

### Exemplo de body (PATCH resolver)

```json
{
  "observacao": "Recurso reabastecido via cápsula de suprimentos. Nível normalizado."
}
```

> Severidades: `CRITICA`, `ALTA`, `MEDIA`, `BAIXA`  
> Status: `ABERTO`, `RESOLVIDO`

---

# ⭐ Diferenciais da API

# ✅ Alertas Automáticos por Sinais Vitais

Ao registrar sinais vitais críticos de um habitante, o sistema dispara automaticamente um alerta sem necessidade de intervenção humana.

### Endpoint

```http
POST /api/habitantes/{id}/saude
```

---

# ✅ Simulação de Dia na Colônia

O sistema simula o consumo diário de todos os recursos de uma colônia, atualizando os estoques e gerando alertas caso algum recurso atinja o nível crítico.

### Endpoint

```http
POST /api/colonias/{id}/simular-dia
```

---

# ✅ Cálculo de Autonomia de Recursos

O sistema calcula automaticamente por quantos dias o estoque atual de um recurso durará com base no histórico de consumo.

### Endpoint

```http
GET /api/colonias/{coloniaId}/recursos/{recursoId}/autonomia?tipo=OXIGENIO
```

---

# ✅ Operação Autônoma (Marte)

Em Marte, com latência de 3 a 22 minutos com a Terra, o sistema opera de forma completamente autônoma:

- alertas disparados localmente sem comunicação com a Terra
- simulação de consumo e projeção de escassez processadas no edge
- histórico armazenado localmente com sincronização posterior

---

# 📂 Estrutura do Projeto

```text
src/main/java/com/fiap/areslife

├── controller
├── service
├── repository
├── dto
├── entity
├── exception
└── config
```

---

# 🚀 Objetivo Final

O objetivo do **AresLife** é ser a espinha dorsal digital que tornará possível a colonização sustentável e o turismo espacial em escala comercial, através de:

- inteligência operacional em tempo real
- automação e alertas proativos
- monitoramento contínuo e autônomo
- centralização de dados de colônias, recursos e habitantes
- experiência digital moderna alinhada às missões NASA Artemis · SpaceX Starship · ESA Moon Village

---
