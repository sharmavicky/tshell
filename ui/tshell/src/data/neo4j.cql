USING PERIODIC COMMIT 
LOAD CSV with headers from "" as row
create(:NeoSkill {id: row.sk_id, name: row.sk_name, description: row.sk_description, createdOn: row.sk_creation_date, active: row.sk_active, image:row.sk_image});

USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM "file:///topics.csv" AS row
CREATE (:Topic {name: row.tp_name, id: row.tp_id});

CREATE (C:NeoSkill {name:'C'})
CREATE (Cplusplus:NeoSkill {name:'C++'})
CREATE (HTML:NeoSkill {name:'HTML'})
CREATE (CSS:NeoSkill {name:'CSS'})
CREATE (JS:NeoSkill {name:'JavaScript'})
CREATE (TypeSkript:NeoSkill {name:'TypeSkript'})
CREATE (MySQL:NeoSkill {name:'MySQL'})
CREATE (MySQL_Workbench:NeoSkill {name:'MySQL_Workbench'})
CREATE (Spring:NeoSkill {name:'Spring'})
CREATE (SpringBoot:NeoSkill {name:'SpringBoot'})
CREATE (SpringMVC:NeoSkill {name:'SpringMVC'})

CREATE (MSOffice:NeoSkill {name:'Microsoft Office'})
CREATE (MSWord:NeoSkill {name:'Microsoft Office Word'})
CREATE (MSExcel:NeoSkill {name:'Microsoft Office Excel'})
CREATE (MSPowerPoint:NeoSkill {name:'Microsoft Office Powerpoint'})
CREATE (MSAccess:NeoSkill {name:'Microsoft Office Access'})
CREATE (MSPaint:NeoSkill {name:'Microsoft Office Paint'})


CREATE
  (MSOffice)-[:REQUIRED_KNOWLEDGE_OF {skillRequiredRelationships:['MSOffice']}]->(MSAccess),
  (MSOffice)-[:REQUIRED_KNOWLEDGE_OF {skillRequiredRelationships:['MSOffice']}]->(MSWord),
  (MSOffice)-[:REQUIRED_KNOWLEDGE_OF {skillRequiredRelationships:['MSOffice']}]->(MSExcel),
  (MSOffice)-[:REQUIRED_KNOWLEDGE_OF {skillRequiredRelationships:['MSOffice']}]->(MSPaint),
  (MSOffice)-[:REQUIRED_KNOWLEDGE_OF {skillRequiredRelationships:['MSOffice']}]->(MSPowerPoint),
  (CSS)-[:REQUIRED_KNOWLEDGE_OF {skillRequiredRelationships:['CSS']}]->(HTML),
  (JS)-[:REQUIRED_KNOWLEDGE_OF {skillRequiredRelationships:['JS']}]->(HTML),
  (TypeSkript)-[:REQUIRED_KNOWLEDGE_OF {skillRequiredRelationships:['TypeSkript']}]->(JS),
  (SpringBoot)-[:REQUIRED_KNOWLEDGE_OF {skillRequiredRelationships:['SpringBoot']}]->(Java),
  (SpringBoot)-[:REQUIRED_KNOWLEDGE_OF {skillRequiredRelationships:['SpringBoot']}]->(Spring),
  (SpringMVC)-[:REQUIRED_KNOWLEDGE_OF {skillRequiredRelationships:['SpringMVC']}]->(Java),
  (SpringMVC)-[:REQUIRED_KNOWLEDGE_OF {skillRequiredRelationships:['SpringMVC']}]->(Spring),
  (SpringMVC)-[:REQUIRED_KNOWLEDGE_OF {skillRequiredRelationships:['SpringMVC']}]->(SpringBoot),
  (MySQL_Workbench)-[:REQUIRED_KNOWLEDGE_OF {skillRequiredRelationships:['MySQL_Workbench']}]->(MySQL)

CREATE (Java:NeoSkill {name:"Java"})
CREATE (Java)-[:REQUIRED_KNOWLEDGE_OF {skillRequiredRelationships:["Java"]}]->(C)



// To create node with relationship with existing
MATCH(u:NeoSkill {name:'Adil'})
CREATE(p:NeoSkill {name:'Vishal'})
CREATE(p)-[w:ClassMate]->(u)
return w;

//Create Relationship between existing nodes
MATCH (u:NeoSkill {name:'C'}), (r:NeoSkill {name:'C++'})
CREATE (u)-[w:HAS_ROLE]->(r)
return u,w,r;
//Delete Relationships
MATCH (n { name: 'C' })-[r:HAS_ROLE]->()
DELETE r

//Delate a Node and return that node (If it has no relationship)
MATCH(j:NeoSkill) where j.name = "Vishal" 
WITH j,  j.name AS name
DELETE j
RETURN j, name

//Delete only node
MATCH (n:NeoSkill { name: 'Vishal' })
DELETE n

//Delete node and all relationship
MATCH (n { name: 'Adil' })
DETACH DELETE n

//Delete Node and relationship if it has any
MATCH (m:NeoSkill {name:"Vishal"})
OPTIONAL MATCH (m)-[r]-() 
DELETE m, r
return m, r



