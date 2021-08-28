# Manager-system
- Create a microservice bases on Java, MySQL and Spring boot framework

## Business needs
- The microservice will be able to perform the following functions:
- The system shall support create and update system settings with the types is: string, float, date, integer, boolean
- The system shall support searching system setting by keys and system setting ids
- The system shall provide the capability to delete a system setting
- The system shall support create system category
- The system shall support add system setting to one or more categories

## Working requirements
Working environment:
- Java 8 or later
- Eclipse IDE or IntelliJ community
- MySQL Database
- Maven Build Tool
- Delivery: Source code, deployment and testing, reviewing evident packaged in a compressed archive

## Product architecture
- The microservices are by nature distributed applications, meaning that they are program that run on more than computer and communicate through a network or server.

## Technologies
The product implements one or more technology:
- Java
- Spring Framework
- Hibernate Framework

## Database Relationship
Base on the analysis of the requirements, we decide to use the following database tables to store the persistent data for our microservices.

![image](https://user-images.githubusercontent.com/51531118/131212888-91e0977d-2da9-4ade-92a0-8eb1dbfb531e.png)


## Assignment Descriptions
1. Create API-CS01 create system settings
	- Method `POST/system-settings`
	- Request

	```Json
	{
		"key": "example",
		"value": "value",
		"type": "string",
		"allowOverride": true,
		"allowValues": ["VALUE1", "VALUE2"],
		"description": "Description of system config",
		"categories": ["category 1", "category 2"]
	}
	```

	- Response

	```Json
	{
		"id" : "1",
		"key": "example",
		"value": "value",
		"type": "string",
		"allowOverride": true,
		"allowValues": ["VALUE1", "VALUE2"],
		"description": "Description of system config",
		"categories": ["category 1", "category 2"]
	}
	```

	- Validate:
		- Key, value is required
		- Type must be one of: string, float, date, integer, boolean
		- The value is must match with the type

	- Business rule:
		- Category must be a exist on the database
		- The key must be unique
		- If system config allows to override, can be set the allow values can be override



2. Create API-CS02 Update system settings
	- Method `PUT/system-settings/{id}`
	- Request

	```Json
	{
		"key": "example",
		"value": "value",
		"type": "string",
		"allowOverride": true,
		"allowValues": ["VALUE1", "VALUE2"],
		"description": "Description of system config",
		"categories": ["category 1", "category 2"]
	}
	```

	- Response

	```Json
	{
		"id" : "1",
		"key": "example",
		"value": "value",
		"type": "string",
		"allowOverride": true,
		"allowValues": ["VALUE1", "VALUE2"],
		"description": "Description of system config",
		"categories": ["category 1", "category 2"]
	}
	```

	- Validate:
		- Key, value is required
		- Type must be one of: string, float, date, integer, boolean
		- The value is must match with the type

	- Business rule:
		- Category must be a exist on the database
		- System settings must be a exist on the database
		- The key must be unique
		- If system config allows to override, can be set the allow values can be override

3. Create API-CS03 Get list system setting by list key and list ID support paging

4. Create API-CS04 Delete system setting by ID
	- Method `DELETE/system-settings/{id}`
	- Request: empty
	- Response: 

	```Json
	{
		"success": true
	}
	```
	- Business Rule:
		- System setting must be a exists on the database

5. Create API-CS05 Create category
	- Method `POST/categories`
	- Request:
	```Json
	{
		"name": "tho",
		"desciption": "abc test"
	}
	```

	- Response: 

	```Json
	{
		"id": "1"
		"name": "category_name",
		"description": "Description of the category"
	}
	```
	- Validation:
		- Name is required
	- Business Rule:
		- Name must be unique


6. Create API-CS06 Delete category
	- Method `DELETE/categories/{id}`
	- Request: empty
	- Response: 

	```Json
	{
		"success": true
	}
	```
	- Business Rule:
		- Category must be a exists on the 



