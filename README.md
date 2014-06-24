ServicesPool
============

Manage the SQL like services.

When working with databased related project, the most jobs we facing is writing SQL to implement functions. 
How to manage the these SQLs? 

The Services Pool project is created to make this job easier. 

# The project logic
1. Create a new type of SQL (Called Service SQL)which allows to input parameters when the SQL is called. 
	For example: Select s.id, s.name, s.class from students s where s.id=:id;
	The ":id" will be replaced by the value which comes from the SQL called.
2. The Service SQL was writen in a XML file which organizes the relationship between the Service SQLs.
	The structure of the file shows as bellow. 
	<?xml version="1.0" encoding="UTF-8"?>
	<ServicesPool>
		<services namescope="">
			<Service name="StudentInfo">
				<SQL>
					SELECT S.ID, S.NAME, S.CLASS FROM SUTDENT S WHERE S.ID=:ID [AND S.NAME=:NAME];
				</SQL>
				<param id="ID" value="" default="1" />	
				<Param id="NAME" value="" default="" />
			</Service>
			<Service>
				...
			</Service>
		</services>
		<services namescope="" >
			<Service>
				...	
			</Service>
			<Service>
				...
			</Service>
		</services>
	</ServicesPool>


