# Access-Wrapper
Java convenience class to access private fields and methods using reflection.

## Breaking the rules
Sometimes it may be useful to access the internal state (fields) or behavior (methods) of an object. In this case, it is not needed to break overall design in order to expose internal mechanisms. Reflection may be used to derogate design rules in a controlled manner.

This a didactical example on how use Java reflection, but suggestions and contributions are welcome.


### Accesing private fields

The following class contains a private field (obviously, it is a trivial example).

```java
public class Dummy {
  private int value = 10;
}
```

By the means of `AccessWrapper` class it is possible to read and modify the value of private fields without the need to violate the original design.

#### Setup
Create an a new `AccessWrapper` instance that wraps an instance of `Dummy` class

```java
  Dummy d = new Dummy();
  AccessWrapper w = new AccessWrapper(d);
```  

#### Read values  
Read `Dummy` internal state using `get()` method:

```java
  System.out.println(w.get("value"));
  // output: 10
```

#### Modify values
Write a new value using `set()` method:

```java
  w.set("value", 100);
  System.out.println(w.get("value"));
  // output: 100
```


### Accesing private methods

The following class contains a private method (obviously, it is a trivial example).

```java
public class Dummy {
  private int inc(int value) {
    return value++;
  }
}
```

#### Setup
Create an a new `AccessWrapper` instance that wraps an instance of `Dummy` class

```java
  Dummy d = new Dummy();
  AccessWrapper w = new AccessWrapper(d);
```  

#### Calling internal behavior
Call private methods using `invoke()`:

```java
  int value = w.invoke("inc", 10);
  System.out.println(value);
  // output: 11
```
