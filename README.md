# Huseyin_Arda_Davulcu_QA_Task_2

QA Engineer - Selenium Task 2

### Description

It is a project created to perform automation tests requested through the Insider site.

### Features
- **UI Automation**: All requirements were met in accordance with the POM structure using Selenium WebDriver.
- **Configuration**: Developments outside the project requirements can be turned on or off via the configuration file.
- **Screenshot**: At the end of each test, a screenshot was taken and the test result was written to the file name.

### Project Structure
```
Huseyin_Arda_Davulcu_QA_Task_2/
├── pom.xml                          
├── README.md                        
├── src/
│   ├── main/java/insider/
│   │   ├── base/                  
│   │   │   ├── BasePage.java
│   │   ├── pages/                   
│   │   │   ├── ApplicationPage.java
│   │   │   ├── CareersPage.java        
│   │   │   ├── HomePage.java      
│   │   │   ├── JobsPage.java     
│   │   │   └── QaPage.java      
│   │   ├── resources/                   
│   │   │   └── config.properties            
│   └── test/java/tests/
│       ├── BaseTest.java  
│       ├── CareerTest.java                      
│       ├── HomeTest.java   
│       ├── JobsTest.java
│       └── QaTest.java      
└── test-output/
    └── screenshots/ 
```

### System Requirements

Tests were performed with the following versions.

- **Java**: JDK 21
- **Maven**: 3.9.11
- **Chrome Browser**: 142.0.7444.176 

### Check Versions

1. **Check Java Version**
   ```bash
   java -version
   javac -version
   ```

2. **Check Maven Version**
   ```bash
   mvn -version
   ```

## Configuration

The `src/main/java/insider/resources/config.properties` file can be edited to configure test settings such as browser and screenshot options.



## Run Tests

```bash
# Clone the repository
git clone <repository-url>
cd Huseyin_Arda_Davulcu_QA_Task_2

# Install dependencies
mvn clean install

# Run tests
mvn test
```

## What Can Be Added

Logging and reporting mechanisms can be established.

## Authors

Hüseyin Arda Davulcu - h.arda_1034@gmail.com
