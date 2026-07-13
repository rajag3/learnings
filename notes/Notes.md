# Learning Notes

## Spring Boot

### Create new Spring Boot application
```bash
mvn archetype:generate -DgroupId=com.h2 -DartifactId=book-search -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

### Run the application
```bash
cd book-search
mvn spring-boot:run
```

### Build the application
```bash
mvn clean package
```

### Test the application
```bash
curl http://localhost:8080/hello
```

---

## Docker

### Start PostgreSQL container
```bash
docker-compose up -d
```

### List running containers
```bash
docker ps
```

### Connect to PostgreSQL database
```bash
docker exec -it library-db psql -U admin -d library
```
- `-i` → interactive mode
- `-T` → terminal mode

---

## CompletableFuture Exception Handling

In multi-threaded environments, exceptions thrown in background threads may not be visible to the main thread or propagate to it.

Exceptions are wrapped in `CompletionException`. To handle them properly, use the following pipeline methods:

1. **exceptionally()** - Acts like a catch block, used to log errors and provide fallback values so the pipeline can continue
2. **handle()** - Acts like a finally block, always executes, giving access to both success (if any) and failure/exception (if any) cases
3. **whenComplete()** - Used mainly for logging/cleanup operations; unlike handle, it doesn't allow you to modify the result

---

## Additional Notes

*Add new topics here as you learn them*
