# Add bean to Spring context using stereotype annotation

> This project is based on chapter **2.2.2. Using stereotype annotations to add beans to the Spring context** from book **Spring Starts here (2021)** by Laurentiu Spilca.

## Create Maven project with Intellij Idea

File > New project > Java

## Add Spring Context dependency

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>6.1.10</version>
</dependency>
```

## Create entity

```java
public class Book {
    private final String title;

    public Book() {
        this.title = "One Hundred Years of Solitude";
    }

    public String getTitle() {
        return title;
    }
}
```

## Mark entity with @Component annotation

```diff
+ @Component
public class Book {
    private final String title;

    public Book() {
        this.title = "One Hundred Years of Solitude";
    }

    public String getTitle() {
        return title;
    }
}
```

## Create configuration class

```java
@Configuration
public class ApplicationConfiguration {
}
```

## Configure component scan in configuration class

```diff
@Configuration
+ @ComponentScan(basePackages = "org.example")
public class ApplicationConfiguration {
}
```

## Create Spring context

```java
ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
```

## Get bean from Spring context

```java
Book book = context.getBean(Book.class);
```

## Add tests

### Add dependency for Spring TestContext Framework

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>6.1.10</version>
    <scope>test</scope>
</dependency>
```

### Add dependency for JUnit

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.11.0-M2</version>
    <scope>test</scope>
</dependency>
```

### Create test to check that application context is created

```java
public class ApplicationTests {

    @Test
    @DisplayName("Checks that Application Context is created")
    public void checkApplicationContextCreated() {
        ApplicationContext context = new AnnotationConfigApplicationContext();

        assertNotNull(context);
    }
}
```

### Create test to check that book is added to Spring context

- use `@ExtendWith(SpringExtension.class)` to integrate Spring TestContext Framework to the test
- use `@ContextConfiguration` to configure Spring context in Spring integration tests

```java
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfiguration.class })
public class BookTests {

    @Autowired
    private Book book;

    @Test
    @DisplayName("Fetch the book bean from the context")
    public void fetchBookBean() {
        String actualTitle = book.getTitle();
        String expectedTitle = "One Hundred Years of Solitude";

        assertEquals(actualTitle, expectedTitle);
    }
}
```
