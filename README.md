[TOC]

# 🔦Introduce

[SnowFlake](https://github.com/HEXDude/Snowflake) is an implementation of [@Twitter's SnowFlake algorithm](https://blog.twitter.com/engineering/en_us/a/2010/announcing-snowflake) in Java with [Spring-Boot](https://spring.io/projects/[Spring-Boot](https://spring.io/projects/spring-boot)) framework.



# 🏳‍🌈Capability

This project will offers primary key generating strategy for [ORM](https://en.wikipedia.org/wiki/Object%E2%80%93relational_mapping) framework such as [JPA](https://spring.io/projects/spring-data-jpa)(Hibernate) , [Mybatis-Plus](https://baomidou.com/)

and more to develop. 

**Its' generated number is  linear and increasing, Makes higher data-base index performance, And the sequence is**

**generated base on time, So it is easy to trace when you want to.**

**You are welcomed to contribute for this project to dig more fun stuff✨✨✨**.

It is now operate successfully and compatible with [JPA](https://spring.io/projects/spring-data-jpa) and [Mybatis-Plus](https://baomidou.com/) to integrate in [Spring-Boot](https://spring.io/projects/spring-boot) framework.



# 🚀Get start

## 1. Integrate

### 		a. Manual integrate

😁

1. Download the latest package from the right(maybe...) on this page.
2. Drag the downloaded jar into your projects' root library.

###		 	b. Integrate to [Spring-Boot](https://spring.io/projects/spring-boot) with [Maven](https://maven.apache.org)

😅Sorry I currently does not find a appropriate to upload my project to [Maven](https://maven.apache.org) central repository, I'm working on it!!!

###		 	c. Integrate to [Spring-Boot](https://spring.io/projects/spring-boot) with [Gradle](https://gradle.org/)

🤣Same reason as last one.





## 2. Configuration

This project offers multiple customization configuration syntax.

All these attributes you can customized  its value from application[*].properties.

|             NAME             | DEFAULT VALUE | ACCEPTABLE VALUE |                         DESCRIPTION                          |
| :--------------------------: | :-----------: | :--------------: | :----------------------------------------------------------: |
| hex.snowflake.data-center-id |       0       |       0~31       | This attribute  reveals reference services' identifier in entire micro-service system. |
|   hex.snowflake.worker-id    |       0       |       0~31       | This attribute  reveals reference services' deployed server or data-center identifier. |
|   hex.snowflake.time-stamp   | 1645539742000 |  UNIX Timestamp  | [**Not recommend to modify**]Generated number sequence will based on this time. |
|    hex.snowflake.sequence    |       0       |      0~4095      | [**Not recommend to modify**]Generated number sequence's identifier in 1 million-second. |
|   hex.snowflake.time-stamp   |      -1       |  UNIX Timestamp  | [**Not recommend to modify**]Record the timestamp of last successfully generated. |



## 3. In Project

After refer this package to your own project and configuration. You still need some small step to start.

1. For [JPA](https://spring.io/projects/spring-data-jpa)

   Your domain will be like:

   ```java
   @Entity
   @Data
   @NoArgsConstructor
   @AllArgsConstructor
   @Builder
   public class User extends AbstractEntity {
       
       @Id
       @GeneratedValue(generator = "hexGen")
       //Declarition below 'strategy' must be strict same sa this.
       @GenericGenerator(name = "hexGen", strategy = "com.github.hexdude.generator.[JPA](https://spring.io/projects/spring-data-jpa)Generator")
       private String id;
       
       private String firstName;
   
       private String secondName;
       
       @Enumerated(EnumType.STRING)
       private Gender gender;
   }
   
   ```

   Then it is complete!

2. For [Mybatis-Plus](https://baomidou.com/)

   Your domain will be like:

   ```java
   @TableName("user")
   public class User {
   
       @Id
       @TableId(type = IdType.ASSIGN_ID)
       private Long id;
   }
   ```

   

## 🧲Now you are good to go!

# 🐛Known problem

- [ ] [Critical](https://github.com/HEXDude/Snowflake/issues/5)Number sequence will be repeat once system has back in time. 
- [ ] Bad performance on higher concurrency situation.