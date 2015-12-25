[![Build Status](https://travis-ci.org/mad4j/Access-Wrapper.svg?branch=master)](https://travis-ci.org/mad4j/Access-Wrapper)

![Access-Wrapper](resources/logo.png)
Java convenience class to access private fields and methods using reflection.

# Breaking the rules
Sometimes it may be useful to access the internal state (fields) or behavior (methods) of an object. In this case, it is not needed to break overall design in order to expose internal mechanisms. Reflection may be used to derogate design rules in a controlled manner.

This a didactical example on how use Java reflection, but suggestions and contributions are welcome.

## See also:
* [Spring ReflectionTestUtils](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/test/util/ReflectionTestUtils.html)
* [PowerMock WhiteBox](https://github.com/jayway/powermock)

# Accesing private fields

The following class contains a private field (obviously, it is a trivial example).

```java
public class Dummy {
  private int value = 10;
}
```

By the means of `AccessWrapper` class it is possible to read and modify the value of private fields without the need to violate the original design.

## Setup
Create an a new `AccessWrapper` instance that wraps an instance of `Dummy` class

```java
  Dummy d = new Dummy();
  AccessWrapper w = new AccessWrapper(d);
```  

## Read values  
Read `Dummy` internal state using `get()` method:

```java
  System.out.println(w.get("value"));
  // output: 10
```

## Modify values
Write a new value using `set()` method:

```java
  w.set("value", 100);
  System.out.println(w.get("value"));
  // output: 100
```

## Using straightforward reflection
Here an example on how access to private fields using straightforward reflection

```java
int intValue = -1;
String stringValue = null;
		
Field f = null;
try {
	
	f = d.getClass().getDeclaredField("intField");
	f.setAccessible(true);
	
	intValue = (int) f.get(d);
	
	f = d.getClass().getDeclaredField("stringField");
	f.setAccessible(true);
	
	stringValue = (String) f.get(d);
	
} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
	e.printStackTrace();
}

System.out.println(intValue);
System.out.println(stringValue);
```

# Accesing private methods

The following class contains a private method (obviously, it is a trivial example).

```java
public class Dummy {
  private int inc(int value) {
    return value++;
  }
}
```

## Setup
Create an a new `AccessWrapper` instance that wraps an instance of `Dummy` class

```java
  Dummy d = new Dummy();
  AccessWrapper w = new AccessWrapper(d);
```  

## Calling internal behavior
Call private methods using `invoke()`:

```java
  int value = w.invoke("inc", 10);
  System.out.println(value);
  // output: 11
```

## Accessing private constructors

The following class contains a private constructor (obviously, it is a trivial example).

```java
public class Dummy {

  private int value;

  private Dummy(int value) {
    this.value = value;
  }
}
```

## Setup

Create a new instance of `Dummy` class using `AccessWrapper` facility.

```java
  Dummy d = null;
  d = AccessWrapper.create(Dummy.class, 100);
  assertNotNull(d);
```
