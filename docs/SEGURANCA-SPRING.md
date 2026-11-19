# Segurança do PI — Spring Security e Validation

## Descrição geral

Foi integrada uma camada de segurança stateless à API do ImobFiscal. O login confere a senha
armazenada com BCrypt e devolve um JWT assinado. Todas as rotas `/api/**`, com exceção de login,
cadastro e health check, exigem o cabeçalho `Authorization: Bearer <token>`.

O JWT contém o e-mail do usuário, o identificador do usuário, seu perfil e o identificador da
imobiliária. O filtro de segurança valida assinatura e expiração em cada requisição, recarrega o
usuário do banco e impede que o identificador da imobiliária presente na URL seja diferente do
tenant registrado no token. Requisições sem autenticação recebem `401`; tentativas de acessar
outra imobiliária recebem `403`.

Os DTOs de entrada utilizam Jakarta Bean Validation. Os controllers aplicam `@Valid` e o
`GlobalExceptionHandler` devolve `400` com uma mensagem geral e os erros por campo.

## Recursos utilizados

- Spring Boot 3.3 e Java 17.
- `spring-boot-starter-security`: cadeia de filtros, autorização e contexto autenticado.
- BCrypt por meio do starter de segurança.
- JJWT 0.12.6 (`jjwt-api`, `jjwt-impl` e `jjwt-jackson`): criação e validação dos JWTs.
- `spring-boot-starter-validation`: Jakarta Validation (`@Valid`, `@NotBlank`, `@Email`,
  `@Pattern`, `@PositiveOrZero`, `@DecimalMin`, entre outras).
- `spring-security-test` e JUnit 5: suporte aos testes automatizados.

## Configuração

Em produção, configure obrigatoriamente uma chave exclusiva com pelo menos 32 caracteres:

```text
JWT_SECRET=<chave longa, aleatória e exclusiva do ambiente>
JWT_EXPIRATION_MS=28800000
```

O tempo padrão é de 8 horas. A chave de desenvolvimento presente em `application.properties`
serve somente para execução local e não deve ser usada em produção.

## Fluxo implementado

1. O cliente envia e-mail e senha para `POST /api/auth/login`.
2. A senha é conferida usando BCrypt.
3. A API gera um JWT com expiração, perfil e imobiliária.
4. O frontend envia o token em todas as chamadas posteriores.
5. O filtro valida o JWT e cria a autenticação do Spring Security.
6. A rota só prossegue se o usuário estiver autenticado e pertencer à imobiliária solicitada.

## Módulos que ainda devem ser validados

- Regras brasileiras completas para CPF e CNPJ, além do tamanho e formato já verificados.
- Coerência entre datas de início/fim, prazo e vencimento dos contratos.
- Transições permitidas de status de contrato, boleto e nota fiscal.
- Perfis e permissões por operação, por exemplo somente `ADMIN` excluir registros e somente
  `FINANCEIRO` emitir boletos/notas.
- Bloqueio ou revogação de tokens após logout, troca de senha ou desativação do usuário.
- Rate limiting e bloqueio temporário após várias tentativas de login.
- Confirmação de e-mail, recuperação de senha e política de senha mais forte.
- Ampliar a auditoria atual para registrar também tentativas de acesso negadas.
- Cadastro administrativo de funcionários e aplicação efetiva das permissões de cada perfil.
- Eliminação futura do tenant padrão usado apenas na tela pública de cadastro; após o login, as
  chamadas protegidas já utilizam a imobiliária retornada pela API.
- Restrições de unicidade por imobiliária para códigos de imóveis e documentos.

## Testes

Execute no diretório `backend`:

```bash
mvn test
```

Os testes cobrem geração/validação de JWT, presença de tenant e perfil no token, recusa de chave
fraca, validações de DTOs e o fluxo HTTP completo com banco H2 temporário: rota pública, `401`
sem token, login, acesso autorizado ao próprio tenant, `403` entre tenants e erros por campo.
