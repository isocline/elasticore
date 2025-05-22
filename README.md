# ElastiCORE

[![Version](https://img.shields.io/badge/version-1.6.7--SNAPSHOT-blue.svg)](https://github.com/isocline/elasticore)
[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)

> A powerful DSL-based development framework that generates code from YAML model definitions

ElastiCORE is a modern Java framework that enables developers to define entities, services, and APIs using a simple YAML-based Domain Specific Language (DSL). It automatically generates boilerplate code, reduces development time, and ensures consistency across your application.

## ✨ Key Features

- **📝 YAML-based DSL**: Define your domain models using intuitive YAML syntax
- **🔄 Code Generation**: Automatic generation of entities, DTOs, and service interfaces
- **🌐 Protocol Buffers Support**: Built-in gRPC service definitions
- **🍃 Spring Boot Integration**: Seamless integration with Spring Boot 3.x
- **🗄️ JPA Support**: Automatic JPA entity generation with relationships
- **🏗️ Modular Architecture**: Clean separation of concerns with multiple modules
- **⚡ Annotation Processing**: Compile-time code generation for better performance

## 🏗️ Architecture

ElastiCORE consists of several modules working together:

```
elasticore/
├── elasticore-base/          # Core framework components
│   ├── annotation/           # Custom annotations (@ElastiCORE, etc.)
│   ├── base/                 # Core classes and interfaces
│   ├── processor/            # Annotation processors
│   └── runtime/              # Runtime components
├── elasticore-template/      # DSL templates and examples
│   ├── blueprint/            # YAML model definitions
│   └── proto/                # Protocol Buffer definitions
├── elasticore-springboot3/   # Spring Boot 3 integration
├── elasticore-jpa/          # JPA integration (coming soon)
└── elasticore-schema/       # Schema validation (coming soon)
```

## 🚀 Quick Start

### Prerequisites

- Java 17 or higher
- Gradle 7.x or higher

### Installation

Add the following to your `build.gradle`:

```gradle
dependencies {
    implementation 'io.elasticore:elasticore-base:1.6.7-SNAPSHOT'
    implementation 'io.elasticore:elasticore-springboot3:1.6.7-SNAPSHOT'
    annotationProcessor 'io.elasticore:elasticore-base:1.6.7-SNAPSHOT'
}
```

### Basic Usage

1. **Define your domain model** in YAML:

```yaml
# src/main/resources/blueprint/model/car.yml
entity:
  CarModel:
    meta: entity @expose
    fields:
      id: string(36)! @id          # UUID primary key
      name: string(100)!           # Model name
      brand: string(50)!           # Brand name
      img: string(255)             # Image URL
      catalog: Catalog @           # Reference to catalog

  Catalog:
    meta: entity @expose
    fields:
      catalogId: string(30)! @id   # Catalog unique ID
      name: string(100)!           # Catalog name
```

2. **Add the annotation** to your configuration class:

```java
@ElastiCORE
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

3. **Define service interfaces** using Protocol Buffers:

```protobuf
// src/main/proto/CarService.proto
syntax = "proto3";

option java_package = "io.elasticore.blueprint.proto";

service CarInfoPortService {
  rpc findByBrand (CarInfoInput) returns (CarInfoOutput);
}

message CarInfoInput {
  string brand = 1;
}

message CarInfoOutput {
  string brand = 1;
  string description = 2;
  string modelId = 3;
}
```

4. **Build your project**:

```bash
./gradlew build
```

ElastiCORE will automatically generate:
- JPA entities with proper annotations
- DTO classes for data transfer
- Service interfaces
- Repository interfaces
- Controller stubs (optional)

## 📖 DSL Reference

### Entity Definition

```yaml
entity:
  EntityName:
    meta: entity @expose @audited    # Metadata and annotations
    fields:
      id: string(36)! @id            # Required field with ID annotation
      name: string(100)!             # Required string with max length
      description: string(500)       # Optional field
      price: decimal(10,2)           # Decimal with precision
      active: boolean @default(true) # Boolean with default value
      created: datetime @audited     # Timestamp field
      tags: List<Tag> @cascade(ALL)  # One-to-many relationship
```

### Supported Field Types

| Type | Description | Example |
|------|-------------|---------|
| `string(n)` | String with max length | `string(100)` |
| `int(n)` | Integer with max digits | `int(10)` |
| `decimal(p,s)` | Decimal with precision | `decimal(10,2)` |
| `boolean` | Boolean field | `boolean` |
| `datetime` | Timestamp field | `datetime` |
| `List<Type>` | Collection field | `List<Tag>` |

### Annotations

| Annotation | Description |
|------------|-------------|
| `@id` | Primary key field |
| `@expose` | Include in REST API |
| `@audited` | Enable audit logging |
| `@default(value)` | Set default value |
| `@cascade(type)` | JPA cascade type |
| `@ref(Entity.field)` | Foreign key reference |

## 🛠️ Development

### Building from Source

```bash
git clone https://github.com/isocline/elasticore.git
cd elasticore
./gradlew build
```

### Running Tests

```bash
./gradlew test
```

### Publishing to Local Repository

```bash
./gradlew publishToMavenLocal
```

## 📝 Examples

Check out the [examples directory](elasticore-project/elasticore-template/src/main/resources/blueprint/) for complete working examples:

- [Car Parts Management System](elasticore-project/elasticore-template/src/main/resources/blueprint/parts/)
- [BBS Forum System](elasticore-project/elasticore-template/src/main/resources/blueprint/bbs/)
- [Channel Management](elasticore-project/elasticore-template/src/main/resources/blueprint/channel/)

## 🤝 Contributing

We welcome contributions! Please see our [Contributing Guidelines](CONTRIBUTING.md) for details.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## 🔗 Links

- [Documentation](https://github.com/isocline/elasticore/wiki) (coming soon)
- [API Reference](https://github.com/isocline/elasticore/wiki/api) (coming soon)
- [Examples](elasticore-project/elasticore-template/)
- [Issue Tracker](https://github.com/isocline/elasticore/issues)

## 📞 Support

- 📧 Email: support@elasticore.io
- 💬 Discussions: [GitHub Discussions](https://github.com/isocline/elasticore/discussions)
- 🐛 Bug Reports: [GitHub Issues](https://github.com/isocline/elasticore/issues)

## 🎯 Roadmap

- [ ] Complete JPA module implementation
- [ ] Add schema validation module
- [ ] REST API auto-generation
- [ ] GraphQL support
- [ ] CLI tool for project scaffolding
- [ ] IDE plugins (IntelliJ, VSCode)
- [ ] Docker support
- [ ] Kubernetes integration

---

⭐ **Star this repository if you find it useful!**

*Made with ❤️ by the ElastiCORE team*