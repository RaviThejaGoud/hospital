drop database eschool;
create database eschool;
use eschool;
grant all on esdev04122017.* to esdev04122017@localhost identified by 'esdev04122017';

grant all on esprod.* to esprod@localhost identified by 'esprod';

grant all on smsdevlocal.* to smsdevlocal@localhost identified by 'smsdevlocal';

grant all on smsdev1.* to smsdev1@localhost identified by 'smsdev1';

grant all on smsprodnew1.* to smsprodnew1@localhost identified by 'smsprodnew1';

-- List of Roles
delete from Role;
INSERT INTO Role(id,name,description) VALUES
(1,'ROLE_ADMIN','Administrator'),
(2,'ROLE_TEACHER','Teacher'),
(3,'ROLE_STUDENT','Student'),
(4,'ROLE_CLERK','Clerk'),
(5,'ROLE_DRIVER','Driver'),
(6,'ROLE_HELPER','Helper'),
(7,'ROLE_PARENT','Parent'),
(8,'ROLE_HOD','Hod'),
(9,'ROLE_LIBRARIAN','Librarian'),
(10,'ROLE_TRANSPORT','Transport'),
(11,'ROLE_FINANCE','Finance'),
(12,'ROLE_PRINCIPAL','Principal'),
(13,'ROLE_MASTERADMIN','MASTERADMIN'),
(14,'ROLE_HOSTEL','HOSTEL'),
(15,'ROLE_TRANSPORTFINANCE','TransportFinance'),
(16,'ROLE_HOSTELFINANCE','HostelFinance'),
(17,'ROLE_PEON','Peon'),
(18,'ROLE_AYAH','Ayah'),
(19,'ROLE_CONDUCTOR','Conductor'),
(20,'ROLE_SYSTEMADMINISTRATOR','System Administrator'),
(21,'ROLE_LABASST','LabAsst'),
(22,'ROLE_MANAGEMENTTRAINEE','Management Trainee'),
(23,'ROLE_WATCHMAN','Watchman'),
(24,'ROLE_TYPIST','Typist'),
(25,'ROLE_SWEEPER','Sweeper'),
(26,'ROLE_SEO','SEO'),
(27,'ROLE_DEO','DEO'),
(28,'ROLE_MEO','ROLE_MEO'),
(29,'ROLE_INCHARGE','Incharge'),
(30,'ROLE_OTHER','Other'),
(31,'ROLE_VICEPRINCIPAL','Vice Principal'),
(32,'ROLE_ADMINOFFICER','Admin Officer'),
(33,'ROLE_PUBLICRELATIONOFFICER','Public Relation Officer'),
(34,'ROLE_FPS','Fps'),
(35,'ROLE_ASST_TEACHER','Asst Teacher'),
(36,'ROLE_SECRETARY','Secretary'),
(37,'ROLE_SECRETARY_PA','Secretary PA'),
(38,'ROLE_MANAGER','Manager'),
(39,'ROLE_BEO','ROLE_BEO'),
(40,'ROLE_CHAIR_MAN','Chairman'),
(41,'ROLE_MESS_MANAGER','Mess Manager'),
(42,'ROLE_DIRECTOR','Director'),
(43,'ROLE_SALES_HEAD','Sales Head'),
(44,'ROLE_SALES_EXECUTIVE','Sales Executive'),
(45,'ROLE_COORDINATOR','Coordinator'),
(46,'ROLE_BDM','BDM'),
(47,'ROLE_EXECUTIVE_VICE_CHAIRMAN','Executive Vice Chairman'),
(48,'ROLE_VICE_CHAIRMAN','Vice Chairman'),
(49,'ROLE_TREASURER','Treasurer'),
(50,'ROLE_JOINT_SECRETARIES','Joint Secretary'),
(51,'ROLE_EXECUTIVE_MEMBER','Executive Member'),
(52,'ROLE_MEMBER','Member'),
(53,'ROLE_ADMIN_COORDINATOR','Admin Coordinator'),
(54,'ROLE_STOREKEEPER','Store Keeper'),
55,'ROLE_RECEPTIONIST','Receptionist');

--WeekDays
INSERT INTO weekDays(id,dayName) VALUES
(1,'SUNDAY'),
(2,'MONDAY'),
(3,'TUESDAY'),
(4,'WEDNESDAY'),
(5,'THURSDAY'),
(6,'FRIDAY'),
(7,'SATURDAY');

--States
delete from State;
insert into State (id,StateCode,stateName) values(1,'AP','Andhra Pradesh');
insert into State (id,StateCode,stateName) values(2,'AR','Arunachal Pradesh');
insert into State (id,StateCode,stateName) values(3,'AS','Assam');
insert into State (id,StateCode,stateName) values(4,'BR','Bihar');
insert into State (id,StateCode,stateName) values(5,'CG','Chattisgarh');
insert into State (id,StateCode,stateName) values(6,'GA','Goa');
insert into State (id,StateCode,stateName) values(7,'GJ','Gujarat');
insert into State (id,StateCode,stateName) values(8,'HR','Haryana');
insert into State (id,StateCode,stateName) values(9,'HP','Himachal Pradesh');
insert into State (id,StateCode,stateName) values(10,'JK','Jammu & Kashmir');
insert into State (id,StateCode,stateName) values(11,'JH','Jharkhand');
insert into State (id,StateCode,stateName) values(12,'KA','Karnataka');
insert into State (id,StateCode,stateName) values(13,'KL','Kerala');
insert into State (id,StateCode,stateName) values(14,'MP','Madhya Pradesh');
insert into State (id,StateCode,stateName) values(15,'MH','Maharashtra');
insert into State (id,StateCode,stateName) values(16,'MN','Manipur');
insert into State (id,StateCode,stateName) values(17,'ML','Meghalaya');
insert into State (id,StateCode,stateName) values(18,'MZ','Mizoram');
insert into State (id,StateCode,stateName) values(19,'NL','Nagaland');
insert into State (id,StateCode,stateName) values(20,'OR','Orissa');
insert into State (id,StateCode,stateName) values(21,'PB','Punjab');
insert into State (id,StateCode,stateName) values(22,'RJ','Rajasthan');
insert into State (id,StateCode,stateName) values(23,'SK','Sikkim');
insert into State (id,StateCode,stateName) values(24,'TN','Tamil Nadu');
insert into State (id,StateCode,stateName) values(25,'TR','Tripura');
insert into State (id,StateCode,stateName) values(26,'UK','Uttaranchal');
insert into State (id,StateCode,stateName) values(27,'UP','Uttar Pradesh');
insert into State (id,StateCode,stateName) values(28,'WB','West Bengal');
insert into State (id,StateCode,stateName) values(29,'AN','Andaman & Nicobar');
insert into State (id,StateCode,stateName) values(30,'CH','Chandigarh');
insert into State (id,StateCode,stateName) values(31,'DN','Dadra and Nagar Haveli');
insert into State (id,StateCode,stateName) values(32,'DD','Daman & Diu');
insert into State (id,StateCode,stateName) values(33,'DL','Delhi');
insert into State (id,StateCode,stateName) values(34,'LD','Lakshadweep');
insert into State (id,StateCode,stateName) values(35,'PY','Puducherry');
insert into State (id,StateCode,stateName) values(36,'TG','Telangana');
insert into State (id,StateCode,stateName) values(37,'NA','---------------------');

--list of districts
delete from district;

INSERT INTO district (id,stateId,districtCode,districtName) 
VALUES
(1,1,'AD','Adilabad'),
(2,1,'AN','Anantapur'),
(3,1,'CH','Chittoor'),
(4,1,'EG','East Godavari'),
(5,1,'GU','Guntur'),
(6,1,'HY','Hyderabad'),
(7,1,'KA','Karimnagar'),
(8,1,'KH','Khammam'),
(9,1,'KR','Krishna'),
(10,1,'KU','Kurnool'),
(11,1,'MA','Mahbubnagar'),
(12,1,'Me','Medak'),
(13,1,'NA','Nalgonda'),
(14,1,'NI','Nizamabad'),
(15,1,'PR','Prakasam'),
(16,1,'RA','Ranga Reddy'),
(17,1,'SR','Srikakulam'),
(18,1,'NE','Sri Potti Sri Ramulu Nellore'),
(19,1,'VS','Vishakhapatnam'),
(20,1,'VZ','Vizianagaram'),
(21,1,'WA','Warangal'),
(22,1,'WG','West
Godavari '),(23,1,'CU','Kadapa'),
(24,2,'AJ','Anjaw'),(25,2,'CH','Changlang'),(26,2,'ES','East
Siang'),(27,2,'EK','East Kameng'),(28,2,'KK','Kurung Kumey
'),(29,2,'EL','Lohit'),(30,2,'LD','Lower Dibang
Valley'),(31,2,'LB','Lower Subansiri '),(32,2,'PA','Papum Pare
'),(33,2,'TA','Tawang'),(34,2,'TI','Tirap'),(35,2,'UD','Dibang
Valley'),(36,2,'US','Upper Siang'),(37,2,'UB','Upper
Subansiri'),(38,2,'WK','West Kameng'),(39,2,'WS','West
Siang'),(40,3,'KP','Kamrup'),(41,3,'BK','Baksa
'),(42,3,'BA','Barpeta'),(43,3,'BO','Bongaigaon'),(44,3,'CA','Cachar'),(45,3,'CR','Chirang'),(46,3,'DA','Darrang'),(47,3,'DM','Dhemaji'),(48,3,'NC','Dima
Hasao'),(49,3,'DB','Dhubri'),(50,3,'DI','Dibrugarh'),(51,3,'GP','Goalpara'),(52,3,'GG','Golaghat'),(53,3,'HA','Hailakandi'),(54,3,'JO','Jorhat'),(55,3,'KP','Kamrup'),(56,3,'KM','Kamrup
Metropolitan'),(57,3,'KA','Karbi Anglong
'),(58,3,'KR','Karimganj'),(59,3,'KK','Kokrajhar'),(60,3,'LA','Lakhimpur'),(61,3,'MA','Marigaon'),(62,3,'NG','Nagaon'),(63,3,'NL','Nalbari'),(64,3,'SI','Sibsagar'),(65,3,'SO','Sonitpur'),(66,3,'UG','Udalguri'),(67,5,'BA','Bastar'),(68,5,'BJ','Bijapur'),(69,5,'BI','Bilaspur'),(70,5,'DA','Dantewada'),(71,5,'DH','Dhamtari'),(72,5,'DU','Durg'),(73,5,'JA','Jashpur'),(74,5,'JC','Janjgir-Champa'),(75,5,'KB','Korba'),(76,5,'KJ','Koriya'),(77,5,'KK','Kanker'),(78,5,'KW','Kabirdham
formerlyKawardha)'),(79,5,'MA','Mahasamund'),(80,5,'NR','Narayanpur'),(81,5,'RG','Raigarh'),(82,5,'RN','Rajnandgaon'),(83,5,'RP','Raipur'),(84,5,'SJ','Surguja'),(85,8,'AM','Ambala'),(86,8,'AM','Bhiwani'),(87,8,'AM','Faridabad'),(88,8,'AM','Fatehabad'),(89,8,'AM','Gurgaon'),(90,8,'AM','Hissar'),(91,8,'AM','Jhajjar'),(92,8,'AM','Jind'),(93,8,'AM','Karnal'),(94,8,'AM','Kaithal'),(95,8,'AM','Kurukshetra'),(96,8,'AM','Mahendragarh'),(97,8,'AM','Mewat'),(98,8,'AM','Palwal'),(99,8,'AM','Panchkula'),(100,8,'AM','Panipat'),(101,8,'AM','Rewari'),(102,8,'AM','Rohtak'),(103,8,'AM','Sirsa'),(104,8,'AM','Sonipat'),(105,8,'AM','Yamuna
Nagar'),(106,35,'KA','Karaikal'),(107,35,'MA','Mahe'),(108,35,'PO','Pondicherry'),(109,35,'YA','Yanam'),(110,34,'CD','Central
Delhi'),(111,34,'ED','East Delhi'),(112,34,'ND','New
Delhi'),(113,34,'NO','North Delhi'),(114,34,'NE','North East
Delhi'),(115,34,'NW','North West Delhi'),(116,34,'SD','South
Delhi'),(117,34,'SW','South West Delhi'),(118,34,'WD','West
Delhi'),(119,33,'LD','Lakshadweep'),(120,32,'DA','Daman'),(121,32,'DI','Diu'),(122,31,'DN','Dadra
and Nagar Haveli'),(123,30,'DH','Chandigarh'),(124,29,'NA','North
Andaman'),(125,29,'SA','South
Andaman'),(126,29,'NI','Nicobar'),(127,28,'BN','Bankura'),(128,28,'BR','Bardhaman'),(129,28,'BI','Birbhum'),(130,28,'KB','Cooch
Behar'),(131,28,'DD','Dakshin
Dinajpur'),(132,28,'DA','Darjeeling'),(133,28,'HG','Hooghly'),(134,28,'HR','Howrah'),(135,28,'JA','Jalpaiguri'),(136,28,'KO','Kolkata'),(137,28,'MA','Maldah'),(138,28,'MSD','Murshidabad'),(139,28,'NA','Nadia'),(140,28,'PN','North
24 Parganas'),(141,28,'PM','Paschim Medinipur'),(142,28,'PR','Purba
Medinipur'),(143,28,'PU','Purulia'),(144,28,'PS','South 24
Parganas'),(145,28,'UD','Uttar
Dinajpur'),(146,27,'AL','Almora'),(147,27,'BA','Bageshwar'),(148,27,'CL','Chamoli'),(149,27,'CP','Champawat'),(150,27,'DD','Dehradun'),(151,27,'HA','Haridwar'),(152,27,'NA','Nainital'),(153,27,'PG','Pauri
Garhwal'),(154,27,'PI','Pithoragarh'),(155,27,'RP','Rudraprayag'),(156,27,'TG','Tehri
Garhwal'),(157,27,'US','Udham Singh
Nagar'),(158,27,'UT','Uttarkashi'),(159,26,'AL','Almora'),(160,26,'BA','Bageshwar'),(161,26,'CL','Chamoli'),(162,26,'CP','Champawat'),(163,26,'DD','Dehradun'),(164,26,'HA','Haridwar'),(165,26,'NA','Nainital'),(166,26,'PG','Pauri
Garhwal'),(167,26,'PI','Pithoragarh'),(168,26,'RP','Rudraprayag'),(169,26,'TG','Tehri
Garhwal'),(170,26,'US','Udham Singh
Nagar'),(171,26,'UT','Uttarkashi'),(172,15,'AH','Ahmednagar'),(173,15,'AK','Akola'),(174,15,'AM','Amravati'),(175,15,'AU','Aurangabad'),(176,15,'BI','Beed'),(177,15,'BH','Bhandara'),(178,15,'BU','Buldhana'),(179,15,'CH','Chandrapur'),(180,15,'DH','Dhule'),(181,15,'GA','Gadchiroli'),(182,15,'GO','Gondia'),(183,15,'HI','Hingoli'),(184,15,'JG','Jalgaon'),(185,15,'JN','Jalna'),(186,15,'KO','Kolhapur'),(187,15,'LA','Latur'),(188,15,'MC','Mumbai
City'),(189,15,'MU','Mumbai
suburban'),(190,15,'ND','Nanded'),(191,15,'NB','Nandurbar'),(192,15,'NG','Nagpur'),(193,15,'NS','Nashik'),(194,15,'OS','Osmanabad'),(195,15,'PA','Parbhani'),(196,15,'PU','Pune'),(197,15,'RG','Raigad'),(198,15,'RT','Ratnagiri'),(199,15,'SN','Sangli'),(200,15,'ST','Satara'),(201,15,'SI','Sindhudurg'),(202,15,'SO','Solapur'),(203,15,'TH','Thane'),(204,15,'WR','Wardha'),(205,15,'WS','Washim'),(206,15,'YA','Yavatmal'),(207,4,'AR','Araria'),(208,4,'AR','Arwal'),(209,4,'AU','Aurangabad'),(210,4,'BA','Banka'),(211,4,'BE','Begusarai'),(212,4,'BG','Bhagalpur'),(213,4,'BJ','Bhojpur'),(214,4,'BU','Buxar'),(215,4,'DA','Darbhanga'),(216,4,'EC','East
Champaran'),(217,4,'GA','Gaya'),(218,4,'GO','Gopalganj'),(219,4,'JA','Jamui'),(220,4,'JE','Jehanabad'),(221,4,'KM','Kaimur'),(222,4,'KT','Katihar'),(223,4,'KH','Khagaria'),(224,4,'KI','Kishanganj'),(225,4,'LA','Lakhisarai'),(226,4,'MP','Madhepura'),(227,4,'MB','Madhubani'),(228,4,'MG','Munger'),(229,4,'MZ','Muzaffarpur'),(230,4,'NL','Nalanda'),(231,4,'NW','Nawada'),(232,4,'PA','Patna'),(233,4,'PU','Purnia'),(234,4,'RO','Rohtas'),(235,4,'SH','Saharsa'),(236,4,'SM','Samastipur'),(237,4,'SR','Saran'),(238,4,'SP','Sheikhpura'),(239,4,'SO','Sheohar'),(240,4,'ST','Sitamarhi'),(241,4,'SW','Siwan'),(242,4,'SU','Supaul'),(243,4,'VA','Vaishali'),(244,4,'WC','West
Champaran'),(245,6,'NG','West North Goa'),(246,6,'SG','South
Goa'),(247,7,'AH','Ahmedabad'),(248,7,'AM','Amreli
district'),(249,7,'AN','Anand'),(250,7,'BK','Banaskantha'),(251,7,'BR','Bharuch'),(252,7,'BV','Bhavnagar'),(253,7,'DA','Dahod'),(254,7,'DG','The
Dangs'),(255,7,'GA','Gandhinagar'),(256,7,'JA','Jamnagar'),(257,7,'JU','Junagadh'),(258,7,'KA','Kutch'),(259,7,'KH','Kheda'),(260,7,'MA','Mehsana'),(261,7,'NR','Narmada'),(262,7,'PA','Patan'),(263,7,'PM','Panchmahal'),(264,7,'PO','Porbandar'),(265,7,'RA','Rajkot'),(266,7,'SK','Sabarkantha'),(267,7,'SN','Surendranagar'),(268,7,'ST','Surat'),(269,7,'TI','Tapi'),(270,7,'VD','Vadodara'),(271,7,'VL','Valsad'),(272,9,'BI','Bilaspur'),(273,9,'CH','Chamba'),(274,9,'HA','Hamirpur'),(275,9,'KA','Kangra'),(276,9,'KI','Kinnaur'),(277,9,'KU','Kullu'),(278,9,'LS','Lahaul
and
Spiti'),(279,9,'MA','Mandi'),(280,9,'SH','Shimla'),(281,9,'SI','Sirmaur'),(282,9,'SO','Solan'),(283,9,'VL','Valsad'),(284,9,'UNA','Una'),(285,10,'AN','Anantnag'),(286,10,'BD','Badgam'),(287,10,'BPR','Bandipora'),(288,10,'BR','Baramulla'),(289,10,'DO','Doda'),(290,10,'GB','Ganderbal'),(291,10,'JA','Jammu'),(292,10,'KR','Kargil'),(293,10,'KT','Kathua'),(294,10,'KW','Kishtwar'),(295,10,'KU','Kupwara'),(296,10,'KG','Kulgam'),(297,10,'LE','Leh'),(298,10,'PO','Poonch'),(299,10,'PU','Pulwama'),(300,10,'RA','Rajauri'),(301,10,'RB','Ramban'),(302,10,'RS','Reasi'),(303,10,'SB','Samba'),(304,10,'SH','Shopian'),(305,10,'SR','Srinagar'),(306,10,'UD','Udhampur'),(307,11,'BO','Bokaro'),(308,11,'CH','Chatra'),(309,11,'DE','Deoghar'),(310,11,'DH','Dhanbad'),(311,11,'DU','Dumka'),(312,11,'ES','East
Singhbhum'),(313,11,'GA','Garhwa'),(314,11,'GI','Godda'),(315,11,'GU','Gumla'),(316,11,'HA','Hazaribag'),(317,11,'GA','Jamtara'),(318,11,'KI','Khunti'),(319,11,'KO','Koderma'),(320,11,'LR','Latehar'),(321,11,'LO','Lohardaga'),(322,11,'PK','Pakur'),(323,11,'PL','Palamu'),(324,11,'RM','Ramgarh'),(325,11,'RA','Ranchi'),(326,11,'SA','Sahibganj'),(327,11,'SK','Seraikela
Kharsawa'),(328,11,'SG','Simdega'),(329,11,'WS','West
Singhbhum'),(330,12,'BK','Bagalkot'),(331,12,'BR','Bangalore
Rural'),(332,12,'BN','Bangalore
Urban'),(333,12,'BG','Belgaum'),(334,12,'BL','Bellary'),(335,12,'BD','Bidar'),(336,12,'BJ','Bijapur'),(337,12,'CJ','Chamarajnagar'),(338,12,'CK','Chikkamagaluru'),(339,12,'CT','Chitradurga'),(340,12,'DA','Davanagere'),(341,12,'DH','Dharwad'),(342,12,'DK','Dakshina
Kannada
'),(343,12,'GA','Gadag'),(344,12,'GU','Gulbarga'),(345,12,'HS','Hassan'),(346,12,'HV','Haveri
district'),(347,12,'KD','Kodagu'),(348,12,'KL','Kolar'),(349,12,'KP','Koppal'),(350,12,'MA','Mandya'),(351,12,'MY','Mysore'),(352,12,'RA','Raichur'),(353,12,'SH','Shimoga'),(354,12,'TU','Tumkur'),(355,12,'UD','Udupi'),(356,12,'UK','Uttara
Kannada'),(357,12,'RM','Ramanagara'),(358,12,'YG','Yadgir'),(359,11,'BO','Bokaro'),(360,11,'CH','Chatra'),(361,11,'DE','Deoghar'),(362,11,'DH','Dhanbad'),(363,11,'DU','Dumka'),(364,11,'ES','East
Singhbhum'),(365,11,'GA','Garhwa'),(366,11,'GI','Godda'),(367,11,'GU','Gumla'),(368,11,'HA','Hazaribag'),(369,11,'GA','Jamtara'),(370,11,'KI','Khunti'),(371,11,'KO','Koderma'),(372,11,'LR','Latehar'),(373,11,'LO','Lohardaga'),(374,11,'PK','Pakur'),(375,11,'PL','Palamu'),(376,11,'RM','Ramgarh'),(377,11,'RA','Ranchi'),(378,11,'SA','Sahibganj'),(379,11,'SK','Seraikela
Kharsawa'),(380,11,'SG','Simdega'),(381,11,'WS','West
Singhbhum'),(382,12,'BK','Bagalkot'),(383,12,'BR','Bangalore
Rural'),(384,12,'BN','Bangalore
Urban'),(385,12,'BG','Belgaum'),(386,12,'BL','Bellary'),(387,12,'BD','Bidar'),(388,12,'BJ','Bijapur'),(389,12,'CJ','Chamarajnagar'),(390,12,'CK','Chikkamagaluru'),(391,12,'CT','Chitradurga'),(392,12,'DA','Davanagere'),(393,12,'DH','Dharwad'),(394,12,'DK','Dakshina
Kannada
'),(395,12,'GA','Gadag'),(396,12,'GU','Gulbarga'),(397,12,'HS','Hassan'),(398,12,'HV','Haveri
district'),(399,12,'KD','Kodagu'),(400,12,'KL','Kolar'),(401,12,'KP','Koppal'),(402,12,'MA','Mandya'),(403,12,'MY','Mysore'),(404,12,'RA','Raichur'),(405,12,'SH','Shimoga'),(406,12,'TU','Tumkur'),(407,12,'UD','Udupi'),(408,12,'UK','Uttara
Kannada'),(409,12,'RM','Ramanagara'),(410,12,'YG','Yadgir'),(411,13,'',''),(412,13,'AL','Alappuzha'),(413,13,'ER','Ernakulam'),(414,13,'ID','Idukki'),(415,13,'KN','Kannur'),(416,13,'KS','Kasaragod'),(417,13,'KL','Kollam'),(418,13,'KT','Kottayam'),(419,13,'KZ','Kozhikode'),(420,13,'MA','Malappuram'),(421,13,'PL','Palakkad'),(422,13,'PT','Pathanamthitta'),(423,13,'TS','Thrissur'),(424,13,'TV','Thiruvananthapuram'),(425,13,'WA','Wayanad'),(426,14,'AL','Alirajpur'),(427,14,'AP','Anuppur'),(428,14,'AS','Ashok
Nagar'),(429,14,'BL','Balaghat'),(430,14,'BR','Barwani'),(431,14,'BE','Betul'),(432,14,'BD','Bhind'),(433,14,'BP','Bhopal'),(434,14,'BU','Burhanpur'),(435,14,'CT','Chhatarpur'),(436,14,'CN','Chhindwara'),(437,14,'DM','Damoh'),(438,14,'DT','Datia'),(439,14,'DE','Dewas'),(440,14,'DH','Dhar'),(441,14,'DI','Dindori'),(442,14,'GU','Guna'),(443,14,'GW','Gwalior'),(444,14,'HA','Harda'),(445,14,'HO','Hoshangabad'),(446,14,'IN','Indore'),(447,14,'JA','Jabalpur'),(448,14,'JH','Jhabua'),(449,14,'KA','Katni'),(450,14,'EN','Khandwa
(East Nimar)'),(451,14,'WN','Khargone (West
Nimar)'),(452,14,'ML','Mandla'),(453,14,'MS','Mandsaur'),(454,14,'MO','Morena'),(455,14,'NA','Narsinghpur'),(456,14,'NE','Neemuch'),(457,14,'PA','Panna'),(458,14,'RS','Raisen'),(459,14,'RG','Rajgarh'),(460,14,'RL','Ratlam'),(461,14,'RE','Rewa'),(462,14,'SG','Sagar'),(463,14,'ST','Satna'),(464,14,'SR','Sehore'),(465,14,'SO','Seoni'),(466,14,'SH','Shahdol'),(467,14,'SJ','Shajapur'),(468,14,'SP','Sheopur'),(469,14,'SV','Shivpuri'),(470,14,'SI','Sidhi'),(471,14,'SN','Singrauli'),(472,14,'TI','Tikamgarh'),(473,14,'UJ','Ujjain'),(474,14,'UM','Umaria'),(475,14,'VI','Vidisha'),(476,16,'BI','Bishnupur'),(477,16,'CC','Churachandpur'),(478,16,'CD','Chandel'),(479,16,'EI','Imphal
East'),(480,16,'SE','Senapati'),(481,16,'TA','Tamenglong'),(482,16,'TH','Thoubal'),(483,16,'UK','Ukhrul'),(484,16,'WI','Imphal
West'),(485,17,'EG','East Garo Hills'),(486,17,'EK','East Khasi
Hills'),(487,17,'JH','Jaintia Hills'),(488,17,'RB','Ri
Bhoi'),(489,17,'SG','South Garo Hills'),(490,17,'WG','West Garo
Hills'),(491,17,'WK','West Khasi
Hills'),(492,18,'AI','Aizawl'),(493,18,'CH','Champhai'),(494,18,'KO','Kolasib'),(495,18,'LA','Lawngtlai'),(496,18,'LU','Lunglei'),(497,18,'MA','Mamit'),(498,18,'SA','Saiha'),(499,18,'SE','Serchhip'),(500,19,'DI','Dimapur'),(501,19,'KE','Kiphire'),(502,19,'KO','Kohima'),(503,19,'LL','Longleng'),(504,19,'MK','Mokokchung'),(505,19,'MN','Mon'),(506,19,'PN','Peren'),(507,19,'PH','Phek'),(508,19,'TU','Tuensang'),(509,19,'WO','Wokha'),(510,19,'ZU','Zunheboto'),(511,20,'AN','Angul'),(512,20,'BD','Boudh
(Bauda)'),(513,20,'BH','Bhadrak'),(514,20,'BL','Balangir'),(515,20,'BR','Bargarh
(Baragarh)'),(516,20,'BW','Balasore'),(517,20,'CU','Cuttack'),(518,20,'DE','Debagarh
(Deogarh)'),(519,20,'DH','Dhenkanal'),(520,20,'GN','Ganjam'),(521,20,'GP','Gajapati'),(522,20,'JH','Jharsuguda'),(523,20,'JP','Jajpur'),(524,20,'JS','Jagatsinghpur'),(525,20,'KH','Khordha'),(526,20,'KJ','Kendujhar
(Keonjhar)'),(527,20,'KL','Kalahandi'),(528,20,'KN','Kandhamal'),(529,20,'KO','Koraput'),(530,20,'KP','Kendrapara'),(531,20,'ML','Malkangiri'),(532,20,'MY','Mayurbhanj'),(533,20,'NB','Nabarangpur'),(534,20,'NU','Nuapada'),(535,20,'NY','Nayagarh'),(536,20,'PU','Puri'),(537,20,'RA','Rayagada'),(538,20,'SA','Sambalpur'),(539,20,'SO','Subarnapur
(Sonepur)'),(540,20,'SU','Sundergarh'),(541,21,'AM','Amritsar'),(542,21,'BNL','Barnala'),(543,21,'BA','Bathinda'),(544,21,'FI','Firozpur'),(545,21,'FR','Faridkot'),(546,21,'FT','Fatehgarh
Sahib'),(547,21,'FA','Fazilka[6]'),(548,21,'GU','Gurdaspur'),(549,21,'HO','Hoshiarpur'),(550,21,'JA','Jalandhar'),(551,21,'KA','Kapurthala'),(552,21,'LU','Ludhiana'),(553,21,'MA','Mansa'),(554,21,'MO','Moga'),(555,21,'MU','Sri
Muktsar
Sahib'),(556,21,'PA','Pathankot'),(557,21,'PA','Patiala'),(558,21,'RU','Rupnagar'),(559,21,'SAS','Ajitgarh
(Mohali)'),(560,21,'SA','Sangrur'),(561,21,'SN','Shahid Bhagat Singh
Nagar'),(562,21,'TT','Tarn
Taran'),(563,22,'AJ','Ajmer'),(564,22,'AL','Alwar'),(565,22,'BI','Bikaner'),(566,22,'BM','Barmer'),(567,22,'BN','Banswara'),(568,22,'BP','Bharatpur'),(569,22,'BR','Baran'),(570,22,'BU','Bundi'),(571,22,'BW','Bhilwara'),(572,22,'CR','Churu'),(573,22,'CT','Chittorgarh'),(574,22,'DA','Dausa'),(575,22,'DH','Dholpur'),(576,22,'DU','Dungapur'),(577,22,'GA','Ganganagar'),(578,22,'HA','Hanumangarh'),(579,22,'JJ','Jhunjhunu'),(580,22,'JL','Jalore'),(581,22,'JO','Jodhpur'),(582,22,'JP','Jaipur'),(583,22,'JS','Jaisalmer'),(584,22,'JW','Jhalawar'),(585,22,'KA','Karauli'),(586,22,'KO','Kota'),(587,22,'NA','Nagaur'),(588,22,'PA','Pali'),(589,22,'PG','Pratapgarh'),(590,22,'RA','Rajsamand'),(591,22,'SK','Sikar'),(592,22,'SM','Sawai
Madhopur'),(593,22,'SR','Sirohi'),(594,22,'TO','Tonk'),(595,22,'UD','Udaipur'),(596,23,'ES','East
Sikkim'),(597,23,'NS','North Sikkim'),(598,23,'SS','South
Sikkim'),(599,23,'WS','West
Sikkim'),(600,24,'AY','Ariyalur'),(601,24,'CH','Chennai'),(602,24,'CO','Coimbatore'),(603,24,'CU','Cuddalore'),(604,24,'DH','Dharmapuri'),(605,24,'DI','Dindigul'),(606,24,'ER','Erode'),(607,24,'KC','Kanchipuram'),(608,24,'KK','Kanyakumari'),(609,24,'KR','Karur'),(610,24,'KR','Krishnagiri'),(611,24,'MA','Madurai'),(612,24,'NG','Nagapattinam'),(613,24,'NI','Nilgiris'),(614,24,'NM','Namakkal'),(615,24,'PE','Perambalur'),(616,24,'PU','Pudukkottai'),(617,24,'RA','Ramanathapuram'),(618,24,'SA','Salem'),(619,24,'SI','Sivaganga'),(620,24,'TP','Tirupur'),(621,24,'TC','Tiruchirappalli'),(622,24,'TH','Theni'),(623,24,'TI','Tirunelveli'),(624,24,'TJ','Thanjavur'),(625,24,'TK','Thoothukudi'),(626,24,'TL','Tiruvallur'),(627,24,'TR','Tiruvarur'),(628,24,'TV','Tiruvannamalai'),(629,24,'VE','Vellore'),(630,24,'VL','Viluppuram'),(631,24,'VR','Virudhunagar'),(632,25,'DH','Dhalai'),(633,25,'NT','North
Tripura'),(634,25,'ST','South
Tripura'),(635,25,'ST','Khowai[7]'),(636,25,'WT','West Tripura');


INSERT INTO  govtmealtypes (id, version, mealType, mealTypeCode) VALUES (1, 0, 'BreakFast', 'B');

INSERT INTO  govtmealtypes (id, version, mealType, mealTypeCode) VALUES (2, 0, 'Lunch', 'L');

INSERT INTO govtmealtypes (id, version, mealType, mealTypeCode) VALUES (3, 0, 'Dinner', 'D');



/* Inserting Queries for Mandals */
delete from mandal;

insert into mandal(districtId,mandalCode,mandalName) Values(20,'1','IBRAHIMPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'2','MALLAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'3','RAIKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'4','SARANGAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'5','DHARMAPURI');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'6','VELGATOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'7','RAMAGUNDAM');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'8','KAMANPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'9','MANTHANI');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'10','KATARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'11','MAHADEVPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'12','MUTHARAM MAHADEVPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'13','MALHARRAO');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'14','MUTHARAM MANTHANI');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'15','SRIRAMPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'16','PEDDAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'17','JULAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'18','DHARMARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'19','GOLLAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'20','JAGTIAL');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'21','MEDIPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'22','KORATLA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'23','METPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'24','KATHLAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'25','CHANDURTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'26','KODIMIAL');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'27','GANGADHARA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'28','MALLIAL');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'29','PEGADAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'30','CHOPPADANDI');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'31','SULTANABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'32','ODELA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'33','JAMMIKUNTA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'34','VEENAVANKA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'35','MANAKONDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'36','KARIMNAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'37','RAMADUGU');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'38','BOINPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'39','VEMULAWADA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'40','KONARAOPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'41','YELLA REDDI PETA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'42','GAMBHIRAOPET');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'43','MUSTABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'44','SIRSILLA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'45','ELLANTHAKUNTA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'46','BEJJANKI');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'47','THIMMAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'48','KESAVAPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'49','HUZURABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'50','KAMALAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'51','ELKATHURTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'52','SAIDAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'53','CHIGURUMAMIDI');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'54','KOHEDA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'55','HUSNABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'56','BHEEMADEVARPALLE');


insert into mandal(districtId,mandalCode,mandalName) Values(21,'1','CHERIYAL');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'2','MADDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'3','NARMETTA');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'4','BACHANNAPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'5','JANGAON');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'6','LINGALA GHANPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'7','RAGHUNATHA PALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'8','GHANPUR(stn)');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'9','DHARMASAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'10','HASANPARTHY');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'11','HANAMKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'12','WARDHANNAPET');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'13','ZAFFERGADH');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'14','PALAKURTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'15','DEVARUPPULA');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'16','KODAKANDLA');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'17','RAIPARTHY');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'18','THORRUR');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'19','NELLIKUDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'20','NARSIMHULAPET');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'21','MARIPEDA');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'22','DORNAKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'23','KURAVI');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'24','MAHABUBABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'25','KESAMUDRAM');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'26','NEKKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'27','GUDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'28','KOTHAGUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'29','KHANAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'30','NARSAMPET');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'31','CHENNARAOPET');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'32','PARVATHAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'33','SANGEM');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'34','NALLABELLY');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'35','DUGGONDI');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'36','GEESUGONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'37','ATMAKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'38','SHAYAMPET');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'39','PARKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'40','REGONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'41','MOGULLAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'42','CHITYAL');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'43','BHUPALPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'44','GHANAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'45','MULUG');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'46','VENKATAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'47','GOVINDARAOPET');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'48','TADVAI');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'49','ETURNAGARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'50','MANGAPET');



insert into mandal(districtId,mandalCode,mandalName) Values(22,'1','WAZEED');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'2','VENKATAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'3','CHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'4','PINAPAKA');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'5','GUNDALA');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'6','MANUGURU');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'7','ASWAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'8','DUMMUGUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'9','BHADRACHALAM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'10','KUNAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'11','CHINTUR');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'12','VARARAMACHANDRAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'13','VELAIRPAD');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'14','KUKUNOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'15','BURGAMPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'16','PALAWANCHA');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'17','KOTHAGUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'18','TEKULAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'19','YELLANDU');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'20','SINGARENI');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'21','BAYYARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'22','GARLA');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'23','KAMEPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'24','JULURPAD');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'25','CHANDRUGONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'26','MULAKALAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'27','ASWARAOPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'28','DAMMAPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'29','SATHUPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'30','VEMSOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'31','PENUBALLI');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'32','KALLURU');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'33','THALLADA');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'34','ENKURU');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'35','KONIJERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'36','KHAMMAM URBAN');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'37','KHAMMAM RURAL');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'38','THIRUMALAYAPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'39','KUSUMANCHI');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'40','NELAKONDAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'41','MUDIGONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'42','CHINTHAKANI');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'43','WYRA');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'44','BONAKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'45','MADHIRA');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'46','YERRUPALEM');


insert into mandal(districtId,mandalCode,mandalName) Values(23,'1','BOMMALARAMARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'2','M TURKAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'3','RAJAPET');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'4','YADAGIRIGUTTA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'5','ALAIR');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'6','GUNDALA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'7','THIRUMALAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'8','THUNGA THURTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'9','NUTHANKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'10','ATMAKUR (S)');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'11','JAJI REDDI GUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'12','SALIGOURARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'13','MOTHKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'14','ATMAKUR (M)');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'15','VALIGONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'16','BHUVANAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'17','BIBINAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'18','POCHAMPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'19','CHOUTUPPAL');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'20','RAMANNAPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'21','CHITYALA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'22','NARKETPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'23','KATTANGOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'24','NAKREKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'25','KETHEPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'26','SURYAPET');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'27','CHIVVEMLA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'28','MOTHEY');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'29','NADIGUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'30','MUNAGALA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'31','PENPAHAD');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'32','VEMULAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'33','THIPPARTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'34','NALGONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'35','MUNUGODE');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'36','NARAYANAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'37','MARRI GUDA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'38','CHANDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'39','KANGAL');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'40','NIDAMANUR');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'41','THRIPURARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'42','MIRYALAGUDA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'43','GARIDE PALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'44','CHILKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'45','KODAD');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'46','MELLACHERVU');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'47','HUZURNAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'48','MATTAMPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'49','NERED CHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'50','DAMERACHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'51','ANUMULA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'52','PEDDAVURA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'53','PEDDA ADISERLAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'54','GURRAMPODE');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'55','NAMPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'56','CHINTHA PALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'57','DEVARAKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'58','GUNDLA PALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'59','CHANDAM PET');



insert into mandal(districtId,mandalCode,mandalName) Values(19,'1','TALAMADUGU');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'2','TAMSI');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'3','ADILABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'4','JAINAD');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'5','BELA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'6','NARNOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'7','INDERAVELLY');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'8','GUDIHATHNUR');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'9','ICHODA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'10','BAZARHATHNOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'11','BOATH');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'12','NERADIGONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'13','SARANGAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'14','KUNTALA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'15','KUBEER');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'16','BHAINSA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'17','TANUR');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'18','MUDHOLE');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'19','LOHESRA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'20','DILAWARPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'21','NIRMAL');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'22','LAXMANCHANDA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'23','MAMDA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'24','KHANPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'25','KADDAMPEDDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'26','UTNUR');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'27','JAINOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'28','KERAMERI');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'29','SIRPUR (U)');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'30','JANNARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'31','DANDEPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'32','LUXETTIPET');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'33','MANCHERIAL');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'34','MANDAMARRI');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'35','KASIPET');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'36','TIRYANI');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'37','ASIFABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'38','WANKDI');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'39','KAGAZ NAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'40','REBBANA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'41','TANDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'42','BELLAMPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'43','NENNAL');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'44','BHEEMINI');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'45','SIRPUR (T)');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'46','KOUTHALA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'47','BEJJUR');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'48','DAHEGAON');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'49','VEMANPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'50','KOTAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'51','CHENNUR');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'52','JAIPUR');


insert into mandal(districtId,mandalCode,mandalName) Values(18,'1','RANJAL');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'2','NAVIPET');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'3','NANDIPET');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'4','ARMUR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'5','BALKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'6','MORTAD');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'7','KAMMAR PALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'8','BHEEMGAL');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'9','VELPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'10','JAKRANPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'11','MAKLOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'12','NIZAMABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'13','YEDA PALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'14','BODHAN');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'15','KOTGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'16','MADNUR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'17','JUKKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'18','BICHKUNDA');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'19','BIRKOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'20','VARNI');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'21','DICH PALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'22','DHAR PALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'23','SIRKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'24','MACHAREDDY');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'25','SADASIVANAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'26','GANDHARI');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'27','BANSWADA');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'28','PITLAM');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'29','NIZAM SAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'30','YELLAREDDY');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'31','NAGA REDDIPET');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'32','LINGAMPET');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'33','TADWAI');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'34','KAMAREDDY');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'35','BHIKNUR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'36','DOMAKONDA');



insert into mandal(districtId,mandalCode,mandalName) Values(17,'1','MANOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'2','KANGTI');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'3','KALHER');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'4','NARAYANKHED');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'5','REGODE');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'6','SHANKARAMPET (A)');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'7','ALLADURG');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'8','TEKMAL');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'9','PAPANNAPET');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'10','KULCHARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'11','MEDAK');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'12','SHANKARAMPET (R)');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'13','RAMAYAMPET');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'14','DUBBAK');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'15','MIRDODDI');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'16','SIDDIPET');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'17','CHINNA KODUR');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'18','NANGANUR');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'19','KONDAPAK');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'20','JAGDEVPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'21','GAJWEL');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'22','DOULTABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'23','CHEGUNTA');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'24','YELDURTHY');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'25','KOWDIPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'26','ANDOLE');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'27','RAIKODE');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'28','NYALKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'29','JHARASANGAM');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'30','ZAHIRABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'31','KOHIR');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'32','MUNPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'33','PULKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'34','SADASIVPET');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'35','KONDAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'36','SANGAREDDY');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'37','PATANCHERU');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'38','RAMACHANDRAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'39','JINNARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'40','HATHNOORA');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'41','NARSAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'42','SHIVAMPET');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'43','TUPRAN');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'44','WARGAL');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'45','MULUG');



insert into mandal(districtId,mandalCode,mandalName) Values(16,'1','AMEERPET');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'2','TIRUMALGHERRY');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'3','MARREDPALLY');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'4','AMBERPET');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'5','HIMAYATNAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'6','NAMPALLY');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'7','SHAIKPET');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'8','KHAIRTABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'9','ASIFNAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'10','SAIDABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'11','BHADURPURA');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'12','BANDLAGUDA');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'13','SECUNDERABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'14','MUSHEERABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'15','GOLCONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'16','CHARMINAR');



insert into mandal(districtId,mandalCode,mandalName) Values(15,'1','MARPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'2','MOMINPET');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'3','NAWABPET');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'4','SHANKARPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'5','SERILINGAMPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'6','BALANAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'7','QUTHBULLAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'8','MEDCHAL');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'9','SHAMIRPET');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'10','MALKAJGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'11','KEESARA');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'12','GHATKESAR');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'13','UPPAL');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'14','HAYATHNAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'15','SAROORNAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'16','RAJENDRANAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'17','MOINABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'18','CHEVELLA');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'19','VIKARABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'20','DHARUR');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'21','BANTARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'22','PEDDEMUL');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'23','TANDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'24','BASHEERABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'25','YELAL');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'26','DOMA');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'27','GANDEED');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'28','KULKACHARLA');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'29','PARGI');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'30','PUDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'31','SHABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'32','SHAMSHABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'33','MAHESWARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'34','IBRAHIMPATAM');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'35','MANCHAL');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'36','YACHARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'37','KANDUKUR');



insert into mandal(districtId,mandalCode,mandalName) Values(14,'1','KODANGAL');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'2','BOMRASPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'3','KOSGI');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'4','DOULATABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'5','DAMARAGIDDA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'6','MADDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'7','KOILKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'8','HANWADA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'9','NAWABPET');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'10','BALANAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'11','KONDURG');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'12','FAROOQNAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'13','KOTHUR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'14','KESHAMPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'15','TALAKONDAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'16','AMANGAL');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'17','MADGUL');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'18','VANGOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'19','VELDANDA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'20','KALWAKURTHY');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'21','MIDJIL');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'22','THIMMAJIPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'23','JADCHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'24','BHOOTHPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'25','MAHBUBNAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'26','ADDAKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'27','DEVARKADARA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'28','DHANWADA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'29','NARAYANPET');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'30','UTKOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'31','MAGANOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'32','MAKTHAL');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'33','NARVA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'34','CHINNA CHINTA KUNTA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'35','ATMAKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'36','KOTHAKOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'37','PEDDAMANDADI');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'38','GHANPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'39','BIJINAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'40','NAGAR KURNOOL');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'41','TADOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'42','TELKAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'43','UPPUNUNTHALA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'44','ACHAMPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'45','AMRABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'46','BALMOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'47','LINGAL');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'48','PEDDAKOTHAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'49','KODAIR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'50','GOPALPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'51','WANAPARTHY');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'52','PANGAL');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'53','PEBBAIR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'54','GADWAL');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'55','DHARUR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'56','MALDAKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'57','GHATTU');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'58','AIZA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'59','WADDEPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'60','ITIKYAL');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'61','MANOPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'62','ALAMPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'63','VEEPANGANDLA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'64','KOLLAPUR');


insert into mandal(districtId,mandalCode,mandalName) Values(13,'1','KOWTHALAM');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'2','KOSIGI');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'3','MANTRALAYAM');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'4','NANDAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'5','C.BELAGAL');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'6','GUDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'7','KURNOOL');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'8','NANDI KOTKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'9','PAGIDYALA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'10','KOTHAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'11','ATMAKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'12','SRISAILAM');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'13','VELGODU');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'14','PAMULAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'15','JUPADU BUNGALOW');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'16','MIDTHUR');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'17','ORVAKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'18','KALLUR');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'19','KODUMUR');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'20','GONEGANDLA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'21','YEMMIGANUR');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'22','PEDDA KADUBUR');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'23','ADONI');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'24','HOLAGUNDA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'25','ALUR');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'26','ASPARI');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'27','DEVANAKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'28','KRISHNAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'29','VELDURTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'30','BETHAMCHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'31','PANYAM');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'32','GADIVEMULA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'33','BANDI ATMAKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'34','NANDYAL');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'35','MAHANANDI');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'36','SIRVEL');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'37','RUDRAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'38','ALLAGADDA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'39','CHAGALAMARRI');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'40','UYYALAWADA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'41','DORNIPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'42','GOSPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'43','KOILKUNTLA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'44','BANAGANAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'45','SANJAMALA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'46','KOLIMIGUNDLA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'47','OWK');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'48','PEAPALLY');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'49','DHONE');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'50','TUGGALI');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'51','PATTIKANDA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'52','MADDIKERA EAST');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'53','CHIPPAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'54','HALAHARVI');



insert into mandal(districtId,mandalCode,mandalName) Values(12,'1','D.HIRCHAL');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'2','BOMMANAHAL');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'3','VIDAPANAKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'4','VAJRAKARUR');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'5','GUNTAKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'6','GOOTY');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'7','PEDDAVADUGUR');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'8','YADIKI');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'9','TADPATRI');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'10','PEDDAPAPPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'11','SINGANAMALA');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'12','PAMIDI');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'13','GARLADINNE');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'14','KUDAIR');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'15','URAVAKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'16','BELUGUPPA');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'17','KANEKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'18','RAYADURG');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'19','GUMMAGATTA');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'20','BRAHMASAMUDRAM');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'21','SETTUR');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'22','KUNDURPI');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'23','KALYANDURG');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'24','ATMAKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'25','ANANTAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'26','BUKKARAYASAMUDRAM');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'27','NARPALA');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'28','PUTLUR');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'29','YELLANUR');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'30','TADIMARRI');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'31','BATHALAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'32','RAPTADU');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'33','KANAGANAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'34','KAMBADUR');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'35','RAMAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'36','CHENNE KOTHAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'37','DHARMAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'38','MUDIGUBBA');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'39','TALUPULA');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'40','NAMBULIPULIKUNTA');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'41','TANAKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'42','NALLACHERUVU');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'43','GANDLAPENTA');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'44','KADIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'45','AMADAGUR');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'46','OBULADEVARACHERUVU');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'47','NALLAMADA');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'48','GORANTLA');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'49','PUTTAPARTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'50','BUKKAPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'51','KOTHACHERUVU');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'52','PENU KONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'53','RODDAM');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'54','SOMANDEPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'55','CHILAMATHUR');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'56','LEPAKSHI');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'57','HINDUPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'58','PARIGI');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'59','MADAKASIRA');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'60','GUDIBANDA');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'61','AMARAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'62','AGALI');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'63','ROLLA');


insert into mandal(districtId,mandalCode,mandalName) Values(11,'1','KONDAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'2','MYLAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'3','PEDDAMUDIUM');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'4','RAJU PALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'5','DUVVUR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'6','S MYDUKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'7','BRAHMAMGARIMATTAM');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'8','B KODUR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'9','KALASAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'10','PORUMAMILLA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'11','BADVEL');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'12','GOPAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'13','KHAJIPET');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'14','CHAPAD');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'15','PRODDUTUR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'16','JAMMALAMADUGU');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'17','MUDDANUR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'18','SIMHADRIPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'19','LINGALA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'20','PULIVENDLA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'21','VEMULA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'22','THANDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'23','VEERAPUNAYUNIPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'24','YERRAGUNTLA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'25','KAMALAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'26','VALLUR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'27','CHENNUR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'28','ATLUR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'29','VONTIMITTA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'30','SIDHOUT');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'31','CUDDAPAH');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'32','CHINTHA KOMMADINNE');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'33','PENDLIMARRI');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'34','VEMPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'35','CHAKRAYAPET');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'36','LAKKIREDDIPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'37','RAMAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'38','VEERABALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'39','RAJAMPET');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'40','NANDALUR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'41','PENAGALURU');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'42','CHITVEL');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'43','KODUR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'44','OBULAVARIPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'45','PULLAMPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'46','T SUNDUPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'47','SAMBEPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'48','CHINNAMANDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'48','RAYACHOTI');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'49','GALIVEEDU');



insert into mandal(districtId,mandalCode,mandalName) Values(10,'1','PEDDAMANDYAM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'2','THAMBALLAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'3','MULAKALACHERUVU');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'4','PEDDATHIPPASAMUDRAM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'5','B KOTHAKOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'6','KURABALAKOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'7','GURRAMKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'8','KALAKADA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'9','KAMBHAMVARIPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'10','YERRAVARIPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'11','TIRUPATI URBAN');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'12','RENIGUNTA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'13','YERPEDU');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'14','SRIKALAHASTI');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'15','THOTTAMBEDU');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'16','BUCHINAIDU KHANDRIGA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'17','VARADAIAHPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'18','SATYAVEDU');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'19','NAGALAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'20','PICHATUR');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'21','VIJAYA PURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'22','NINDRA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'23','K V P PURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'24','NARAYANAVANAM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'25','VADAMALAPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'26','TIRUPATI RURAL');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'27','RAMACHANDRAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'28','CHANDRAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'29','CHINNAGOTTIGALLU');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'30','ROMPICHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'31','PILERU');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'32','KALIKIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'33','VAYALPAD');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'34','NIMMANAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'35','MANDANPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'36','RAMASAMUDRAM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'37','PUNGANUR');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'38','CHOWDEPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'39','SOMALA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'40','SODAM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'41','PULICHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'42','PAKALA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'43','VEDURU KUPPAM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'44','PUTTUR');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'45','NAGARI');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'46','KARVETINAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'47','SRIRANGARAJAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'48','PALASAMUDRAM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'49','GANGADHARA NELLORE');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'50','PENUMURU');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'51','PUTHALAPATTU');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'52','IRALA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'53','THAVANAMPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'54','CHITTOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'55','GUDIPALA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'56','YADAMARI');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'57','BANGARUPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'58','PALAMANER');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'59','GANGAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'60','PEDDA PANJANI');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'61','BAIREDDI PALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'62','VENKATAGIRI KOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'63','RAMA KUPPAM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'64','SANTHI PURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'65','GUDI PALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'66','KUPPAM');


insert into mandal(districtId,mandalCode,mandalName) Values(9,'1','SEETHARAMAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'2','VARIKUNTAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'3','KONDAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'4','JALADANKI');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'5','KAVALI');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'6','BOGOLE');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'7','KALIGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'8','VINJAMUR');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'9','DUTTALUR');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'10','UDAYAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'11','MARRIPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'12','ATMAKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'13','ANUMASAMUDRAMPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'14','DAGADARTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'15','ALLUR');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'16','VIDAVALUR');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'17','KODAVALUR');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'18','BUTCHIREDDIPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'19','SANGAM');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'20','CHEJERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'21','ANANTHASAGARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'22','KALUVOYA');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'23','RAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'24','PODLAKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'25','NELLORE');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'26','KOVUR');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'27','INDUKURPET');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'28','THOTAPALLIGUDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'29','MUTHUKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'30','VENKATACHALAM');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'31','MANUBOLU');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'32','GUDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'33','SYDAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'34','DAKKILI');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'35','VENKATAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'36','BALAYAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'37','OJILI');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'38','CHILLAKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'39','KOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'40','VAKADU');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'41','CHITTAMUR');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'42','NAIDUPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'43','PELLAKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'44','DORAVARISATRAM');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'45','SULLURPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'46','TADA');


insert into mandal(districtId,mandalCode,mandalName) Values(8,'1','YERRAGONDAPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'2','PULLALACHERUVU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'3','TRIPURANTHAKAM');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'4','KURICHEDU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'5','DONAKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'6','PEDAARAVEEDU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'7','DORNALA');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'8','ARDHAVEEDU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'9','MARKAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'10','TARLAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'11','KONAKANAMITLA');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'12','PODILI');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'13','DARSI');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'14','MUNDLAMURU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'15','THALLUR');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'16','ADDANKI');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'17','BALLIKURUVA');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'18','SANTHAMAGULURU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'19','YEDDANAPUDI');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'20','MARTUR');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'21','PARCHUR');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'22','KARAMCHEDU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'23','CHIRALA');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'24','VETAPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'25','INKOLLU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'26','JANAKAVARAMPANGULURU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'27','KORISAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'28','MADDIPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'29','CHIMAKURTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'30','MARRIPUDI');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'31','KANIGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'32','HANUMANTHUNIPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'33','BESTAVARIPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'34','CUMBUM');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'35','RACHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'36','GIDDALURU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'37','KOMAROLU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'38','CHADRASEKARAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'39','VELIGANDLA');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'40','PEDACHERLOPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'41','PONNALURU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'42','KONDAPI');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'43','SANTHANUTHLAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'44','ONGOLE');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'45','NAGULUPPALAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'46','CHINAGANJAM');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'47','KOTHAPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'48','TANGUTUR');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'49','ZARUGUMILLI');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'50','KANDUKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'51','VOLETIVARIPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'52','PAMUR');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'53','LINGASAMUDRAM');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'54','GUDLURU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'55','ULAVAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'56','SINGARAYAKONDA');



insert into mandal(districtId,mandalCode,mandalName) Values(7,'1','MACHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'2','RENTACRINTALA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'3','GURAZALA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'4','DACHEPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'5','MACHAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'6','BELLAMKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'7','ACHAMPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'8','KROSURU');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'9','AMARAVATHI');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'10','THULLUR');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'11','THADEPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'12','MANGALAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'13','TADIKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'14','PEDAKURAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'15','SATTENAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'16','RAJUPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'17','PIDUGURALLA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'18','KAREMPUDI');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'19','DURGI');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'20','VELDURTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'21','BOLLAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'22','NAKARIKALLU');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'23','MUPPALLA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'24','PHIRANGIPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'25','MEDIKONDURU');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'26','GUNTUR');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'27','PEDAKAKANI');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'28','DUGGIRALA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'29','KOLLIPARA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'30','KOLLUR');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'31','VEMURU');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'32','TENALI');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'33','TSUNDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'34','CHEBROLE');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'35','VATTICHERUKURU');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'36','PRATHIPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'37','EDLAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'38','NADENDLA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'39','NARASARAOPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'40','ROMPICHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'41','IPURU');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'42','SAVALYAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'43','VINUKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'44','NUZENDLA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'45','CHILAKALURIPET');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'46','PEDANANDIPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'47','KAKUMANU');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'48','PONNUR');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'49','AMRUTHALUR');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'50','CHERUKUPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'51','BHATTIPROLU');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'52','REPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'53','NAGARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'54','NIZAMPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'55','PITTALAVANIPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'56','KARLAPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'57','BAPATLA');


insert into mandal(districtId,mandalCode,mandalName) Values(6,'1','JAGGAYYAPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'2','VATSAVAI');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'3','PENUGANCHIPROLU');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'4','NANDIGAMA');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'5','CHANDARLAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'6','KANCHIKA CHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'7','VEERULLAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'8','IBRAHIMPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'9','G KONDURU');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'10','MYLAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'11','A KONDURU');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'12','GAMPALAGUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'13','TIRUVURU');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'14','VISSANNAPET');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'15','REDDIGUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'16','VIJAYAWADA RURAL');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'17','VIJAYAWADA URBAN');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'18','PENAMALURU');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'19','THOTLAVALLURU');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'20','KANKIPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'21','GANNAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'22','AGIRIPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'23','NUZVID');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'24','CHATRAI');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'25','MUSUNURU');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'26','BAPULAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'27','UNGUTURU');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'28','VUYYURU');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'29','PAMIDIMUKKALA');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'30','MOVVA');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'31','GHANTASALA');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'32','CHALLAPALLI');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'33','MOPIDEVI');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'34','AVANIGADDA');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'35','NAGAYALANKA');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'36','KODURU');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'37','MACHILIPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'38','GUDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'39','PAMARRU');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'40','PEDAPARUPUDI');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'41','NANDIVADA');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'42','GUDIVADA');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'43','GUDLAVALLERU');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'44','PEDANA');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'45','BANTUMILLI');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'46','MUDINEPALLI');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'47','MANDAVALLI');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'48','KAIKALUR');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'49','KALIDINDI');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'50','KRUTHIVENNU');



insert into mandal(districtId,mandalCode,mandalName) Values(5,'1','JEELUGUMILLI');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'2','BUTTAYAGUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'3','POLAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'4','THALLAPUDI');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'5','GOPALAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'6','KOYYALAGUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'7','JANGAREDDIGUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'8','T NARASAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'9','CHINTALAPUDI');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'10','LINGAPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'11','KAMAVARAPUKOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'12','DWARAKA TIRUMALA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'13','NALLAJERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'14','DEVARAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'15','CHAGALLU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'16','KOVVUR');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'17','NIDADAVOLE');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'18','TADEPALLIGUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'19','UNGUTURU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'20','BHIMADOLE');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'21','PEDAVEGI');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'22','PEDAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'23','ELURU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'24','DENDULURU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'25','NIDAMARRU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'26','GANAPAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'27','PENTAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'28','TANUKU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'29','UNDRAJAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'30','PERAVALI');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'31','IRAGAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'32','ATTILI');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'33','UNDI');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'34','AKIVEEDU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'35','KALLA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'36','BHEEMAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'37','PALAKODERU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'38','VEERAVASARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'39','PENUMANTRA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'40','PENUGONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'41','ACHANTA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'42','PODURU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'43','PALACOLE');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'44','YELAMANCHILI');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'45','NARASAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'46','MOGALTHUR');




insert into mandal(districtId,mandalCode,mandalName) Values(4,'1','MAREDUMILLI');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'2','Y RAMAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'3','ADDATEEGALA');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'4','RAJAVOMMANGI');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'5','KOTANANDURU');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'6','TUNI');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'7','THONDANGI');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'8','GOLLAPROLU');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'9','SANKHAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'10','PRATHIPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'11','YELESWARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'12','GANGAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'13','RAMPACHODAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'14','DEVIPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'15','SEETHANAGARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'16','KORUKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'17','GOKAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'18','JAGGAMPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'19','KIRLAMPUDI');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'20','PEDDAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'21','PITHAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'22','KOTHAPALLI');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'23','KAKINADA (RURAL)');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'24','KAKUNADA (URBAN)');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'25','SAMALKOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'26','RANGAMPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'27','GANDEPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'28','RAJANAGARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'29','RAJAHMUNDRY(RURAL)');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'30','RAJAHMUNDRY(URBAN)');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'31','KADIAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'32','MANDAPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'33','ANAPARTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'34','BICCAVOLU');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'35','PEDAPUDI');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'36','KARAPA');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'37','THALLAREVU');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'38','KAJULURU');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'39','RAMACHANDRAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'40','RAYAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'41','KAPILESWARAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'42','ALAMURU');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'43','ATREYAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'44','RAVULAPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'45','PAMARRU');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'46','KOTHAPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'47','P GANNAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'48','AMBAJIPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'49','AINAVILLI');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'50','MUMMIDIVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'51','I POLAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'52','KATRENIKONA');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'53','UPPALAGUPTAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'54','AMALAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'55','ALLAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'56','MAMIDIKUDURU');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'57','RAZOLE');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'57','RAZOLE');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'58','MALIKIPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'59','SAKHINETIPALLE');


insert into mandal(districtId,mandalCode,mandalName) Values(3,'1','MUNCHINGIPUTTU');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'2','PEDABAYALU');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'3','HUKUMPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'4','DUMBRIGUDA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'5','ARAKU VALLEY');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'6','ANANTHAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'7','DEVARAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'8','CHEEDIKADA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'9','MADUGULA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'10','PADERU');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'11','GANGARAJU MADUGULA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'12','CHINTAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'13','GUDEM KOTHAVEEDHI');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'14','KOYYURU');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'15','GOLUGONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'16','NATHAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'17','NARSIPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'18','ROLUGUNTA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'19','RAVIKAMATHAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'20','BUTCHAYYAPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'21','CHODAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'22','K KOTAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'23','SABBAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'24','PENDURTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'25','ANANDAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'26','PADMANABHAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'27','BHEEMUNIPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'28','VISAKHAPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'29','VISAKHAPATNAM(U)');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'30','GAJUWAKA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'31','PEDAGANTYADA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'32','PARAVADA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'33','ANAKAPALLI');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'34','MUNAGAPAKA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'35','KASIMKOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'36','MAKAVARAPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'37','KOTAURATLA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'38','PAYAKARAOPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'39','NAKKAPALLI');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'40','S RAYAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'41','YELAMANCHILI');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'42','RAMBILLI');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'43','ATCHUTAPURAM');


insert into mandal(districtId,mandalCode,mandalName) Values(2,'1','KOMARADA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'2','GUMMALAKSHMIPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'3','KURUPAM');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'4','JIYYAMMA VALASA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'5','GARUGUBILLI');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'6','PARVATHIPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'7','MAKKUVA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'8','SEETHANAGARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'9','BALAJIPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'10','BOBBILI');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'11','SALUR');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'12','PACHIPENTA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'13','RAMABHADRAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'14','BADANGI');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'15','THERLAM');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'16','MERAKAMUDIDAM');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'17','DATTIRAJERU');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'18','MENTADA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'19','GAJAPATHINAGARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'20','BONDAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'21','GURLA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'22','GARIVIDI');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'23','CHEEPURUPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'24','NELLIMARLA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'25','PUSAPATIREGA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'26','BHOGHAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'27','DENKADA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'28','VIZIANAGARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'29','GANTYADA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'30','SRUNGAVARAPUKOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'31','VEPADA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'32','LAKKAVARAPUKOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'33','JAMI');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'34','KOTHAVALASA');


insert into mandal(districtId,mandalCode,mandalName) Values(1,'1','VEERAGHATTAM');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'2','VANGARA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'3','REGIDIAMADALAVALASA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'4','RAJAM');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'5','GANGUVARI SINGADAM');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'6','LAVERU');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'7','RANASTALAM');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'8','ETCHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'9','PONDURU');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'10','SANTHAKAVITI');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'11','BURJA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'12','PALAKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'13','SEETHAMPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'14','BHAMINI');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'15','KOTHURU');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'16','HIRAMANDALAM');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'17','SARUBUJJILI');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'18','AMADALAVALASA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'19','SRIKAKULAM');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'20','GARA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'21','POLAKI');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'22','NARASANNAPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'23','JALUMURU');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'24','SARAVAKOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'25','PATHAPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'26','MEILAPUTTI');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'27','TEKKALI');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'28','KOTABOMMILI');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'29','SANTHABOMMALI');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'30','NANDIGAM');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'31','VAJRAPUKOTHURU');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'32','PALASA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'33','MANDASA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'34','SOMPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'35','KANCHILI');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'36','KAVITI');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'37','ICHCHAPURAM');

insert into mandal(districtId,mandalCode,mandalName) Values(1,'1','TALAMADUGU');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'2','TAMSI');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'3','ADILABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'4','JAINAD');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'5','BELA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'6','NARNOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'7','INDERAVELLY');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'8','GUDIHATHNUR');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'9','ICHODA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'10','BAZARHATHNOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'11','BOATH');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'12','NERADIGONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'13','SARANGAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'14','KUNTALA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'15','KUBEER');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'16','BHAINSA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'17','TANUR');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'18','MUDHOLE');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'19','LOHESRA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'20','DILAWARPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'21','NIRMAL');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'22','LAXMANCHANDA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'23','MAMDA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'24','KHANPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'25','KADDAMPEDDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'26','UTNUR');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'27','JAINOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'28','KERAMERI');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'29','SIRPUR (U)');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'30','JANNARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'31','DANDEPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'32','LUXETTIPET');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'33','MANCHERIAL');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'34','MANDAMARRI');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'35','KASIPET');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'36','TIRYANI');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'37','ASIFABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'38','WANKDI');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'39','KAGAZ NAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'40','REBBANA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'41','TANDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'42','BELLAMPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'43','NENNAL');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'44','BHEEMINI');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'45','SIRPUR (T)');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'46','KOUTHALA');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'47','BEJJUR');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'48','DAHEGAON');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'49','VEMANPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'50','KOTAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'51','CHENNUR');
insert into mandal(districtId,mandalCode,mandalName) Values(1,'52','JAIPUR');




insert into mandal(districtId,mandalCode,mandalName) Values(2,'1','D.HIRCHAL');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'2','BOMMANAHAL');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'3','VIDAPANAKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'4','VAJRAKARUR');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'5','GUNTAKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'6','GOOTY');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'7','PEDDAVADUGUR');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'8','YADIKI');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'9','TADPATRI');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'10','PEDDAPAPPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'11','SINGANAMALA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'12','PAMIDI');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'13','GARLADINNE');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'14','KUDAIR');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'15','URAVAKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'16','BELUGUPPA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'17','KANEKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'18','RAYADURG');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'19','GUMMAGATTA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'20','BRAHMASAMUDRAM');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'21','SETTUR');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'22','KUNDURPI');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'23','KALYANDURG');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'24','ATMAKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'25','ANANTAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'26','BUKKARAYASAMUDRAM');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'27','NARPALA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'28','PUTLUR');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'29','YELLANUR');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'30','TADIMARRI');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'31','BATHALAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'32','RAPTADU');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'33','KANAGANAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'34','KAMBADUR');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'35','RAMAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'36','CHENNE KOTHAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'37','DHARMAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'38','MUDIGUBBA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'39','TALUPULA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'40','NAMBULIPULIKUNTA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'41','TANAKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'42','NALLACHERUVU');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'43','GANDLAPENTA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'44','KADIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'45','AMADAGUR');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'46','OBULADEVARACHERUVU');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'47','NALLAMADA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'48','GORANTLA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'49','PUTTAPARTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'50','BUKKAPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'51','KOTHACHERUVU');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'52','PENU KONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'53','RODDAM');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'54','SOMANDEPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'55','CHILAMATHUR');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'56','LEPAKSHI');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'57','HINDUPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'58','PARIGI');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'59','MADAKASIRA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'60','GUDIBANDA');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'61','AMARAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'62','AGALI');
insert into mandal(districtId,mandalCode,mandalName) Values(2,'63','ROLLA');



insert into mandal(districtId,mandalCode,mandalName) Values(3,'1','PEDDAMANDYAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'2','THAMBALLAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'3','MULAKALACHERUVU');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'4','PEDDATHIPPASAMUDRAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'5','B KOTHAKOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'6','KURABALAKOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'7','GURRAMKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'8','KALAKADA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'9','KAMBHAMVARIPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'10','YERRAVARIPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'11','TIRUPATI URBAN');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'12','RENIGUNTA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'13','YERPEDU');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'14','SRIKALAHASTI');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'15','THOTTAMBEDU');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'16','BUCHINAIDU KHANDRIGA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'17','VARADAIAHPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'18','SATYAVEDU');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'19','NAGALAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'20','PICHATUR');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'21','VIJAYA PURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'22','NINDRA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'23','K V P PURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'24','NARAYANAVANAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'25','VADAMALAPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'26','TIRUPATI RURAL');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'27','RAMACHANDRAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'28','CHANDRAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'29','CHINNAGOTTIGALLU');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'30','ROMPICHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'31','PILERU');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'32','KALIKIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'33','VAYALPAD');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'34','NIMMANAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'35','MANDANPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'36','RAMASAMUDRAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'37','PUNGANUR');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'38','CHOWDEPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'39','SOMALA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'40','SODAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'41','PULICHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'42','PAKALA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'43','VEDURU KUPPAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'44','PUTTUR');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'45','NAGARI');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'46','KARVETINAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'47','SRIRANGARAJAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'48','PALASAMUDRAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'49','GANGADHARA NELLORE');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'50','PENUMURU');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'51','PUTHALAPATTU');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'52','IRALA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'53','THAVANAMPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'54','CHITTOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'55','GUDIPALA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'56','YADAMARI');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'57','BANGARUPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'58','PALAMANER');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'59','GANGAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'60','PEDDA PANJANI');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'61','BAIREDDI PALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'62','VENKATAGIRI KOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'63','RAMA KUPPAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'64','SANTHI PURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'65','GUDI PALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(3,'66','KUPPAM');



insert into mandal(districtId,mandalCode,mandalName) Values(4,'1','MAREDUMILLI');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'2','Y RAMAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'3','ADDATEEGALA');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'4','RAJAVOMMANGI');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'5','KOTANANDURU');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'6','TUNI');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'7','THONDANGI');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'8','GOLLAPROLU');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'9','SANKHAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'10','PRATHIPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'11','YELESWARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'12','GANGAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'13','RAMPACHODAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'14','DEVIPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'15','SEETHANAGARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'16','KORUKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'17','GOKAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'18','JAGGAMPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'19','KIRLAMPUDI');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'20','PEDDAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'21','PITHAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'22','KOTHAPALLI');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'23','KAKINADA (RURAL)');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'24','KAKUNADA (URBAN)');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'25','SAMALKOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'26','RANGAMPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'27','GANDEPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'28','RAJANAGARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'29','RAJAHMUNDRY(RURAL)');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'30','RAJAHMUNDRY(URBAN)');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'31','KADIAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'32','MANDAPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'33','ANAPARTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'34','BICCAVOLU');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'35','PEDAPUDI');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'36','KARAPA');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'37','THALLAREVU');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'38','KAJULURU');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'39','RAMACHANDRAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'40','RAYAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'41','KAPILESWARAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'42','ALAMURU');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'43','ATREYAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'44','RAVULAPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'45','PAMARRU');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'46','KOTHAPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'47','P GANNAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'48','AMBAJIPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'49','AINAVILLI');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'50','MUMMIDIVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'51','I POLAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'52','KATRENIKONA');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'53','UPPALAGUPTAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'54','AMALAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'55','ALLAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'56','MAMIDIKUDURU');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'57','RAZOLE');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'57','RAZOLE');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'58','MALIKIPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(4,'59','SAKHINETIPALLE');



insert into mandal(districtId,mandalCode,mandalName) Values(5,'1','MACHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'2','RENTACRINTALA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'3','GURAZALA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'4','DACHEPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'5','MACHAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'6','BELLAMKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'7','ACHAMPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'8','KROSURU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'9','AMARAVATHI');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'10','THULLUR');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'11','THADEPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'12','MANGALAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'13','TADIKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'14','PEDAKURAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'15','SATTENAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'16','RAJUPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'17','PIDUGURALLA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'18','KAREMPUDI');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'19','DURGI');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'20','VELDURTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'21','BOLLAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'22','NAKARIKALLU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'23','MUPPALLA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'24','PHIRANGIPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'25','MEDIKONDURU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'26','GUNTUR');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'27','PEDAKAKANI');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'28','DUGGIRALA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'29','KOLLIPARA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'30','KOLLUR');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'31','VEMURU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'32','TENALI');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'33','TSUNDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'34','CHEBROLE');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'35','VATTICHERUKURU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'36','PRATHIPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'37','EDLAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'38','NADENDLA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'39','NARASARAOPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'40','ROMPICHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'41','IPURU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'42','SAVALYAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'43','VINUKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'44','NUZENDLA');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'45','CHILAKALURIPET');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'46','PEDANANDIPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'47','KAKUMANU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'48','PONNUR');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'49','AMRUTHALUR');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'50','CHERUKUPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'51','BHATTIPROLU');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'52','REPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'53','NAGARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'54','NIZAMPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'55','PITTALAVANIPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'56','KARLAPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(5,'57','BAPATLA');




insert into mandal(districtId,mandalCode,mandalName) Values(6,'1','AMEERPET');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'2','TIRUMALGHERRY');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'3','MARREDPALLY');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'4','AMBERPET');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'5','HIMAYATNAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'6','NAMPALLY');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'7','SHAIKPET');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'8','KHAIRTABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'9','ASIFNAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'10','SAIDABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'11','BHADURPURA');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'12','BANDLAGUDA');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'13','SECUNDERABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'14','MUSHEERABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'15','GOLCONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(6,'16','CHARMINAR');




insert into mandal(districtId,mandalCode,mandalName) Values(7,'1','IBRAHIMPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'2','MALLAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'3','RAIKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'4','SARANGAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'5','DHARMAPURI');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'6','VELGATOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'7','RAMAGUNDAM');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'8','KAMANPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'9','MANTHANI');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'10','KATARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'11','MAHADEVPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'12','MUTHARAM MAHADEVPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'13','MALHARRAO');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'14','MUTHARAM MANTHANI');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'15','SRIRAMPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'16','PEDDAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'17','JULAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'18','DHARMARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'19','GOLLAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'20','JAGTIAL');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'21','MEDIPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'22','KORATLA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'23','METPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'24','KATHLAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'25','CHANDURTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'26','KODIMIAL');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'27','GANGADHARA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'28','MALLIAL');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'29','PEGADAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'30','CHOPPADANDI');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'31','SULTANABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'32','ODELA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'33','JAMMIKUNTA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'34','VEENAVANKA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'35','MANAKONDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'36','KARIMNAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'37','RAMADUGU');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'38','BOINPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'39','VEMULAWADA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'40','KONARAOPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'41','YELLA REDDI PETA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'42','GAMBHIRAOPET');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'43','MUSTABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'44','SIRSILLA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'45','ELLANTHAKUNTA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'46','BEJJANKI');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'47','THIMMAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'48','KESAVAPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'49','HUZURABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'50','KAMALAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'51','ELKATHURTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'52','SAIDAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'53','CHIGURUMAMIDI');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'54','KOHEDA');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'55','HUSNABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(7,'56','BHEEMADEVARPALLE');




insert into mandal(districtId,mandalCode,mandalName) Values(8,'1','WAZEED');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'2','VENKATAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'3','CHERLA');
into mandal(districtId,mandalCode,mandalName) Values(8,'4','PINAPAKA');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'5','GUNDALA');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'6','MANUGURU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'7','ASWAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'8','DUMMUGUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'9','BHADRACHALAM');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'10','KUNAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'11','CHINTUR');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'12','VARARAMACHANDRAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'13','VELAIRPAD');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'14','KUKUNOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'15','BURGAMPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'16','PALAWANCHA');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'17','KOTHAGUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'18','TEKULAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'19','YELLANDU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'20','SINGARENI');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'21','BAYYARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'22','GARLA');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'23','KAMEPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'24','JULURPAD');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'25','CHANDRUGONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'26','MULAKALAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'27','ASWARAOPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'28','DAMMAPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'29','SATHUPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'30','VEMSOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'31','PENUBALLI');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'32','KALLURU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'33','THALLADA');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'34','ENKURU');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'35','KONIJERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'36','KHAMMAM URBAN');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'37','KHAMMAM RURAL');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'38','THIRUMALAYAPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'39','KUSUMANCHI');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'40','NELAKONDAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'41','MUDIGONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'42','CHINTHAKANI');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'43','WYRA');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'44','BONAKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'45','MADHIRA');
insert into mandal(districtId,mandalCode,mandalName) Values(8,'46','YERRUPALEM');





insert into mandal(districtId,mandalCode,mandalName) Values(9,'1','JAGGAYYAPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'2','VATSAVAI');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'3','PENUGANCHIPROLU');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'4','NANDIGAMA');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'5','CHANDARLAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'6','KANCHIKA CHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'7','VEERULLAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'8','IBRAHIMPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'9','G KONDURU');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'10','MYLAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'11','A KONDURU');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'12','GAMPALAGUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'13','TIRUVURU');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'14','VISSANNAPET');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'15','REDDIGUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'16','VIJAYAWADA RURAL');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'17','VIJAYAWADA URBAN');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'18','PENAMALURU');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'19','THOTLAVALLURU');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'20','KANKIPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'21','GANNAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'22','AGIRIPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'23','NUZVID');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'24','CHATRAI');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'25','MUSUNURU');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'26','BAPULAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'27','UNGUTURU');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'28','VUYYURU');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'29','PAMIDIMUKKALA');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'30','MOVVA');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'31','GHANTASALA');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'32','CHALLAPALLI');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'33','MOPIDEVI');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'34','AVANIGADDA');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'35','NAGAYALANKA');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'36','KODURU');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'37','MACHILIPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'38','GUDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'39','PAMARRU');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'40','PEDAPARUPUDI');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'41','NANDIVADA');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'42','GUDIVADA');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'43','GUDLAVALLERU');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'44','PEDANA');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'45','BANTUMILLI');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'46','MUDINEPALLI');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'47','MANDAVALLI');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'48','KAIKALUR');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'49','KALIDINDI');
insert into mandal(districtId,mandalCode,mandalName) Values(9,'50','KRUTHIVENNU');



insert into mandal(districtId,mandalCode,mandalName) Values(10,'1','KOWTHALAM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'2','KOSIGI');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'3','MANTRALAYAM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'4','NANDAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'5','C.BELAGAL');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'6','GUDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'7','KURNOOL');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'8','NANDI KOTKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'9','PAGIDYALA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'10','KOTHAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'11','ATMAKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'12','SRISAILAM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'13','VELGODU');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'14','PAMULAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'15','JUPADU BUNGALOW');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'16','MIDTHUR');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'17','ORVAKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'18','KALLUR');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'19','KODUMUR');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'20','GONEGANDLA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'21','YEMMIGANUR');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'22','PEDDA KADUBUR');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'23','ADONI');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'24','HOLAGUNDA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'25','ALUR');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'26','ASPARI');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'27','DEVANAKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'28','KRISHNAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'29','VELDURTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'30','BETHAMCHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'31','PANYAM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'32','GADIVEMULA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'33','BANDI ATMAKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'34','NANDYAL');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'35','MAHANANDI');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'36','SIRVEL');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'37','RUDRAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'38','ALLAGADDA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'39','CHAGALAMARRI');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'40','UYYALAWADA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'41','DORNIPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'42','GOSPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'43','KOILKUNTLA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'44','BANAGANAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'45','SANJAMALA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'46','KOLIMIGUNDLA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'47','OWK');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'48','PEAPALLY');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'49','DHONE');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'50','TUGGALI');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'51','PATTIKANDA');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'52','MADDIKERA EAST');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'53','CHIPPAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(10,'54','HALAHARVI');




insert into mandal(districtId,mandalCode,mandalName) Values(11,'1','KODANGAL');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'2','BOMRASPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'3','KOSGI');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'4','DOULATABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'5','DAMARAGIDDA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'6','MADDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'7','KOILKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'8','HANWADA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'9','NAWABPET');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'10','BALANAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'11','KONDURG');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'12','FAROOQNAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'13','KOTHUR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'14','KESHAMPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'15','TALAKONDAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'16','AMANGAL');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'17','MADGUL');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'18','VANGOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'19','VELDANDA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'20','KALWAKURTHY');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'21','MIDJIL');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'22','THIMMAJIPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'23','JADCHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'24','BHOOTHPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'25','MAHBUBNAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'26','ADDAKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'27','DEVARKADARA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'28','DHANWADA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'29','NARAYANPET');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'30','UTKOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'31','MAGANOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'32','MAKTHAL');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'33','NARVA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'34','CHINNA CHINTA KUNTA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'35','ATMAKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'36','KOTHAKOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'37','PEDDAMANDADI');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'38','GHANPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'39','BIJINAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'40','NAGAR KURNOOL');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'41','TADOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'42','TELKAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'43','UPPUNUNTHALA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'44','ACHAMPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'45','AMRABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'46','BALMOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'47','LINGAL');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'48','PEDDAKOTHAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'49','KODAIR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'50','GOPALPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'51','WANAPARTHY');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'52','PANGAL');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'53','PEBBAIR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'54','GADWAL');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'55','DHARUR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'56','MALDAKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'57','GHATTU');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'58','AIZA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'59','WADDEPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'60','ITIKYAL');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'61','MANOPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'62','ALAMPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'63','VEEPANGANDLA');
insert into mandal(districtId,mandalCode,mandalName) Values(11,'64','KOLLAPUR');




insert into mandal(districtId,mandalCode,mandalName) Values(12,'1','MANOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'2','KANGTI');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'3','KALHER');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'4','NARAYANKHED');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'5','REGODE');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'6','SHANKARAMPET (A)');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'7','ALLADURG');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'8','TEKMAL');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'9','PAPANNAPET');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'10','KULCHARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'11','MEDAK');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'12','SHANKARAMPET (R)');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'13','RAMAYAMPET');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'14','DUBBAK');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'15','MIRDODDI');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'16','SIDDIPET');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'17','CHINNA KODUR');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'18','NANGANUR');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'19','KONDAPAK');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'20','JAGDEVPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'21','GAJWEL');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'22','DOULTABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'23','CHEGUNTA');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'24','YELDURTHY');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'25','KOWDIPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'26','ANDOLE');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'27','RAIKODE');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'28','NYALKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'29','JHARASANGAM');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'30','ZAHIRABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'31','KOHIR');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'32','MUNPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'33','PULKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'34','SADASIVPET');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'35','KONDAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'36','SANGAREDDY');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'37','PATANCHERU');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'38','RAMACHANDRAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'39','JINNARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'40','HATHNOORA');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'41','NARSAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'42','SHIVAMPET');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'43','TUPRAN');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'44','WARGAL');
insert into mandal(districtId,mandalCode,mandalName) Values(12,'45','MULUG');



insert into mandal(districtId,mandalCode,mandalName) Values(13,'1','BOMMALARAMARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'2','M TURKAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'3','RAJAPET');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'4','YADAGIRIGUTTA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'5','ALAIR');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'6','GUNDALA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'7','THIRUMALAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'8','THUNGA THURTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'9','NUTHANKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'10','ATMAKUR (S)');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'11','JAJI REDDI GUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'12','SALIGOURARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'13','MOTHKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'14','ATMAKUR (M)');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'15','VALIGONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'16','BHUVANAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'17','BIBINAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'18','POCHAMPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'19','CHOUTUPPAL');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'20','RAMANNAPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'21','CHITYALA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'22','NARKETPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'23','KATTANGOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'24','NAKREKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'25','KETHEPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'26','SURYAPET');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'27','CHIVVEMLA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'28','MOTHEY');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'29','NADIGUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'30','MUNAGALA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'31','PENPAHAD');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'32','VEMULAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'33','THIPPARTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'34','NALGONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'35','MUNUGODE');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'36','NARAYANAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'37','MARRI GUDA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'38','CHANDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'39','KANGAL');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'40','NIDAMANUR');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'41','THRIPURARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'42','MIRYALAGUDA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'43','GARIDE PALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'44','CHILKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'45','KODAD');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'46','MELLACHERVU');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'47','HUZURNAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'48','MATTAMPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'49','NERED CHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'50','DAMERACHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'51','ANUMULA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'52','PEDDAVURA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'53','PEDDA ADISERLAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'54','GURRAMPODE');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'55','NAMPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'56','CHINTHA PALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'57','DEVARAKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'58','GUNDLA PALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(13,'59','CHANDAM PET');



insert into mandal(districtId,mandalCode,mandalName) Values(14,'1','RANJAL');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'2','NAVIPET');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'3','NANDIPET');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'4','ARMUR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'5','BALKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'6','MORTAD');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'7','KAMMAR PALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'8','BHEEMGAL');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'9','VELPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'10','JAKRANPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'11','MAKLOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'12','NIZAMABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'13','YEDA PALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'14','BODHAN');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'15','KOTGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'16','MADNUR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'17','JUKKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'18','BICHKUNDA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'19','BIRKOOR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'20','VARNI');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'21','DICH PALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'22','DHAR PALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'23','SIRKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'24','MACHAREDDY');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'25','SADASIVANAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'26','GANDHARI');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'27','BANSWADA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'28','PITLAM');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'29','NIZAM SAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'30','YELLAREDDY');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'31','NAGA REDDIPET');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'32','LINGAMPET');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'33','TADWAI');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'34','KAMAREDDY');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'35','BHIKNUR');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'36','DOMAKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(14,'37','ICHCHAPURAM');



insert into mandal(districtId,mandalCode,mandalName) Values(15,'1','YERRAGONDAPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'2','PULLALACHERUVU');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'3','TRIPURANTHAKAM');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'4','KURICHEDU');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'5','DONAKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'6','PEDAARAVEEDU');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'7','DORNALA');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'8','ARDHAVEEDU');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'9','MARKAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'10','TARLAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'11','KONAKANAMITLA');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'12','PODILI');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'13','DARSI');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'14','MUNDLAMURU');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'15','THALLUR');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'16','ADDANKI');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'17','BALLIKURUVA');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'18','SANTHAMAGULURU');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'19','YEDDANAPUDI');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'20','MARTUR');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'21','PARCHUR');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'22','KARAMCHEDU');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'23','CHIRALA');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'24','VETAPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'25','INKOLLU');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'26','JANAKAVARAMPANGULURU');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'27','KORISAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'28','MADDIPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'29','CHIMAKURTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'30','MARRIPUDI');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'31','KANIGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'32','HANUMANTHUNIPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'33','BESTAVARIPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'34','CUMBUM');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'35','RACHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'36','GIDDALURU');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'37','KOMAROLU');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'38','CHADRASEKARAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'39','VELIGANDLA');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'40','PEDACHERLOPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'41','PONNALURU');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'42','KONDAPI');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'43','SANTHANUTHLAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'44','ONGOLE');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'45','NAGULUPPALAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'46','CHINAGANJAM');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'47','KOTHAPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'48','TANGUTUR');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'49','ZARUGUMILLI');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'50','KANDUKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'51','VOLETIVARIPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'52','PAMUR');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'53','LINGASAMUDRAM');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'54','GUDLURU');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'55','ULAVAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(15,'56','SINGARAYAKONDA');



insert into mandal(districtId,mandalCode,mandalName) Values(16,'1','MARPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'2','MOMINPET');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'3','NAWABPET');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'4','SHANKARPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'5','SERILINGAMPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'6','BALANAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'7','QUTHBULLAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'8','MEDCHAL');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'9','SHAMIRPET');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'10','MALKAJGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'11','KEESARA');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'12','GHATKESAR');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'13','UPPAL');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'14','HAYATHNAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'15','SAROORNAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'16','RAJENDRANAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'17','MOINABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'18','CHEVELLA');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'19','VIKARABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'20','DHARUR');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'21','BANTARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'22','PEDDEMUL');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'23','TANDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'24','BASHEERABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'25','YELAL');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'26','DOMA');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'27','GANDEED');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'28','KULKACHARLA');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'29','PARGI');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'30','PUDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'31','SHABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'32','SHAMSHABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'33','MAHESWARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'34','IBRAHIMPATAM');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'35','MANCHAL');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'36','YACHARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(16,'37','KANDUKUR');




insert into mandal(districtId,mandalCode,mandalName) Values(17,'1','VEERAGHATTAM');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'2','VANGARA');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'3','REGIDIAMADALAVALASA');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'4','RAJAM');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'5','GANGUVARI SINGADAM');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'6','LAVERU');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'7','RANASTALAM');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'8','ETCHERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'9','PONDURU');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'10','SANTHAKAVITI');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'11','BURJA');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'12','PALAKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'13','SEETHAMPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'14','BHAMINI');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'15','KOTHURU');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'16','HIRAMANDALAM');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'17','SARUBUJJILI');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'18','AMADALAVALASA');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'19','SRIKAKULAM');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'20','GARA');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'21','POLAKI');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'22','NARASANNAPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'23','JALUMURU');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'24','SARAVAKOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'25','PATHAPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'26','MEILAPUTTI');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'27','TEKKALI');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'28','KOTABOMMILI');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'29','SANTHABOMMALI');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'30','NANDIGAM');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'31','VAJRAPUKOTHURU');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'32','PALASA');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'33','MANDASA');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'34','SOMPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'35','KANCHILI');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'36','KAVITI');
insert into mandal(districtId,mandalCode,mandalName) Values(17,'37','ICHCHAPURAM');



insert into mandal(districtId,mandalCode,mandalName) Values(18,'1','SEETHARAMAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'2','VARIKUNTAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'3','KONDAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'4','JALADANKI');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'5','KAVALI');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'6','BOGOLE');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'7','KALIGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'8','VINJAMUR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'9','DUTTALUR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'10','UDAYAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'11','MARRIPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'12','ATMAKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'13','ANUMASAMUDRAMPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'14','DAGADARTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'15','ALLUR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'16','VIDAVALUR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'17','KODAVALUR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'18','BUTCHIREDDIPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'19','SANGAM');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'20','CHEJERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'21','ANANTHASAGARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'22','KALUVOYA');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'23','RAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'24','PODLAKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'25','NELLORE');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'26','KOVUR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'27','INDUKURPET');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'28','THOTAPALLIGUDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'29','MUTHUKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'30','VENKATACHALAM');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'31','MANUBOLU');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'32','GUDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'33','SYDAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'34','DAKKILI');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'35','VENKATAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'36','BALAYAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'37','OJILI');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'38','CHILLAKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'39','KOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'40','VAKADU');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'41','CHITTAMUR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'42','NAIDUPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'43','PELLAKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'44','DORAVARISATRAM');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'45','SULLURPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(18,'46','TADA');



insert into mandal(districtId,mandalCode,mandalName) Values(19,'1','MUNCHINGIPUTTU');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'2','PEDABAYALU');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'3','HUKUMPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'4','DUMBRIGUDA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'5','ARAKU VALLEY');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'6','ANANTHAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'7','DEVARAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'8','CHEEDIKADA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'9','MADUGULA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'10','PADERU');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'11','GANGARAJU MADUGULA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'12','CHINTAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'13','GUDEM KOTHAVEEDHI');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'14','KOYYURU');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'15','GOLUGONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'16','NATHAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'17','NARSIPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'18','ROLUGUNTA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'19','RAVIKAMATHAM');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'20','BUTCHAYYAPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'21','CHODAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'22','K KOTAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'23','SABBAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'24','PENDURTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'25','ANANDAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'26','PADMANABHAM');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'27','BHEEMUNIPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'28','VISAKHAPATNAM');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'29','VISAKHAPATNAM(U)');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'30','GAJUWAKA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'31','PEDAGANTYADA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'32','PARAVADA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'33','ANAKAPALLI');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'34','MUNAGAPAKA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'35','KASIMKOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'36','MAKAVARAPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'37','KOTAURATLA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'38','PAYAKARAOPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'39','NAKKAPALLI');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'40','S RAYAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'41','YELAMANCHILI');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'42','RAMBILLI');
insert into mandal(districtId,mandalCode,mandalName) Values(19,'43','ATCHUTAPURAM');





insert into mandal(districtId,mandalCode,mandalName) Values(20,'1','KOMARADA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'2','GUMMALAKSHMIPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'3','KURUPAM');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'4','JIYYAMMA VALASA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'5','GARUGUBILLI');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'6','PARVATHIPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'7','MAKKUVA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'8','SEETHANAGARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'9','BALAJIPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'10','BOBBILI');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'11','SALUR');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'12','PACHIPENTA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'13','RAMABHADRAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'14','BADANGI');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'15','THERLAM');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'16','MERAKAMUDIDAM');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'17','DATTIRAJERU');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'18','MENTADA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'19','GAJAPATHINAGARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'20','BONDAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'21','GURLA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'22','GARIVIDI');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'23','CHEEPURUPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'24','NELLIMARLA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'25','PUSAPATIREGA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'26','BHOGHAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'27','DENKADA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'28','VIZIANAGARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'29','GANTYADA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'30','SRUNGAVARAPUKOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'31','VEPADA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'32','LAKKAVARAPUKOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'33','JAMI');
insert into mandal(districtId,mandalCode,mandalName) Values(20,'34','KOTHAVALASA');



insert into mandal(districtId,mandalCode,mandalName) Values(21,'1','CHERIYAL');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'2','MADDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'3','NARMETTA');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'4','BACHANNAPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'5','JANGAON');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'6','LINGALA GHANPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'7','RAGHUNATHA PALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'8','GHANPUR(stn)');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'9','DHARMASAGAR');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'10','HASANPARTHY');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'11','HANAMKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'12','WARDHANNAPET');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'13','ZAFFERGADH');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'14','PALAKURTHI');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'15','DEVARUPPULA');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'16','KODAKANDLA');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'17','RAIPARTHY');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'18','THORRUR');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'19','NELLIKUDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'20','NARSIMHULAPET');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'21','MARIPEDA');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'22','DORNAKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'23','KURAVI');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'24','MAHABUBABAD');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'25','KESAMUDRAM');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'26','NEKKONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'27','GUDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'28','KOTHAGUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'29','KHANAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'30','NARSAMPET');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'31','CHENNARAOPET');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'32','PARVATHAGIRI');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'33','SANGEM');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'34','NALLABELLY');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'35','DUGGONDI');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'36','GEESUGONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'37','ATMAKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'38','SHAYAMPET');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'39','PARKAL');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'40','REGONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'41','MOGULLAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'42','CHITYAL');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'43','BHUPALPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'44','GHANAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'45','MULUG');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'46','VENKATAPUR');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'47','GOVINDARAOPET');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'48','TADVAI');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'49','ETURNAGARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(21,'50','MANGAPET');




insert into mandal(districtId,mandalCode,mandalName) Values(22,'1','JEELUGUMILLI');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'2','BUTTAYAGUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'3','POLAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'4','THALLAPUDI');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'5','GOPALAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'6','KOYYALAGUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'7','JANGAREDDIGUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'8','T NARASAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'9','CHINTALAPUDI');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'10','LINGAPALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'11','KAMAVARAPUKOTA');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'12','DWARAKA TIRUMALA');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'13','NALLAJERLA');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'14','DEVARAPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'15','CHAGALLU');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'16','KOVVUR');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'17','NIDADAVOLE');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'18','TADEPALLIGUDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'19','UNGUTURU');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'20','BHIMADOLE');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'21','PEDAVEGI');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'22','PEDAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'23','ELURU');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'24','DENDULURU');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'25','NIDAMARRU');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'26','GANAPAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'27','PENTAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'28','TANUKU');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'29','UNDRAJAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'30','PERAVALI');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'31','IRAGAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'32','ATTILI');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'33','UNDI');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'34','AKIVEEDU');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'35','KALLA');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'36','BHEEMAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'37','PALAKODERU');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'38','VEERAVASARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'39','PENUMANTRA');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'40','PENUGONDA');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'41','ACHANTA');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'42','PODURU');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'43','PALACOLE');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'44','YELAMANCHILI');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'45','NARASAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(22,'46','MOGALTHUR');



insert into mandal(districtId,mandalCode,mandalName) Values(23,'1','KONDAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'2','MYLAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'3','PEDDAMUDIUM');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'4','RAJU PALEM');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'5','DUVVUR');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'6','S MYDUKUR');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'7','BRAHMAMGARIMATTAM');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'8','B KODUR');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'9','KALASAPADU');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'10','PORUMAMILLA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'11','BADVEL');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'12','GOPAVARAM');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'13','KHAJIPET');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'14','CHAPAD');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'15','PRODDUTUR');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'16','JAMMALAMADUGU');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'17','MUDDANUR');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'18','SIMHADRIPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'19','LINGALA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'20','PULIVENDLA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'21','VEMULA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'22','THANDUR');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'23','VEERAPUNAYUNIPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'24','YERRAGUNTLA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'25','KAMALAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'26','VALLUR');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'27','CHENNUR');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'28','ATLUR');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'29','VONTIMITTA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'30','SIDHOUT');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'31','CUDDAPAH');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'32','CHINTHA KOMMADINNE');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'33','PENDLIMARRI');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'34','VEMPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'35','CHAKRAYAPET');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'36','LAKKIREDDIPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'37','RAMAPURAM');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'38','VEERABALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'39','RAJAMPET');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'40','NANDALUR');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'41','PENAGALURU');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'42','CHITVEL');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'43','KODUR');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'44','OBULAVARIPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'45','PULLAMPETA');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'46','T SUNDUPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'47','SAMBEPALLE');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'48','CHINNAMANDEM');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'48','RAYACHOTI');
insert into mandal(districtId,mandalCode,mandalName) Values(23,'49','GALIVEEDU');




/* Inserting Queries for Villages */
delete from village;

insert into village(mandalId,villageCode,villageName) Values(630,'1','CHINAPARVATHA PUR ');
insert into village(mandalId,villageCode,villageName) Values(630,'2','THIMMA PURAM ');
insert into village(mandalId,villageCode,villageName) Values(630,'3','BOIN PALLE ');
insert into village(mandalId,villageCode,villageName) Values(630,'4','SOMAJI PALLE ');
insert into village(mandalId,villageCode,villageName) Values(630,'5','MUNEERABAD ');
insert into village(mandalId,villageCode,villageName) Values(630,'6','BANDAKADI PALLE ');
insert into village(mandalId,villageCode,villageName) Values(630,'7','THUMKUNTA ');
insert into village(mandalId,villageCode,villageName) Values(630,'8','JALALPUR ');
insert into village(mandalId,villageCode,villageName) Values(630,'9','PYARARAM ');
insert into village(mandalId,villageCode,villageName) Values(630,'10','SOLIPETA ');
insert into village(mandalId,villageCode,villageName) Values(630,'11','CHEEKATI MAMIDI ');
insert into village(mandalId,villageCode,villageName) Values(630,'12','MARYALA ');
insert into village(mandalId,villageCode,villageName) Values(630,'13','MALYALA ');
insert into village(mandalId,villageCode,villageName) Values(630,'14','YAVAPUR ');
insert into village(mandalId,villageCode,villageName) Values(630,'15','RAMGA PURAM ');
insert into village(mandalId,villageCode,villageName) Values(630,'16','RAMLINGAM PALLE ');
insert into village(mandalId,villageCode,villageName) Values(630,'17','PEDDA PARVATHAPUR ');
insert into village(mandalId,villageCode,villageName) Values(630,'18','BOMMALARAMARAM ');
insert into village(mandalId,villageCode,villageName) Values(630,'19','TIRUMALAGIRI ');
insert into village(mandalId,villageCode,villageName) Values(630,'20','NAGENENI PALLE ');
insert into village(mandalId,villageCode,villageName) Values(630,'21','MAISI REDDY PALLE ');
insert into village(mandalId,villageCode,villageName) Values(630,'22','HAZIPUR ');
insert into village(mandalId,villageCode,villageName) Values(630,'23','MAILARAM ');
insert into village(mandalId,villageCode,villageName) Values(630,'24','MEDI PALLE ');



insert into village(mandalId,villageCode,villageName) Values(631,'1','GOPAL PURAM ');
insert into village(mandalId,villageCode,villageName) Values(631,'2','KONDAPUR ');
insert into village(mandalId,villageCode,villageName) Values(631,'3','SRINIVASAPUR ');
insert into village(mandalId,villageCode,villageName) Values(631,'4','TIRUMALAPUR ');
insert into village(mandalId,villageCode,villageName) Values(631,'5','VEERAREDDI PALLE ');
insert into village(mandalId,villageCode,villageName) Values(631,'6','GANDAMALLA ');
insert into village(mandalId,villageCode,villageName) Values(631,'7','KONAPUR ');
insert into village(mandalId,villageCode,villageName) Values(631,'8','IBRAHIMPUR ');
insert into village(mandalId,villageCode,villageName) Values(631,'9','DATTAI PALLE ');
insert into village(mandalId,villageCode,villageName) Values(631,'10','VELPUPALLE ');
insert into village(mandalId,villageCode,villageName) Values(631,'11','KOMATIKUNTA ');
insert into village(mandalId,villageCode,villageName) Values(631,'12','VENKATAPUR ');
insert into village(mandalId,villageCode,villageName) Values(631,'13','MANNEVARI TURKAPALLE ');
insert into village(mandalId,villageCode,villageName) Values(631,'14','VASALA MARRI ');
insert into village(mandalId,villageCode,villageName) Values(631,'15','MALKA PURAM ');
insert into village(mandalId,villageCode,villageName) Values(631,'16','LAXMA PURAM ');
insert into village(mandalId,villageCode,villageName) Values(631,'17','NAGAI PALLE ');
insert into village(mandalId,villageCode,villageName) Values(631,'18','MADHA PURAM ');
insert into village(mandalId,villageCode,villageName) Values(631,'19','DHARMARAM ');
insert into village(mandalId,villageCode,villageName) Values(631,'20','MULAKALA PALLE ');
insert into village(mandalId,villageCode,villageName) Values(631,'21','PALLE PAHAD ');
insert into village(mandalId,villageCode,villageName) Values(631,'22','RUSTA PURAM');




insert into village(mandalId,villageCode,villageName) Values(632,'1','SINGARAM ');
insert into village(mandalId,villageCode,villageName) Values(632,'2','JALA ');
insert into village(mandalId,villageCode,villageName) Values(632,'3','KURRARAM ');
insert into village(mandalId,villageCode,villageName) Values(632,'4','BURUGU PALLE ');
insert into village(mandalId,villageCode,villageName) Values(632,'5','BONDUGULA ');
insert into village(mandalId,villageCode,villageName) Values(632,'6','PARU PALLE ');
insert into village(mandalId,villageCode,villageName) Values(632,'7','PAMUKUNTA ');
insert into village(mandalId,villageCode,villageName) Values(632,'8','NARSAPUR ');
insert into village(mandalId,villageCode,villageName) Values(632,'9','RENIKUNTA ');
insert into village(mandalId,villageCode,villageName) Values(632,'10','RAJAPET ');
insert into village(mandalId,villageCode,villageName) Values(632,'11','NEMLA ');
insert into village(mandalId,villageCode,villageName) Values(632,'12','SOMARAM ');
insert into village(mandalId,villageCode,villageName) Values(632,'13','LAKSHMAKKA PALLE ');
insert into village(mandalId,villageCode,villageName) Values(632,'14','DOODI VENKATAPUR ');
insert into village(mandalId,villageCode,villageName) Values(632,'15','BASANTHAPUR ');
insert into village(mandalId,villageCode,villageName) Values(632,'16','KALAPALLE ');
insert into village(mandalId,villageCode,villageName) Values(632,'17','BEGUMPET ');
insert into village(mandalId,villageCode,villageName) Values(632,'18','CHALLUR ');
insert into village(mandalId,villageCode,villageName) Values(632,'19','RAGHUNATHPUR ');





insert into village(mandalId,villageCode,villageName) Values(633,'1','MALLAPUR');
insert into village(mandalId,villageCode,villageName) Values(633,'2','GOWRAI PALLE');
insert into village(mandalId,villageCode,villageName) Values(633,'3','SADU VELLE');
insert into village(mandalId,villageCode,villageName) Values(633,'4','KACHARAM');
insert into village(mandalId,villageCode,villageName) Values(633,'5','MASAIPET');
insert into village(mandalId,villageCode,villageName) Values(633,'6','PEDDAKANDUKUR');
insert into village(mandalId,villageCode,villageName) Values(633,'7','SAIDAPUR');
insert into village(mandalId,villageCode,villageName) Values(633,'8','YADAGIRI PALLE (P)U');
insert into village(mandalId,villageCode,villageName) Values(633,'9','DATHAR PALLE');
insert into village(mandalId,villageCode,villageName) Values(633,'10','JANGAM PALLE');
insert into village(mandalId,villageCode,villageName) Values(633,'11','GUNDLA PALLE');
insert into village(mandalId,villageCode,villageName) Values(633,'12','VANGA PALLE');
insert into village(mandalId,villageCode,villageName) Values(633,'13','RAMOJIPET');
insert into village(mandalId,villageCode,villageName) Values(633,'14','CHINNAKANDUKUR');
insert into village(mandalId,villageCode,villageName) Values(633,'15','CHOLLER');
insert into village(mandalId,villageCode,villageName) Values(633,'16','MOOTA KONDUR');
insert into village(mandalId,villageCode,villageName) Values(633,'17','VARTOOR');



insert into village(mandalId,villageCode,villageName) Values(634,'1','KOLANUPAKA');
insert into village(mandalId,villageCode,villageName) Values(634,'2','SRINIVASA PURAM');
insert into village(mandalId,villageCode,villageName) Values(634,'3','PATELGUDA');
insert into village(mandalId,villageCode,villageName) Values(634,'4','TANGUTOOR');
insert into village(mandalId,villageCode,villageName) Values(634,'5','ALAIR');
insert into village(mandalId,villageCode,villageName) Values(634,'6','BAHADURPET');
insert into village(mandalId,villageCode,villageName) Values(634,'7','MANTHAPURI');
insert into village(mandalId,villageCode,villageName) Values(634,'8','KOLLUR');
insert into village(mandalId,villageCode,villageName) Values(634,'9','SHARAJPET');
insert into village(mandalId,villageCode,villageName) Values(634,'10','GOLANKONDA');
insert into village(mandalId,villageCode,villageName) Values(634,'11','AMMAN BOLE');
insert into village(mandalId,villageCode,villageName) Values(634,'12','MATOOR');
insert into village(mandalId,villageCode,villageName) Values(634,'13','IKKURTHI');
insert into village(mandalId,villageCode,villageName) Values(634,'14','DILAWARPUR');


insert into village(mandalId,villageCode,villageName) Values(635,'1','KOMME PALLE');
insert into village(mandalId,villageCode,villageName) Values(635,'2','ANANTARAM');
insert into village(mandalId,villageCode,villageName) Values(635,'3','VELMAJALA');
insert into village(mandalId,villageCode,villageName) Values(635,'4','SEETARAMPUR');
insert into village(mandalId,villageCode,villageName) Values(635,'5','MARPADAGA');
insert into village(mandalId,villageCode,villageName) Values(635,'6','GANGAPUR');
insert into village(mandalId,villageCode,villageName) Values(635,'7','MASAN PALLE');
insert into village(mandalId,villageCode,villageName) Values(635,'8','RAMARAM');
insert into village(mandalId,villageCode,villageName) Values(635,'9','BRAHMAN PALLE');
insert into village(mandalId,villageCode,villageName) Values(635,'10','SUDDALA');
insert into village(mandalId,villageCode,villageName) Values(635,'11','TERYALA');
insert into village(mandalId,villageCode,villageName) Values(635,'12','PALLE PAHAD');
insert into village(mandalId,villageCode,villageName) Values(635,'13','PARU PALLE');
insert into village(mandalId,villageCode,villageName) Values(635,'14','AMBALA');
insert into village(mandalId,villageCode,villageName) Values(635,'15','GUNDALA');
insert into village(mandalId,villageCode,villageName) Values(635,'16','BANDAKOTHA PALLE');
insert into village(mandalId,villageCode,villageName) Values(635,'17','VASTHA KONDUR');
insert into village(mandalId,villageCode,villageName) Values(635,'18','PEDDA PADISHALA');
insert into village(mandalId,villageCode,villageName) Values(635,'19','TURKALA SHAPUR');
insert into village(mandalId,villageCode,villageName) Values(635,'20','VANGALA');


insert into village(mandalId,villageCode,villageName) Values(636,'1','THATI PAMULA');
insert into village(mandalId,villageCode,villageName) Values(636,'2','NANDA PURAM');
insert into village(mandalId,villageCode,villageName) Values(636,'3','THIRUMALAGIRI');
insert into village(mandalId,villageCode,villageName) Values(636,'4','THONDA');
insert into village(mandalId,villageCode,villageName) Values(636,'5','VELCHALA');
insert into village(mandalId,villageCode,villageName) Values(636,'6','MAMIDIYALA');
insert into village(mandalId,villageCode,villageName) Values(636,'7','JALAL PURAM');
insert into village(mandalId,villageCode,villageName) Values(636,'8','SIDDA SAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(636,'9','GUNDEPURI');
insert into village(mandalId,villageCode,villageName) Values(636,'10','BANDLA PALLE');
insert into village(mandalId,villageCode,villageName) Values(636,'11','MALI PURAM');
insert into village(mandalId,villageCode,villageName) Values(636,'12','MAMIDI PALLE');
insert into village(mandalId,villageCode,villageName) Values(636,'13','CHENNA PURAM');
insert into village(mandalId,villageCode,villageName) Values(636,'14','PHANIGIRI');
insert into village(mandalId,villageCode,villageName) Values(636,'15','ETOOR');
insert into village(mandalId,villageCode,villageName) Values(636,'16','ANANTHARAM');


insert into village(mandalId,villageCode,villageName) Values(637,'1','GOTTIPARTHI');
insert into village(mandalId,villageCode,villageName) Values(637,'2','RAVULA PALLE');
insert into village(mandalId,villageCode,villageName) Values(637,'3','MANAPUR');
insert into village(mandalId,villageCode,villageName) Values(637,'4','KUKKADAM');
insert into village(mandalId,villageCode,villageName) Values(637,'5','KUNTA PALLE');
insert into village(mandalId,villageCode,villageName) Values(637,'6','REDDIGUDA');
insert into village(mandalId,villageCode,villageName) Values(637,'7','RAMACHANDRAPUR');
insert into village(mandalId,villageCode,villageName) Values(637,'8','GUMMADAVALLY');
insert into village(mandalId,villageCode,villageName) Values(637,'9','VEMPATI');
insert into village(mandalId,villageCode,villageName) Values(637,'10','BANDARAMARAM');
insert into village(mandalId,villageCode,villageName) Values(637,'11','PASTHALA');
insert into village(mandalId,villageCode,villageName) Values(637,'12','LAXMAPUR');
insert into village(mandalId,villageCode,villageName) Values(637,'13','PASNUR');
insert into village(mandalId,villageCode,villageName) Values(637,'14','THUNGATHURTHI');
insert into village(mandalId,villageCode,villageName) Values(637,'15','GANUGUBANDA');
insert into village(mandalId,villageCode,villageName) Values(637,'16','KARIVIRALA');
insert into village(mandalId,villageCode,villageName) Values(637,'17','ANNARAM');
insert into village(mandalId,villageCode,villageName) Values(637,'18','VELUG PALLE');
insert into village(mandalId,villageCode,villageName) Values(637,'19','KESHAVA PURAM');
insert into village(mandalId,villageCode,villageName) Values(637,'20','SANGEM');


insert into village(mandalId,villageCode,villageName) Values(638,'1','GORENTLA');
insert into village(mandalId,villageCode,villageName) Values(638,'2','POLUMALLA');
insert into village(mandalId,villageCode,villageName) Values(638,'3','MADDIRALA');
insert into village(mandalId,villageCode,villageName) Values(638,'4','MUKUNDAPURAM');
insert into village(mandalId,villageCode,villageName) Values(638,'5','GANJIVARI KOTHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(638,'6','MAMINDLA MADAVA');
insert into village(mandalId,villageCode,villageName) Values(638,'7','CHINNA NEMILA');
insert into village(mandalId,villageCode,villageName) Values(638,'8','CHANDU PATLA');
insert into village(mandalId,villageCode,villageName) Values(638,'9','YERRA PAHAD');
insert into village(mandalId,villageCode,villageName) Values(638,'10','NUTHANKAL');
insert into village(mandalId,villageCode,villageName) Values(638,'11','CHILPA KUNTA');
insert into village(mandalId,villageCode,villageName) Values(638,'12','VENKE PALLE');
insert into village(mandalId,villageCode,villageName) Values(638,'13','TALLA SINGARAM');
insert into village(mandalId,villageCode,villageName) Values(638,'14','YADAVALLI');
insert into village(mandalId,villageCode,villageName) Values(638,'15','GUNDLA SINGARAM');
insert into village(mandalId,villageCode,villageName) Values(638,'16','MIRYALA');
insert into village(mandalId,villageCode,villageName) Values(638,'17','DIRISANA PALLE');
insert into village(mandalId,villageCode,villageName) Values(638,'18','PEDANEMILA');
insert into village(mandalId,villageCode,villageName) Values(638,'19','BHIKUMALLA');
insert into village(mandalId,villageCode,villageName) Values(638,'20','MACHAN PALLE');
insert into village(mandalId,villageCode,villageName) Values(638,'21','LINGAM PALLE');


insert into village(mandalId,villageCode,villageName) Values(639,'1','KANDAGATLA');
insert into village(mandalId,villageCode,villageName) Values(639,'2','GATTIKAL');
insert into village(mandalId,villageCode,villageName) Values(639,'3','PATHARLA PAHAD');
insert into village(mandalId,villageCode,villageName) Values(639,'4','ISTHALAPUR');
insert into village(mandalId,villageCode,villageName) Values(639,'5','MUKKUDEU DEVI PALLE');
insert into village(mandalId,villageCode,villageName) Values(639,'6','AIPUR');
insert into village(mandalId,villageCode,villageName) Values(639,'7','VENKATAPUR');
insert into village(mandalId,villageCode,villageName) Values(639,'8','BOPPARAM');
insert into village(mandalId,villageCode,villageName) Values(639,'9','MIDTHAN PALLE');
insert into village(mandalId,villageCode,villageName) Values(639,'10','MAKTHA KOTHA GUDEM');
insert into village(mandalId,villageCode,villageName) Values(639,'11','SETTI GUDEM');
insert into village(mandalId,villageCode,villageName) Values(639,'12','NARAYANAPPA GUDA');
insert into village(mandalId,villageCode,villageName) Values(639,'13','NASEEMPET');
insert into village(mandalId,villageCode,villageName) Values(639,'14','ATMAKUR');
insert into village(mandalId,villageCode,villageName) Values(639,'15','NAMMIKAL');
insert into village(mandalId,villageCode,villageName) Values(639,'16','DACHARAM');
insert into village(mandalId,villageCode,villageName) Values(639,'17','GOLLAGUDA');
insert into village(mandalId,villageCode,villageName) Values(639,'18','DACHARAM');
insert into village(mandalId,villageCode,villageName) Values(639,'19','THUMMALAPEN PAHAD');
insert into village(mandalId,villageCode,villageName) Values(639,'20','KOTA PAHAD');



insert into village(mandalId,villageCode,villageName) Values(640,'1','WARDHAMANU KOTA');
insert into village(mandalId,villageCode,villageName) Values(640,'2','NAGARAM');
insert into village(mandalId,villageCode,villageName) Values(640,'3','DEVARANENI KOTHA PALLE');
insert into village(mandalId,villageCode,villageName) Values(640,'4','PARSAI PALLE');
insert into village(mandalId,villageCode,villageName) Values(640,'5','BOLLAM PALLE');
insert into village(mandalId,villageCode,villageName) Values(640,'6','JAJI REDDI GUDEM');
insert into village(mandalId,villageCode,villageName) Values(640,'7','KESARAM');
insert into village(mandalId,villageCode,villageName) Values(640,'8','UYYALAWADA');
insert into village(mandalId,villageCode,villageName) Values(640,'9','KUNCHAMARTHI');
insert into village(mandalId,villageCode,villageName) Values(640,'10','VELPUCHERLA');
insert into village(mandalId,villageCode,villageName) Values(640,'11','KASARLA PAHAD');
insert into village(mandalId,villageCode,villageName) Values(640,'12','KOMMALA');
insert into village(mandalId,villageCode,villageName) Values(640,'13','KODUR');
insert into village(mandalId,villageCode,villageName) Values(640,'14','ADIVEMULA');
insert into village(mandalId,villageCode,villageName) Values(640,'15','THIMMAPURAM');




insert into village(mandalId,villageCode,villageName) Values(641,'1','UPPALANCHA ');
insert into village(mandalId,villageCode,villageName) Values(641,'2','MANIMADDE ');
insert into village(mandalId,villageCode,villageName) Values(641,'3','GURIJALA ');
insert into village(mandalId,villageCode,villageName) Values(641,'4','SALI LINGOTAM ');
insert into village(mandalId,villageCode,villageName) Values(641,'5','TUDIMIDI ');
insert into village(mandalId,villageCode,villageName) Values(641,'6','CHITTALUR ');
insert into village(mandalId,villageCode,villageName) Values(641,'7','VANGAMARTHI ');
insert into village(mandalId,villageCode,villageName) Values(641,'8','ITUKULA PAHAD ');
insert into village(mandalId,villageCode,villageName) Values(641,'9','MADARAM KALAN ');
insert into village(mandalId,villageCode,villageName) Values(641,'10','UTUKUR ');
insert into village(mandalId,villageCode,villageName) Values(641,'11','SALIGOURARAM ');
insert into village(mandalId,villageCode,villageName) Values(641,'12','VADDI PAMULA ');
insert into village(mandalId,villageCode,villageName) Values(641,'13','NULAGADDA KOTHAPALLE ');
insert into village(mandalId,villageCode,villageName) Values(641,'14','BAIRONI BANDA ');
insert into village(mandalId,villageCode,villageName) Values(641,'15','THAKKEDLA PAHAD ');
insert into village(mandalId,villageCode,villageName) Values(641,'16','AKARAM ');
insert into village(mandalId,villageCode,villageName) Values(641,'17','VALLALA ');
insert into village(mandalId,villageCode,villageName) Values(641,'18','ADLUR ');
insert into village(mandalId,villageCode,villageName) Values(641,'19','PATHA KONDARAM ');
insert into village(mandalId,villageCode,villageName) Values(641,'20','PERKA KONDARAM');



insert into village(mandalId,villageCode,villageName) Values(642,'1','MOTHKUR');
insert into village(mandalId,villageCode,villageName) Values(642,'2','KONDA GADAPA');
insert into village(mandalId,villageCode,villageName) Values(642,'3','SADARSHAPUR');
insert into village(mandalId,villageCode,villageName) Values(642,'4','CHINNAPADISHALA');
insert into village(mandalId,villageCode,villageName) Values(642,'5','JANAKIPUR');
insert into village(mandalId,villageCode,villageName) Values(642,'6','CHIRRA GUDUR');
insert into village(mandalId,villageCode,villageName) Values(642,'7','KOTAMARTHI');
insert into village(mandalId,villageCode,villageName) Values(642,'8','CHOULLA RAMARAM');
insert into village(mandalId,villageCode,villageName) Values(642,'9','ADDA GUDUR');
insert into village(mandalId,villageCode,villageName) Values(642,'10','KANCHAN PALLE');
insert into village(mandalId,villageCode,villageName) Values(642,'11','PATIMATLA');
insert into village(mandalId,villageCode,villageName) Values(642,'12','BIJILAPUR');
insert into village(mandalId,villageCode,villageName) Values(642,'13','MUSITPATLA');
insert into village(mandalId,villageCode,villageName) Values(642,'14','PANAKA BANDA');
insert into village(mandalId,villageCode,villageName) Values(642,'15','PALADUGU');
insert into village(mandalId,villageCode,villageName) Values(642,'16','ANAJIPUR');
insert into village(mandalId,villageCode,villageName) Values(642,'17','PODICHEDU');
insert into village(mandalId,villageCode,villageName) Values(642,'18','DATTAPPAGUDA');
insert into village(mandalId,villageCode,villageName) Values(642,'19','DACHARAM');
insert into village(mandalId,villageCode,villageName) Values(642,'20','REPAKA (P)');
insert into village(mandalId,villageCode,villageName) Values(642,'21','VELDEVI');
insert into village(mandalId,villageCode,villageName) Values(642,'22','SINGARAM (P)');
insert into village(mandalId,villageCode,villageName) Values(642,'23','DHARMARAM');




insert into village(mandalId,villageCode,villageName) Values(643,'1','CHADA');
insert into village(mandalId,villageCode,villageName) Values(643,'2','DURSAGANI PALLE');
insert into village(mandalId,villageCode,villageName) Values(643,'3','CHANDE PALLE');
insert into village(mandalId,villageCode,villageName) Values(643,'4','CHAMAPUR');
insert into village(mandalId,villageCode,villageName) Values(643,'5','SINGARAM');
insert into village(mandalId,villageCode,villageName) Values(643,'6','KALWAPALLE');
insert into village(mandalId,villageCode,villageName) Values(643,'7','MORIPIRALA');
insert into village(mandalId,villageCode,villageName) Values(643,'8','KORATIKAL');
insert into village(mandalId,villageCode,villageName) Values(643,'9','RAHIMKHANPET');
insert into village(mandalId,villageCode,villageName) Values(643,'10','KHAPRAI PALLE');
insert into village(mandalId,villageCode,villageName) Values(643,'11','KURELLA');
insert into village(mandalId,villageCode,villageName) Values(643,'12','THUKKAPUR');
insert into village(mandalId,villageCode,villageName) Values(643,'13','DHARMAPUR');
insert into village(mandalId,villageCode,villageName) Values(643,'14','ATMAKUR');
insert into village(mandalId,villageCode,villageName) Values(643,'15','RAIPALLE');
insert into village(mandalId,villageCode,villageName) Values(643,'16','SARVEPALLE');
insert into village(mandalId,villageCode,villageName) Values(643,'17','PALLERLA');
insert into village(mandalId,villageCode,villageName) Values(643,'18','RAGHAVAPUR');
insert into village(mandalId,villageCode,villageName) Values(643,'19','LINGARAJ PALLE');
insert into village(mandalId,villageCode,villageName) Values(643,'20','NARSAPUR');
insert into village(mandalId,villageCode,villageName) Values(643,'21','DUPPELLI');


insert into village(mandalId,villageCode,villageName) Values(644,'1','TEKULA SOMARAM ');
insert into village(mandalId,villageCode,villageName) Values(644,'2','PAHILWANPUR ');
insert into village(mandalId,villageCode,villageName) Values(644,'3','KANCHAN PALLE ');
insert into village(mandalId,villageCode,villageName) Values(644,'4','PULIGILLA ');
insert into village(mandalId,villageCode,villageName) Values(644,'5','MOGILIPAKA ');
insert into village(mandalId,villageCode,villageName) Values(644,'6','KASHAMMA BKUNTA ');
insert into village(mandalId,villageCode,villageName) Values(644,'7','MUNAGALA TURKAPALLY ');
insert into village(mandalId,villageCode,villageName) Values(644,'8','MUDDAPUR ');
insert into village(mandalId,villageCode,villageName) Values(644,'9','ARRUR ');
insert into village(mandalId,villageCode,villageName) Values(644,'10','MALLE PALLE ');
insert into village(mandalId,villageCode,villageName) Values(644,'11','SUNKI SHALA ');
insert into village(mandalId,villageCode,villageName) Values(644,'12','REDLA REPAKA ');
insert into village(mandalId,villageCode,villageName) Values(644,'13','PODDATUR ');
insert into village(mandalId,villageCode,villageName) Values(644,'14','SANGAM ');
insert into village(mandalId,villageCode,villageName) Values(644,'15','VERKAT PALLE ');
insert into village(mandalId,villageCode,villageName) Values(644,'16','GOKARAM ');
insert into village(mandalId,villageCode,villageName) Values(644,'17','GOLLE PALLE ');
insert into village(mandalId,villageCode,villageName) Values(644,'18','NEMILI KALAWA ');
insert into village(mandalId,villageCode,villageName) Values(644,'19','VALIGONDA ');
insert into village(mandalId,villageCode,villageName) Values(644,'20','NAGARAM ');
insert into village(mandalId,villageCode,villageName) Values(644,'21','VELVERTHY ');
insert into village(mandalId,villageCode,villageName) Values(644,'22','LOTHUKUNTA ');
insert into village(mandalId,villageCode,villageName) Values(644,'23','GURNATH PALLI ');
insert into village(mandalId,villageCode,villageName) Values(644,'24','GANGAPUR ');
insert into village(mandalId,villageCode,villageName) Values(644,'25','VENKATAPUR ');
insert into village(mandalId,villageCode,villageName) Values(644,'26','CHITTAPUR ');
insert into village(mandalId,villageCode,villageName) Values(644,'27','GOPARAJ PALLE ');
insert into village(mandalId,villageCode,villageName) Values(644,'28','VEMALKONDA');


insert into village(mandalId,villageCode,villageName) Values(645,'1','TAJPUR ');
insert into village(mandalId,villageCode,villageName) Values(645,'2','HANMAPUR ');
insert into village(mandalId,villageCode,villageName) Values(645,'3','WADAPARTHY ');
insert into village(mandalId,villageCode,villageName) Values(645,'4','TIMMAPUR ');
insert into village(mandalId,villageCode,villageName) Values(645,'5','BASWAPUR ');
insert into village(mandalId,villageCode,villageName) Values(645,'6','HUSSAINABAD(RURAL) ');
insert into village(mandalId,villageCode,villageName) Values(645,'7','RAYAGIRI ');
insert into village(mandalId,villageCode,villageName) Values(645,'8','KESARAM ');
insert into village(mandalId,villageCode,villageName) Values(645,'9','KUNOOR ');
insert into village(mandalId,villageCode,villageName) Values(645,'10','CHANDUPATLA ');
insert into village(mandalId,villageCode,villageName) Values(645,'11','CHEEMALA KONDUR ');
insert into village(mandalId,villageCode,villageName) Values(645,'12','MUSTYALAPALLI ');
insert into village(mandalId,villageCode,villageName) Values(645,'13','VEERA VALLY ');
insert into village(mandalId,villageCode,villageName) Values(645,'14','BANDA SOMARAM ');
insert into village(mandalId,villageCode,villageName) Values(645,'15','GOUSE NAGAR ');
insert into village(mandalId,villageCode,villageName) Values(645,'16','YERRAM BALLE ');
insert into village(mandalId,villageCode,villageName) Values(645,'17','TUKKAPUR ');
insert into village(mandalId,villageCode,villageName) Values(645,'18','RAMACHANDRAPUR ');
insert into village(mandalId,villageCode,villageName) Values(645,'19','PENCHIKLA PAHAD ');
insert into village(mandalId,villageCode,villageName) Values(645,'20','BAGATH BHUVANAGIRI ');
insert into village(mandalId,villageCode,villageName) Values(645,'21','BHUVANAGIRI ');
insert into village(mandalId,villageCode,villageName) Values(645,'22','ANANTHARAM ');
insert into village(mandalId,villageCode,villageName) Values(645,'23','PAGIDI PALLE ');
insert into village(mandalId,villageCode,villageName) Values(645,'24','BOMMAI PALLE ');
insert into village(mandalId,villageCode,villageName) Values(645,'25','ANAJIPUR ');
insert into village(mandalId,villageCode,villageName) Values(645,'26','NANDANAM ');
insert into village(mandalId,villageCode,villageName) Values(645,'27','NAGIREDDI PALLE ');
insert into village(mandalId,villageCode,villageName) Values(645,'28','BOLLE PALLE ');
insert into village(mandalId,villageCode,villageName) Values(645,'29','SURE PALLE ');



insert into village(mandalId,villageCode,villageName) Values(646,'1','RAYARAO PET ');
insert into village(mandalId,villageCode,villageName) Values(646,'2','JAMEELAPET ');
insert into village(mandalId,villageCode,villageName) Values(646,'3','JIYA PALLE ');
insert into village(mandalId,villageCode,villageName) Values(646,'4','MAHADEVPUR ');
insert into village(mandalId,villageCode,villageName) Values(646,'5','KONDAMADUGU ');
insert into village(mandalId,villageCode,villageName) Values(646,'6','JAIN PALLE ');
insert into village(mandalId,villageCode,villageName) Values(646,'7','GUDUR ');
insert into village(mandalId,villageCode,villageName) Values(646,'8','ANNAMPATLA ');
insert into village(mandalId,villageCode,villageName) Values(646,'9','BIBINAGAR ');
insert into village(mandalId,villageCode,villageName) Values(646,'10','RANGAPUR ');
insert into village(mandalId,villageCode,villageName) Values(646,'11','BAGDAYARA ');
insert into village(mandalId,villageCode,villageName) Values(646,'12','NEMARUGOMULA ');
insert into village(mandalId,villageCode,villageName) Values(646,'13','PADAMATI SOMARAM ');
insert into village(mandalId,villageCode,villageName) Values(646,'14','RAHEEM KHAN GUDA ');
insert into village(mandalId,villageCode,villageName) Values(646,'15','BRAHMANA PALLE ');
insert into village(mandalId,villageCode,villageName) Values(646,'16','MAQDOOM PALLE ');
insert into village(mandalId,villageCode,villageName) Values(646,'17','RAVI PAHAD ');
insert into village(mandalId,villageCode,villageName) Values(646,'18','MADHARAM ');
insert into village(mandalId,villageCode,villageName) Values(646,'19','JAM PALLE ');
insert into village(mandalId,villageCode,villageName) Values(646,'20','GURRALA DANDI ');
insert into village(mandalId,villageCode,villageName) Values(646,'21','CHINARAVULA PALLE ');
insert into village(mandalId,villageCode,villageName) Values(646,'22','RAGHAVAPUR ');
insert into village(mandalId,villageCode,villageName) Values(646,'23','RUDRA VELLY ');
insert into village(mandalId,villageCode,villageName) Values(646,'24','VENKIRYAL ');
insert into village(mandalId,villageCode,villageName) Values(646,'25','MAKTHA ANANTHARAM');



insert into village(mandalId,villageCode,villageName) Values(647,'1','PILLAI PALLE ');
insert into village(mandalId,villageCode,villageName) Values(647,'2','ALINAGAR ');
insert into village(mandalId,villageCode,villageName) Values(647,'3','JULUR ');
insert into village(mandalId,villageCode,villageName) Values(647,'4','KHAPRAI PALLE ');
insert into village(mandalId,villageCode,villageName) Values(647,'5','PEDDA RAVULA PALLE ');
insert into village(mandalId,villageCode,villageName) Values(647,'6','INDRIYALA ');
insert into village(mandalId,villageCode,villageName) Values(647,'7','RAMALINGAM PALLE ');
insert into village(mandalId,villageCode,villageName) Values(647,'8','GOUSE KONDA ');
insert into village(mandalId,villageCode,villageName) Values(647,'9','ABDULLA NAGAR ');
insert into village(mandalId,villageCode,villageName) Values(647,'10','MUKTHAPUR ');
insert into village(mandalId,villageCode,villageName) Values(647,'11','JAGATTH PALLE ');
insert into village(mandalId,villageCode,villageName) Values(647,'12','DESHMUKHI ');
insert into village(mandalId,villageCode,villageName) Values(647,'13','POCHAMPALLE ');
insert into village(mandalId,villageCode,villageName) Values(647,'14','REVAN PALLE ');
insert into village(mandalId,villageCode,villageName) Values(647,'15','DANTHUR ');
insert into village(mandalId,villageCode,villageName) Values(647,'16','VANKA MAMIDI ');
insert into village(mandalId,villageCode,villageName) Values(647,'17','DHARMA REDDI PALLE ');
insert into village(mandalId,villageCode,villageName) Values(647,'18','KANUMUKLA ');
insert into village(mandalId,villageCode,villageName) Values(647,'19','BHEEMAN PALLE ');
insert into village(mandalId,villageCode,villageName) Values(647,'20','JALAL PUR ');
insert into village(mandalId,villageCode,villageName) Values(647,'21','MEHAR NAGAR ');
insert into village(mandalId,villageCode,villageName) Values(647,'22','HYDERPUR ');
insert into village(mandalId,villageCode,villageName) Values(647,'23','JIBLAK PALLE');




insert into village(mandalId,villageCode,villageName) Values(648,'1','TUPRANPET');
insert into village(mandalId,villageCode,villageName) Values(648,'2','MALKAPUR');
insert into village(mandalId,villageCode,villageName) Values(648,'3','KHAIRATHPUR');
insert into village(mandalId,villageCode,villageName) Values(648,'4','YELLAGIRI');
insert into village(mandalId,villageCode,villageName) Values(648,'5','LAKKARAM');
insert into village(mandalId,villageCode,villageName) Values(648,'6','CHOUTUPPAL');
insert into village(mandalId,villageCode,villageName) Values(648,'7','CHINNAKONDUR');
insert into village(mandalId,villageCode,villageName) Values(648,'8','NELAPATLA');
insert into village(mandalId,villageCode,villageName) Values(648,'9','JAIKESARAM');
insert into village(mandalId,villageCode,villageName) Values(648,'10','SWAMULAVARILINGOTAM');
insert into village(mandalId,villageCode,villageName) Values(648,'11','PANTHANGI');
insert into village(mandalId,villageCode,villageName) Values(648,'12','LINGOJIGUDA');
insert into village(mandalId,villageCode,villageName) Values(648,'13','TALLASINGARAM');
insert into village(mandalId,villageCode,villageName) Values(648,'14','TANGADPALLE');
insert into village(mandalId,villageCode,villageName) Values(648,'15','DEVALAMMANAGARAM');
insert into village(mandalId,villageCode,villageName) Values(648,'16','ALLAPUR');
insert into village(mandalId,villageCode,villageName) Values(648,'17','PEEPALPAHAD');


insert into village(mandalId,villageCode,villageName) Values(649,'1','THUMMALAGUDA');
insert into village(mandalId,villageCode,villageName) Values(649,'2','SHOBANADRIPURAM');
insert into village(mandalId,villageCode,villageName) Values(649,'3','LAXMAPURAM');
insert into village(mandalId,villageCode,villageName) Values(649,'4','MUNIPANPULA');
insert into village(mandalId,villageCode,villageName) Values(649,'5','PALLIWADA');
insert into village(mandalId,villageCode,villageName) Values(649,'6','BACHUPPALA');
insert into village(mandalId,villageCode,villageName) Values(649,'7','SURARAM');
insert into village(mandalId,villageCode,villageName) Values(649,'8','B.THURKAPALLE');
insert into village(mandalId,villageCode,villageName) Values(649,'9','KUNKUDUPAMULA');
insert into village(mandalId,villageCode,villageName) Values(649,'10','YENNARAM');
insert into village(mandalId,villageCode,villageName) Values(649,'11','KAKKIRENI');
insert into village(mandalId,villageCode,villageName) Values(649,'12','UTHATOOR');
insert into village(mandalId,villageCode,villageName) Values(649,'13','ISKILLA');
insert into village(mandalId,villageCode,villageName) Values(649,'14','DUBBAKA');
insert into village(mandalId,villageCode,villageName) Values(649,'15','NEERNEMULA');
insert into village(mandalId,villageCode,villageName) Values(649,'16','NIDHANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(649,'17','BOGARAM');
insert into village(mandalId,villageCode,villageName) Values(649,'18','YELLANKI');
insert into village(mandalId,villageCode,villageName) Values(649,'19','SIRIPURAM');
insert into village(mandalId,villageCode,villageName) Values(649,'20','RAMANNAPET');
insert into village(mandalId,villageCode,villageName) Values(649,'21','JANAMPALLE');


insert into village(mandalId,villageCode,villageName) Values(650,'1','SUNKENEPALLE');
insert into village(mandalId,villageCode,villageName) Values(650,'2','GUNDRAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(650,'3','AIPOOR');
insert into village(mandalId,villageCode,villageName) Values(650,'4','PEREPALLE');
insert into village(mandalId,villageCode,villageName) Values(650,'5','PITTAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(650,'6','VELIMINEDU');
insert into village(mandalId,villageCode,villageName) Values(650,'7','PEDAKAPARTHY');
insert into village(mandalId,villageCode,villageName) Values(650,'8','CHITYAL');
insert into village(mandalId,villageCode,villageName) Values(650,'9','SHIVANENIGUDEM');
insert into village(mandalId,villageCode,villageName) Values(650,'10','VANIPAKALA');
insert into village(mandalId,villageCode,villageName) Values(650,'11','WATTIMARTHY');
insert into village(mandalId,villageCode,villageName) Values(650,'12','NERADA');
insert into village(mandalId,villageCode,villageName) Values(650,'13','URMADLA');
insert into village(mandalId,villageCode,villageName) Values(650,'14','CHINAKAPARTHY');
insert into village(mandalId,villageCode,villageName) Values(650,'15','TALLAYELLEMLA');
insert into village(mandalId,villageCode,villageName) Values(650,'16','YELIKATTA');



insert into village(mandalId,villageCode,villageName) Values(651,'1','SABBIDIGUDA');
insert into village(mandalId,villageCode,villageName) Values(651,'2','AMMANABOLE');
insert into village(mandalId,villageCode,villageName) Values(651,'3','AKKENEPALLE');
insert into village(mandalId,villageCode,villageName) Values(651,'4','NAKKALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(651,'5','SHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(651,'6','CHIPPALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(651,'7','THONDALVAI');
insert into village(mandalId,villageCode,villageName) Values(651,'8','THIRUMALAGIRI');
insert into village(mandalId,villageCode,villageName) Values(651,'9','MANDRA');
insert into village(mandalId,villageCode,villageName) Values(651,'10','MADHAYEDAVALLY');
insert into village(mandalId,villageCode,villageName) Values(651,'11','NEMMANI');
insert into village(mandalId,villageCode,villageName) Values(651,'12','POTHINENIPALLE');
insert into village(mandalId,villageCode,villageName) Values(651,'13','AKKENEPALLIVARILINGOTAM');
insert into village(mandalId,villageCode,villageName) Values(651,'14','CHERUGHAT');
insert into village(mandalId,villageCode,villageName) Values(651,'15','NARKETPALLE');
insert into village(mandalId,villageCode,villageName) Values(651,'16','CHOUDAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(651,'17','BRAHMANVELLEMLA');
insert into village(mandalId,villageCode,villageName) Values(651,'18','AURAVANI');
insert into village(mandalId,villageCode,villageName) Values(651,'19','YELLAREDDYGUDA');


insert into village(mandalId,villageCode,villageName) Values(652,'1','THIMMAPUR');
insert into village(mandalId,villageCode,villageName) Values(652,'2','PARADA');
insert into village(mandalId,villageCode,villageName) Values(652,'3','EDULUR');
insert into village(mandalId,villageCode,villageName) Values(652,'4','PANDENAPALLE');
insert into village(mandalId,villageCode,villageName) Values(652,'5','KURUMATHY');
insert into village(mandalId,villageCode,villageName) Values(652,'6','CHERUVANNARAM');
insert into village(mandalId,villageCode,villageName) Values(652,'7','MUNUKUNTLA');
insert into village(mandalId,villageCode,villageName) Values(652,'8','KALMERA');
insert into village(mandalId,villageCode,villageName) Values(652,'9','YERSANIGUDA');
insert into village(mandalId,villageCode,villageName) Values(652,'10','PAMANAGUNDLA');
insert into village(mandalId,villageCode,villageName) Values(652,'11','ISMAILPALLI');
insert into village(mandalId,villageCode,villageName) Values(652,'12','PITTAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(652,'13','DUGNEVALLY');
insert into village(mandalId,villageCode,villageName) Values(652,'14','KATTANGOOR');
insert into village(mandalId,villageCode,villageName) Values(652,'15','AITIPAMULA');
insert into village(mandalId,villageCode,villageName) Values(652,'16','RAMACHANDRAPURAM');
insert into village(mandalId,villageCode,villageName) Values(652,'17','MALLARAM');
insert into village(mandalId,villageCode,villageName) Values(652,'18','BOLLEPALLE');



insert into village(mandalId,villageCode,villageName) Values(653,'1','OGODE');
insert into village(mandalId,villageCode,villageName) Values(653,'2','VALLABHAPUR');
insert into village(mandalId,villageCode,villageName) Values(653,'3','PALEM');
insert into village(mandalId,villageCode,villageName) Values(653,'4','NOMULA');
insert into village(mandalId,villageCode,villageName) Values(653,'5','KADAPARTHY');
insert into village(mandalId,villageCode,villageName) Values(653,'6','ADAVIBOLLARAM');
insert into village(mandalId,villageCode,villageName) Values(653,'7','CHANDAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(653,'8','NELLIBANDA');
insert into village(mandalId,villageCode,villageName) Values(653,'9','NAKREKAL');
insert into village(mandalId,villageCode,villageName) Values(653,'10','TATIKAL');
insert into village(mandalId,villageCode,villageName) Values(653,'11','TETTEKUNTA');
insert into village(mandalId,villageCode,villageName) Values(653,'12','MANGALIPALLE');
insert into village(mandalId,villageCode,villageName) Values(653,'13','MARRUR');
insert into village(mandalId,villageCode,villageName) Values(653,'14','CHANDUPATLA');
insert into village(mandalId,villageCode,villageName) Values(653,'15','MANDALAPUR');
insert into village(mandalId,villageCode,villageName) Values(653,'16','GORENKALPALLE');



insert into village(mandalId,villageCode,villageName) Values(654,'1','IPPALAGUDEM');
insert into village(mandalId,villageCode,villageName) Values(654,'2','GUDIWADA');
insert into village(mandalId,villageCode,villageName) Values(654,'3','BOPPARAM');
insert into village(mandalId,villageCode,villageName) Values(654,'4','KASANGODE');
insert into village(mandalId,villageCode,villageName) Values(654,'5','KETHEPALLE');
insert into village(mandalId,villageCode,villageName) Values(654,'6','KORLAPAHAD');
insert into village(mandalId,villageCode,villageName) Values(654,'7','INPAMULA');
insert into village(mandalId,villageCode,villageName) Values(654,'8','BONDAPALEM');
insert into village(mandalId,villageCode,villageName) Values(654,'9','CHERUKUPALLE');
insert into village(mandalId,villageCode,villageName) Values(654,'10','THUNGATHURTHY');
insert into village(mandalId,villageCode,villageName) Values(654,'11','UPPALAPAHAD');
insert into village(mandalId,villageCode,villageName) Values(654,'12','KOPPOLE');
insert into village(mandalId,villageCode,villageName) Values(654,'13','BHEEMAVARAM');



insert into village(mandalId,villageCode,villageName) Values(655,'1','SOLIPET');
insert into village(mandalId,villageCode,villageName) Values(655,'2','RAMACHANDRAPURAM');
insert into village(mandalId,villageCode,villageName) Values(655,'3','RAMAVARAM');
insert into village(mandalId,villageCode,villageName) Values(655,'4','YERKARAM');
insert into village(mandalId,villageCode,villageName) Values(655,'5','BALEMLA');
insert into village(mandalId,villageCode,villageName) Values(655,'6','RAMANNAGUDA');
insert into village(mandalId,villageCode,villageName) Values(655,'7','PINNAIPALEM');
insert into village(mandalId,villageCode,villageName) Values(655,'8','YENDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(655,'9','VENKATRAMPUR');
insert into village(mandalId,villageCode,villageName) Values(655,'10','TEKUMATLA');
insert into village(mandalId,villageCode,villageName) Values(655,'11','BECHIRAGDACHARAM');
insert into village(mandalId,villageCode,villageName) Values(655,'12','PILLALAMARRI');
insert into village(mandalId,villageCode,villageName) Values(655,'13','BECHIRAGMADHARAM');
insert into village(mandalId,villageCode,villageName) Values(655,'14','SURYAPET');
insert into village(mandalId,villageCode,villageName) Values(655,'15','KONKATHIMMANIANNARAM');
insert into village(mandalId,villageCode,villageName) Values(655,'16','KASARABAD');
insert into village(mandalId,villageCode,villageName) Values(655,'17','KESARAM');
insert into village(mandalId,villageCode,villageName) Values(655,'18','IMAMPET');
insert into village(mandalId,villageCode,villageName) Values(655,'19','THALLAKHAMMAMPADU');
insert into village(mandalId,villageCode,villageName) Values(655,'20','CIRCLEPET');



insert into village(mandalId,villageCode,villageName) Values(656,'1','AILAPUR');
insert into village(mandalId,villageCode,villageName) Values(656,'2','KUDAKUDA');
insert into village(mandalId,villageCode,villageName) Values(656,'3','BEEBIGUDA');
insert into village(mandalId,villageCode,villageName) Values(656,'4','GAYAMVARIGUDA');
insert into village(mandalId,villageCode,villageName) Values(656,'5','VATTIKHAMMAMPAHAD');
insert into village(mandalId,villageCode,villageName) Values(656,'6','CHENDUPATLA');
insert into village(mandalId,villageCode,villageName) Values(656,'7','THIMMAPURAM');
insert into village(mandalId,villageCode,villageName) Values(656,'8','CHIVVEMLA');
insert into village(mandalId,villageCode,villageName) Values(656,'9','DURAJPALLE');
insert into village(mandalId,villageCode,villageName) Values(656,'10','UNDRUGONDA');
insert into village(mandalId,villageCode,villageName) Values(656,'11','TULJARAOPET');
insert into village(mandalId,villageCode,villageName) Values(656,'12','VALLABHAPUR');
insert into village(mandalId,villageCode,villageName) Values(656,'13','GUMPULA');
insert into village(mandalId,villageCode,villageName) Values(656,'14','THIRUMALAGIRI');
insert into village(mandalId,villageCode,villageName) Values(656,'15','GUNJALURU');


insert into village(mandalId,villageCode,villageName) Values(657,'1','RAVIPAHAD');
insert into village(mandalId,villageCode,villageName) Values(657,'2','SARVARAM');
insert into village(mandalId,villageCode,villageName) Values(657,'3','KUDALI');
insert into village(mandalId,villageCode,villageName) Values(657,'4','URLUGUNDA');
insert into village(mandalId,villageCode,villageName) Values(657,'5','NEREDAVAI');
insert into village(mandalId,villageCode,villageName) Values(657,'6','ANNARIGUDEM');
insert into village(mandalId,villageCode,villageName) Values(657,'7','VIBHALAPUR');
insert into village(mandalId,villageCode,villageName) Values(657,'8','SINGARNENIPALLE');
insert into village(mandalId,villageCode,villageName) Values(657,'9','BUMKACHRLA');
insert into village(mandalId,villageCode,villageName) Values(657,'10','SIRIKONDA');
insert into village(mandalId,villageCode,villageName) Values(657,'11','HUSSAINBAD');
insert into village(mandalId,villageCode,villageName) Values(657,'12','GOPALAPURAM');
insert into village(mandalId,villageCode,villageName) Values(657,'13','MAMILLAGUDEM');
insert into village(mandalId,villageCode,villageName) Values(657,'14','THUMMALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(657,'15','MOTHEY');
insert into village(mandalId,villageCode,villageName) Values(657,'16','RAGHAVAPUR');
insert into village(mandalId,villageCode,villageName) Values(657,'17','NAMAVARAM');


insert into village(mandalId,villageCode,villageName) Values(658,'1','KAGITARAMACHANDRAPURAM');
insert into village(mandalId,villageCode,villageName) Values(658,'2','KARIVIRALA');
insert into village(mandalId,villageCode,villageName) Values(658,'3','BRUNDAVANPURAM');
insert into village(mandalId,villageCode,villageName) Values(658,'4','SIRIPURAM');
insert into village(mandalId,villageCode,villageName) Values(658,'5','YELLAPURAM');
insert into village(mandalId,villageCode,villageName) Values(658,'6','CHANPALLE');
insert into village(mandalId,villageCode,villageName) Values(658,'7','PALARAM');
insert into village(mandalId,villageCode,villageName) Values(658,'8','TRIPURAVARAM');
insert into village(mandalId,villageCode,villageName) Values(658,'9','CHAKIRALA');
insert into village(mandalId,villageCode,villageName) Values(658,'10','NADIGUDEM');
insert into village(mandalId,villageCode,villageName) Values(658,'11','RAMAPURAM');
insert into village(mandalId,villageCode,villageName) Values(658,'12','EKLASHKHANPET');
insert into village(mandalId,villageCode,villageName) Values(658,'13','TELLABALI');
insert into village(mandalId,villageCode,villageName) Values(658,'14','RATNAVARAM');
insert into village(mandalId,villageCode,villageName) Values(658,'15','SINGAVARAM');
insert into village(mandalId,villageCode,villageName) Values(658,'16','YASANTAPURAM');


insert into village(mandalId,villageCode,villageName) Values(659,'1','NELAMARRI');
insert into village(mandalId,villageCode,villageName) Values(659,'2','TADVAI');
insert into village(mandalId,villageCode,villageName) Values(659,'3','SYEDMUJAVARPET');
insert into village(mandalId,villageCode,villageName) Values(659,'4','MADHAVARAM');
insert into village(mandalId,villageCode,villageName) Values(659,'5','REPALA');
insert into village(mandalId,villageCode,villageName) Values(659,'6','KALAKOVA');
insert into village(mandalId,villageCode,villageName) Values(659,'7','MUNAGALA');
insert into village(mandalId,villageCode,villageName) Values(659,'8','GANAPAVARAM');
insert into village(mandalId,villageCode,villageName) Values(659,'9','KOKKIRENI');
insert into village(mandalId,villageCode,villageName) Values(659,'10','BARAKATHGUDA');
insert into village(mandalId,villageCode,villageName) Values(659,'11','AKUPAMULA');



insert into village(mandalId,villageCode,villageName) Values(660,'1','RAJPET');
insert into village(mandalId,villageCode,villageName) Values(660,'2','ANAJIPURAM');
insert into village(mandalId,villageCode,villageName) Values(660,'3','SINGAREDDYPALEM');
insert into village(mandalId,villageCode,villageName) Values(660,'4','MOHAMMADAPURAM');
insert into village(mandalId,villageCode,villageName) Values(660,'5','DHARMAPURAM');
insert into village(mandalId,villageCode,villageName) Values(660,'6','BHAKTHALAPURAM');
insert into village(mandalId,villageCode,villageName) Values(660,'7','MACHARAM');
insert into village(mandalId,villageCode,villageName) Values(660,'8','PENPAHAD');
insert into village(mandalId,villageCode,villageName) Values(660,'9','ANATHARAM');
insert into village(mandalId,villageCode,villageName) Values(660,'10','DOSAPAHAD');
insert into village(mandalId,villageCode,villageName) Values(660,'11','NAGULAPAHAD');
insert into village(mandalId,villageCode,villageName) Values(660,'12','POTLAPAHAD');
insert into village(mandalId,villageCode,villageName) Values(660,'13','N.ANNARAM');
insert into village(mandalId,villageCode,villageName) Values(660,'14','LINGALA');
insert into village(mandalId,villageCode,villageName) Values(660,'15','DUPAHAD');
insert into village(mandalId,villageCode,villageName) Values(660,'16','GAJULAMALKAPURAM');
insert into village(mandalId,villageCode,villageName) Values(660,'17','CHEEDELLA');


insert into village(mandalId,villageCode,villageName) Values(661,'1','KOYALPAHAD');
insert into village(mandalId,villageCode,villageName) Values(661,'2','AGAMOTHKUR');
insert into village(mandalId,villageCode,villageName) Values(661,'3','CHIRUMARTHY');
insert into village(mandalId,villageCode,villageName) Values(661,'4','PAMULAPAHAD');
insert into village(mandalId,villageCode,villageName) Values(661,'5','AMANGAL');
insert into village(mandalId,villageCode,villageName) Values(661,'6','BHEEMANPALLE');
insert into village(mandalId,villageCode,villageName) Values(661,'7','BOMMAKAL');
insert into village(mandalId,villageCode,villageName) Values(661,'8','THOPUCHERLA');
insert into village(mandalId,villageCode,villageName) Values(661,'9','GANDRAVANIGUDA');
insert into village(mandalId,villageCode,villageName) Values(661,'10','KUKKADAM');
insert into village(mandalId,villageCode,villageName) Values(661,'11','BUGGABARIGUDA');
insert into village(mandalId,villageCode,villageName) Values(661,'12','VEMULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(661,'13','ANNAPAREDDIGUDA');
insert into village(mandalId,villageCode,villageName) Values(661,'14','ITIKYALA');
insert into village(mandalId,villageCode,villageName) Values(661,'15','MUNDLAPAHAD');
insert into village(mandalId,villageCode,villageName) Values(661,'16','SETTIPALEM');
insert into village(mandalId,villageCode,villageName) Values(661,'17','THIMMAREDDIGUDA');
insert into village(mandalId,villageCode,villageName) Values(661,'18','SALKUNUR');
insert into village(mandalId,villageCode,villageName) Values(661,'19','KALVALAPALEM');
insert into village(mandalId,villageCode,villageName) Values(661,'20','CHALICHIMALAPALEM');
insert into village(mandalId,villageCode,villageName) Values(661,'21','RAVULAPENTA');
insert into village(mandalId,villageCode,villageName) Values(661,'22','MOLKAPATNAM');
insert into village(mandalId,villageCode,villageName) Values(661,'23','KAMEPALLE');



insert into village(mandalId,villageCode,villageName) Values(662,'1','KESHRAJPALLE');
insert into village(mandalId,villageCode,villageName) Values(662,'2','A.DUPPALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(662,'3','KANKANALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(662,'4','THANDERPALLE');
insert into village(mandalId,villageCode,villageName) Values(662,'5','KHAJIRAMARAM');
insert into village(mandalId,villageCode,villageName) Values(662,'6','SURARAM');
insert into village(mandalId,villageCode,villageName) Values(662,'7','PAJJUR');
insert into village(mandalId,villageCode,villageName) Values(662,'8','GADDIKONDARAM');
insert into village(mandalId,villageCode,villageName) Values(662,'9','INDLOOR');
insert into village(mandalId,villageCode,villageName) Values(662,'10','MAMIDALA');
insert into village(mandalId,villageCode,villageName) Values(662,'11','SARVARAM');
insert into village(mandalId,villageCode,villageName) Values(662,'12','THIPPARTHI');
insert into village(mandalId,villageCode,villageName) Values(662,'13','JANGAMREDDIGUDEM');
insert into village(mandalId,villageCode,villageName) Values(662,'14','RAJUPETA');
insert into village(mandalId,villageCode,villageName) Values(662,'15','GANGANAPALEM');
insert into village(mandalId,villageCode,villageName) Values(662,'16','CHERUVUPALLE');
insert into village(mandalId,villageCode,villageName) Values(662,'17','DACHARAM');
insert into village(mandalId,villageCode,villageName) Values(662,'18','MADUGULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(662,'19','INDUGULA');



insert into village(mandalId,villageCode,villageName) Values(663,'1','APPAJI PETA');
insert into village(mandalId,villageCode,villageName) Values(663,'2','ANNEPARTHY');
insert into village(mandalId,villageCode,villageName) Values(663,'3','CHRLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(663,'4','AMMAGUDA');
insert into village(mandalId,villageCode,villageName) Values(663,'5','DANDAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(663,'6','CHANDAN PALLE');
insert into village(mandalId,villageCode,villageName) Values(663,'7','PANAGALLU');
insert into village(mandalId,villageCode,villageName) Values(663,'8','MARRIGUDA');
insert into village(mandalId,villageCode,villageName) Values(663,'9','BUDHARAM');
insert into village(mandalId,villageCode,villageName) Values(663,'10','KANCHANPPALLE');
insert into village(mandalId,villageCode,villageName) Values(663,'11','K.KONDARAM');
insert into village(mandalId,villageCode,villageName) Values(663,'12','P.DOMALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(663,'13','M.DOMALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(663,'14','NARSING BATLA');
insert into village(mandalId,villageCode,villageName) Values(663,'15','KHUDAVANPUR');
insert into village(mandalId,villageCode,villageName) Values(663,'16','DONAKAL');
insert into village(mandalId,villageCode,villageName) Values(663,'17','GUNDLA PALLE');
insert into village(mandalId,villageCode,villageName) Values(663,'18','ANANTHARAM');
insert into village(mandalId,villageCode,villageName) Values(663,'19','KOTHA PALLE');
insert into village(mandalId,villageCode,villageName) Values(663,'20','NALGONDA(URBAN)');
insert into village(mandalId,villageCode,villageName) Values(663,'21','GOLLA GUDA(URBAN)');
insert into village(mandalId,villageCode,villageName) Values(663,'22','G.K.ANNARAM');
insert into village(mandalId,villageCode,villageName) Values(663,'23','MEDLA DUPPALA PALLE');
insert into village(mandalId,villageCode,villageName) Values(663,'24','ANNAREDDY GUDA');
insert into village(mandalId,villageCode,villageName) Values(663,'25','VELUGU PALLE');
insert into village(mandalId,villageCode,villageName) Values(663,'26','RASOOLPUR');
insert into village(mandalId,villageCode,villageName) Values(663,'27','MUSHAM PALLE');
insert into village(mandalId,villageCode,villageName) Values(663,'28','GANDHAMVARIGUDEM');
insert into village(mandalId,villageCode,villageName) Values(663,'29','MAMILLAGUDA');
insert into village(mandalId,villageCode,villageName) Values(663,'30','ARJALABAVI');




insert into village(mandalId,villageCode,villageName) Values(664,'1','KOTHULARAM');
insert into village(mandalId,villageCode,villageName) Values(664,'2','KISTAPURAM');
insert into village(mandalId,villageCode,villageName) Values(664,'3','IPPARTHY');
insert into village(mandalId,villageCode,villageName) Values(664,'4','PALIVELA');
insert into village(mandalId,villageCode,villageName) Values(664,'5','VOOKONDI');
insert into village(mandalId,villageCode,villageName) Values(664,'6','RATHI PALLE');
insert into village(mandalId,villageCode,villageName) Values(664,'7','PULIPALPULA');
insert into village(mandalId,villageCode,villageName) Values(664,'8','KALAVALA PALLE');
insert into village(mandalId,villageCode,villageName) Values(664,'9','GUDAPUR');
insert into village(mandalId,villageCode,villageName) Values(664,'10','JAMASTHAN PALLE');
insert into village(mandalId,villageCode,villageName) Values(664,'11','SINGARAM');
insert into village(mandalId,villageCode,villageName) Values(664,'12','KASLAPURAM');
insert into village(mandalId,villageCode,villageName) Values(664,'13','CHEEKATI MAMIDI');
insert into village(mandalId,villageCode,villageName) Values(664,'14','KOMPALLE');
insert into village(mandalId,villageCode,villageName) Values(664,'15','CHALMEDA');
insert into village(mandalId,villageCode,villageName) Values(664,'16','VELMAKANNE');
insert into village(mandalId,villageCode,villageName) Values(664,'17','MARRIGUDEM');
insert into village(mandalId,villageCode,villageName) Values(664,'18','KALVAKUNTLA');
insert into village(mandalId,villageCode,villageName) Values(664,'19','CHOLLEDU');
insert into village(mandalId,villageCode,villageName) Values(664,'20','MUNUGODE');
insert into village(mandalId,villageCode,villageName) Values(664,'21','SOLIPUR');
insert into village(mandalId,villageCode,villageName) Values(664,'22','KORATIKAL');



insert into village(mandalId,villageCode,villageName) Values(665,'1','RACHAKONDA');
insert into village(mandalId,villageCode,villageName) Values(665,'2','MAHAMMADABAD');
insert into village(mandalId,villageCode,villageName) Values(665,'3','GUDDI MALKAPURAM');
insert into village(mandalId,villageCode,villageName) Values(665,'4','KOTHULAPURAM');
insert into village(mandalId,villageCode,villageName) Values(665,'5','CHIMIRIYALA');
insert into village(mandalId,villageCode,villageName) Values(665,'6','NARAYANAPUR');
insert into village(mandalId,villageCode,villageName) Values(665,'7','KOTHAGUDEM');
insert into village(mandalId,villageCode,villageName) Values(665,'8','KANKANAL GUDEM');
insert into village(mandalId,villageCode,villageName) Values(665,'9','SARVAIL');
insert into village(mandalId,villageCode,villageName) Values(665,'10','GUJJA');
insert into village(mandalId,villageCode,villageName) Values(665,'11','PUTTAPAKA');
insert into village(mandalId,villageCode,villageName) Values(665,'12','JANGAM');
insert into village(mandalId,villageCode,villageName) Values(665,'13','VAILA PALLE');
insert into village(mandalId,villageCode,villageName) Values(665,'14','CHILLAPURAM');



insert into village(mandalId,villageCode,villageName) Values(3,10,'ADILABAD (U)');
insert into village(mandalId,villageCode,villageName) Values(3,30,'ANKAPOOR');
insert into village(mandalId,villageCode,villageName) Values(3,26,'ANKOLI');
insert into village(mandalId,villageCode,villageName) Values(3,18,'ANUKUNTA');
insert into village(mandalId,villageCode,villageName) Values(3,8,'ARLI (BUZURG)');
insert into village(mandalId,villageCode,villageName) Values(3,31,'ASODA BHURKI');
insert into village(mandalId,villageCode,villageName) Values(3,15,'BATTISAWARGAON');
insert into village(mandalId,villageCode,villageName) Values(3,37,'BELLURI');
insert into village(mandalId,villageCode,villageName) Values(3,11,'B HEEM SERI');
insert into village(mandalId,villageCode,villageName) Values(3,14,'BHUKTAPUR');
insert into village(mandalId,villageCode,villageName) Values(3,34,'BORENUR');
insert into village(mandalId,villageCode,villageName) Values(3,5,'CHANDA');
insert into village(mandalId,villageCode,villageName) Values(3,45,'CHICHADHARI');
insert into village(mandalId,villageCode,villageName) Values(3,29,'CHINCHUGHAT');
insert into village(mandalId,villageCode,villageName) Values(3,16,'DASNAPUR');
insert into village(mandalId,villageCode,villageName) Values(3,1,'DIMMA');
insert into village(mandalId,villageCode,villageName) Values(3,6,'GANESHPUR (D)');
insert into village(mandalId,villageCode,villageName) Values(3,42,'HATHIGUTTA');
insert into village(mandalId,villageCode,villageName) Values(3,4,'JAMDAPUR');
insert into village(mandalId,villageCode,villageName) Values(3,36,'JAMULDHARI');
insert into village(mandalId,villageCode,villageName) Values(3,23,'KACHKANTI');
insert into village(mandalId,villageCode,villageName) Values(3,44,'KHANAPOOR');
insert into village(mandalId,villageCode,villageName) Values(3,17,'KHANAPUR');
insert into village(mandalId,villageCode,villageName) Values(3,39,'KHANDALA (U)');
insert into village(mandalId,villageCode,villageName) Values(3,33,'KOTTUR (NEVEGAON)');
insert into village(mandalId,villageCode,villageName) Values(3,20,'KUMBHAJHERI');
insert into village(mandalId,villageCode,villageName) Values(3,7,'LANDASANGVI');
insert into village(mandalId,villageCode,villageName) Values(3,40,'LOHARA');
insert into village(mandalId,villageCode,villageName) Values(3,32,'LOKARI');
insert into village(mandalId,villageCode,villageName) Values(3,27,'MALEBORGAON');
insert into village(mandalId,villageCode,villageName) Values(3,3,'MALLAPUR (D)');
insert into village(mandalId,villageCode,villageName) Values(3,43,'MAREGAON');
insert into village(mandalId,villageCode,villageName) Values(3,25,'MAVALA');
insert into village(mandalId,villageCode,villageName) Values(3,9,'NISHANGHAT');
insert into village(mandalId,villageCode,villageName) Values(3,38,'PIPPALDHARI');
insert into village(mandalId,villageCode,villageName) Values(3,2,'POCHARA');
insert into village(mandalId,villageCode,villageName) Values(3,21,'RAMAI');
insert into village(mandalId,villageCode,villageName) Values(3,12,'RAMPOOR (ROYATI)');
insert into village(mandalId,villageCode,villageName) Values(3,19,'TAKLI');
insert into village(mandalId,villageCode,villageName) Values(3,13,'TARADA (SRIMATH)');
insert into village(mandalId,villageCode,villageName) Values(3,41,'TIPPA');
insert into village(mandalId,villageCode,villageName) Values(3,24,'TONTOTOI');
insert into village(mandalId,villageCode,villageName) Values(3,28,'WAGHAPUR');
insert into village(mandalId,villageCode,villageName) Values(3,35,'WANWAT');
insert into village(mandalId,villageCode,villageName) Values(3,22,'YAPALGUDA');
insert into village(mandalId,villageCode,villageName) Values(3,2,'ADA');
insert into village(mandalId,villageCode,villageName) Values(3,48,'ADA - DASNAPUR');
insert into village(mandalId,villageCode,villageName) Values(3,64,'ADDAGHAT');
insert into village(mandalId,villageCode,villageName) Values(3,26,'ANKUSAPUR');
insert into village(mandalId,villageCode,villageName) Values(3,31,'APPEPALLE');
insert into village(mandalId,villageCode,villageName) Values(3,27,'ASIFAB AD');
insert into village(mandalId,villageCode,villageName) Values(3,25,'B AB APUR');
insert into village(mandalId,villageCode,villageName) Values(3,41,'BALEGAON');
insert into village(mandalId,villageCode,villageName) Values(3,53,'BALHANPUR');
insert into village(mandalId,villageCode,villageName) Values(3,29,'BURUGUDA');
insert into village(mandalId,villageCode,villageName) Values(3,10,'CHERPALLE');
insert into village(mandalId,villageCode,villageName) Values(3,35,'CHILATIGUDA');
insert into village(mandalId,villageCode,villageName) Values(3,58,'CHIRRAKUNTA');
insert into village(mandalId,villageCode,villageName) Values(3,22,'DADPAPUR');




insert into village(mandalId,villageCode,villageName) Values(1,20,'ARLI KHURD');
insert into village(mandalId,villageCode,villageName) Values(1,26,'BHARAMPUR');
insert into village(mandalId,villageCode,villageName) Values(1,15,'DEHEGAON');
insert into village(mandalId,villageCode,villageName) Values(1,21,'DEVAPUR');
insert into village(mandalId,villageCode,villageName) Values(1,13,'DORLI');
insert into village(mandalId,villageCode,villageName) Values(1,18,'JHARI  ');
insert into village(mandalId,villageCode,villageName) Values(1,9,'KAJJARLA');
insert into village(mandalId,villageCode,villageName) Values(1,14,'KAPPARDEVI');
insert into village(mandalId,villageCode,villageName) Values(1,8,'KHODAD');
insert into village(mandalId,villageCode,villageName) Values(1,1,'KOSAI');
insert into village(mandalId,villageCode,villageName) Values(1,11,'KOTHUR');
insert into village(mandalId,villageCode,villageName) Values(1,4,'KUCHALAPOOR');
insert into village(mandalId,villageCode,villageName) Values(1,23,'LACHAMPUR');
insert into village(mandalId,villageCode,villageName) Values(1,5,'LINGI');
insert into village(mandalId,villageCode,villageName) Values(1,25,'MADNAPOOR');
insert into village(mandalId,villageCode,villageName) Values(1,27,'NANDIGAON');
insert into village(mandalId,villageCode,villageName) Values(1,2,'PALASIBUZURG');
insert into village(mandalId,villageCode,villageName) Values(1,3,'PALASIKHURD');
insert into village(mandalId,villageCode,villageName) Values(1,24,'PALLE BUZURG');
insert into village(mandalId,villageCode,villageName) Values(1,28,'PALLE KHURD');
insert into village(mandalId,villageCode,villageName) Values(1,22,'PANGADPIPRI');
insert into village(mandalId,villageCode,villageName) Values(1,17,'RATNAPUR');
insert into village(mandalId,villageCode,villageName) Values(1,10,'RUYYADI');
insert into village(mandalId,villageCode,villageName) Values(1,19,'SAKNAPOOR');
insert into village(mandalId,villageCode,villageName) Values(1,6,'SUNKIDI');
insert into village(mandalId,villageCode,villageName) Values(1,12,'TALAMADUGU');
insert into village(mandalId,villageCode,villageName) Values(1,7,'UMADAM');
insert into village(mandalId,villageCode,villageName) Values(1,16,'UMREI');



insert into village(mandalId,villageCode,villageName) Values(2,27,'AMBUGAON');
insert into village(mandalId,villageCode,villageName) Values(2,17,'ANDARBANDH');
insert into village(mandalId,villageCode,villageName) Values(2,4,'ANTARGAON');
insert into village(mandalId,villageCode,villageName) Values(2,5,'ARLI (T)');
insert into village(mandalId,villageCode,villageName) Values(2,11,'BABBAKNCHI');
insert into village(mandalId,villageCode,villageName) Values(2,23,'BANDALNAGAPUR');
insert into village(mandalId,villageCode,villageName) Values(2,16,'BELSARI RAMPUR');
insert into village(mandalId,villageCode,villageName) Values(2,12,'BHEEMPOOR');
insert into village(mandalId,villageCode,villageName) Values(2,14,'DHANORA');
insert into village(mandalId,villageCode,villageName) Values(2,21,'DGHOTKURI');
insert into village(mandalId,villageCode,villageName) Values(2,26,'GIRGAON');
insert into village(mandalId,villageCode,villageName) Values(2,8,'GOLLAGHAT');
insert into village(mandalId,villageCode,villageName) Values(2,3,'GOMUTRI');
insert into village(mandalId,villageCode,villageName) Values(2,13,'GONA');
insert into village(mandalId,villageCode,villageName) Values(2,2,'GULEDI');
insert into village(mandalId,villageCode,villageName) Values(2,7,'GUNJALA');
insert into village(mandalId,villageCode,villageName) Values(2,30,'HASNAPUR');
insert into village(mandalId,villageCode,villageName) Values(2,24,'JAMDI');
insert into village(mandalId,villageCode,villageName) Values(2,15,'KAMATHWADA');
insert into village(mandalId,villageCode,villageName) Values(2,1,'KARANJI (T)');
insert into village(mandalId,villageCode,villageName) Values(2,19,'KHAPPERLA');
insert into village(mandalId,villageCode,villageName) Values(2,10,'NIPANI');
insert into village(mandalId,villageCode,villageName) Values(2,25,'PALODI (RAMNAGAR)');
insert into village(mandalId,villageCode,villageName) Values(2,20,'PIPPALKHOTI');
insert into village(mandalId,villageCode,villageName) Values(2,31,'PONNARI');
insert into village(mandalId,villageCode,villageName) Values(2,22,'SANVERGAON');
insert into village(mandalId,villageCode,villageName) Values(2,28,'TAMSI (B)');
insert into village(mandalId,villageCode,villageName) Values(2,9,'TAMSI (K)');
insert into village(mandalId,villageCode,villageName) Values(2,29,'WADDADI');
insert into village(mandalId,villageCode,villageName) Values(2,18,'WADGAON');
insert into village(mandalId,villageCode,villageName) Values(2,6,'WADOOR');






insert into village(mandalId,villageCode,villageName) Values(4,42,'ADA');
insert into village(mandalId,villageCode,villageName) Values(4,5,'AKOLI');
insert into village(mandalId,villageCode,villageName) Values(4,20,'AKURLA');
insert into village(mandalId,villageCode,villageName) Values(4,39,'AWALPUR');
insert into village(mandalId,villageCode,villageName) Values(4,13,'BAHADURPUR');
insert into village(mandalId,villageCode,villageName) Values(4,33,'BALAPUR');
insert into village(mandalId,villageCode,villageName) Values(4,37,'BALLORI');
insert into village(mandalId,villageCode,villageName) Values(4,46,'BELGAON');
insert into village(mandalId,villageCode,villageName) Values(4,36,'BHORAJ');
insert into village(mandalId,villageCode,villageName) Values(4,22,'DADAPOOR');
insert into village(mandalId,villageCode,villageName) Values(4,19,'DEEPAIGUDA');
insert into village(mandalId,villageCode,villageName) Values(4,7,'DOLLARA');
insert into village(mandalId,villageCode,villageName) Values(4,35,'FOUZPUR');
insert into village(mandalId,villageCode,villageName) Values(4,3,'GIMMA BUZURG');
insert into village(mandalId,villageCode,villageName) Values(4,25,'GIMMA KHURD');
insert into village(mandalId,villageCode,villageName) Values(4,27,'GUDA');
insert into village(mandalId,villageCode,villageName) Values(4,14,'HARIYALI');
insert into village(mandalId,villageCode,villageName) Values(4,31,'HASHAMPUR');
insert into village(mandalId,villageCode,villageName) Values(4,1,'HATHIGHAT');
insert into village(mandalId,villageCode,villageName) Values(4,43,'JAINAD');
insert into village(mandalId,villageCode,villageName) Values(4,54,'JUNONI');
insert into village(mandalId,villageCode,villageName) Values(4,6,'KAMAI');
insert into village(mandalId,villageCode,villageName) Values(4,50,'KAMTHA');
insert into village(mandalId,villageCode,villageName) Values(4,53,'KANPA (MEDIGUDA)');
insert into village(mandalId,villageCode,villageName) Values(4,16,'KARANJI');
insert into village(mandalId,villageCode,villageName) Values(4,10,'KARANWADI');
insert into village(mandalId,villageCode,villageName) Values(4,4,'KEDARPUR');
insert into village(mandalId,villageCode,villageName) Values(4,44,'KHAPRI');
insert into village(mandalId,villageCode,villageName) Values(4,15,'KODEKOTHA');
insert into village(mandalId,villageCode,villageName) Values(4,2,'KORTA');
insert into village(mandalId,villageCode,villageName) Values(4,12,'KOWTHA');
insert into village(mandalId,villageCode,villageName) Values(4,17,'KURA');
insert into village(mandalId,villageCode,villageName) Values(4,18,'KUTHOMPUR');
insert into village(mandalId,villageCode,villageName) Values(4,48,'LAXMIPUR (ULIGAN)');
insert into village(mandalId,villageCode,villageName) Values(4,21,'LEKARWADI');
insert into village(mandalId,villageCode,villageName) Values(4,47,'MAKODA');
insert into village(mandalId,villageCode,villageName) Values(4,8,'MANDAGADA');
insert into village(mandalId,villageCode,villageName) Values(4,55,'MANGURLA');
insert into village(mandalId,villageCode,villageName) Values(4,29,'MIRZAPUR');
insert into village(mandalId,villageCode,villageName) Values(4,41,'MUKTAPUR');
insert into village(mandalId,villageCode,villageName) Values(4,40,'NIRALA');
insert into village(mandalId,villageCode,villageName) Values(4,38,'NIZAMPUR');
insert into village(mandalId,villageCode,villageName) Values(4,51,'PARDI BUZURG');
insert into village(mandalId,villageCode,villageName) Values(4,52,'PARDI KHURD');
insert into village(mandalId,villageCode,villageName) Values(4,9,'PENDALWADA');
insert into village(mandalId,villageCode,villageName) Values(4,49,'PIPPALGAON');
insert into village(mandalId,villageCode,villageName) Values(4,24,'PIPPARWADA');
insert into village(mandalId,villageCode,villageName) Values(4,30,'POOSAI');
insert into village(mandalId,villageCode,villageName) Values(4,26,'RAMPUR TARAF');
insert into village(mandalId,villageCode,villageName) Values(4,11,'SANGVI (K)');
insert into village(mandalId,villageCode,villageName) Values(4,32,'SAVAPUR');
insert into village(mandalId,villageCode,villageName) Values(4,23,'SEKAPOOR');
insert into village(mandalId,villageCode,villageName) Values(4,28,'SIRSONNA');
insert into village(mandalId,villageCode,villageName) Values(4,34,'TARADA BUZURG');
insert into village(mandalId,villageCode,villageName) Values(4,45,'UMRI');




insert into village(mandalId,villageCode,villageName) Values(5,1,'AWALPUR');
insert into village(mandalId,villageCode,villageName) Values(5,17,'BELA');
insert into village(mandalId,villageCode,villageName) Values(5,21,'BHADI');
insert into village(mandalId,villageCode,villageName) Values(5,4,'BHEDODA');
insert into village(mandalId,villageCode,villageName) Values(5,14,'BHODOD (KOPSI)');
insert into village(mandalId,villageCode,villageName) Values(5,41,'BOREGAON');
insert into village(mandalId,villageCode,villageName) Values(5,25,'BURHANPUR');
insert into village(mandalId,villageCode,villageName) Values(5,31,'CHANDPALLE');
insert into village(mandalId,villageCode,villageName) Values(5,33,'CHAPRALA');
insert into village(mandalId,villageCode,villageName) Values(5,11,'DATALPUR');
insert into village(mandalId,villageCode,villageName) Values(5,12,'DEHEGAON');
insert into village(mandalId,villageCode,villageName) Values(5,18,'DHOPTALA');
insert into village(mandalId,villageCode,villageName) Values(5,46,'DOUNA');
insert into village(mandalId,villageCode,villageName) Values(5,24,'EKORI');
insert into village(mandalId,villageCode,villageName) Values(5,13,'GUDA');
insert into village(mandalId,villageCode,villageName) Values(5,26,'JUNONI');
insert into village(mandalId,villageCode,villageName) Values(5,5,'KAMGARPUR');
insert into village(mandalId,villageCode,villageName) Values(5,38,'KARONI (B)');
insert into village(mandalId,villageCode,villageName) Values(5,39,'KARONI (K)');
insert into village(mandalId,villageCode,villageName) Values(5,36,'KHADKI');
insert into village(mandalId,villageCode,villageName) Values(5,7,'KHAGDUR');
insert into village(mandalId,villageCode,villageName) Values(5,10,'KOBHAI');
insert into village(mandalId,villageCode,villageName) Values(5,9,'KOPARUZANA');
insert into village(mandalId,villageCode,villageName) Values(5,8,'MANGROOL');
insert into village(mandalId,villageCode,villageName) Values(5,6,'MANYARPUR');
insert into village(mandalId,villageCode,villageName) Values(5,23,'MASALA (BUZURG)');
insert into village(mandalId,villageCode,villageName) Values(5,42,'MASALA (KHURD)');
insert into village(mandalId,villageCode,villageName) Values(5,16,'MOHABATPUR');
insert into village(mandalId,villageCode,villageName) Values(5,27,'NAGRALA');
insert into village(mandalId,villageCode,villageName) Values(5,2,'NARAYANPUR');
insert into village(mandalId,villageCode,villageName) Values(5,28,'PATAN');
insert into village(mandalId,villageCode,villageName) Values(5,32,'PITGAON');
insert into village(mandalId,villageCode,villageName) Values(5,40,'POHAR');
insert into village(mandalId,villageCode,villageName) Values(5,30,'PONNALA');
insert into village(mandalId,villageCode,villageName) Values(5,29,'RAMKAM');
insert into village(mandalId,villageCode,villageName) Values(5,37,'SADARPUR');
insert into village(mandalId,villageCode,villageName) Values(5,45,'SAHEJ');
insert into village(mandalId,villageCode,villageName) Values(5,3,'SANGDI');
insert into village(mandalId,villageCode,villageName) Values(5,47,'SANGVI');
insert into village(mandalId,villageCode,villageName) Values(5,15,'SHAMSHABAD');
insert into village(mandalId,villageCode,villageName) Values(5,20,'SINGAPUR');
insert into village(mandalId,villageCode,villageName) Values(5,19,'SIRSANNA');
insert into village(mandalId,villageCode,villageName) Values(5,35,'SONKHOS');
insert into village(mandalId,villageCode,villageName) Values(5,43,'SYEDPUR');
insert into village(mandalId,villageCode,villageName) Values(5,22,'TAKLI');
insert into village(mandalId,villageCode,villageName) Values(5,44,'TOYAGUDA (KORA)');
insert into village(mandalId,villageCode,villageName) Values(5,34,'WARUR');




insert into village(mandalId,villageCode,villageName) Values(6,11,'ADEMEYON');
insert into village(mandalId,villageCode,villageName) Values(6,14,'ARJUNI');
insert into village(mandalId,villageCode,villageName) Values(6,31,'BABJHARI');
insert into village(mandalId,villageCode,villageName) Values(6,49,'BALANPUR');
insert into village(mandalId,villageCode,villageName) Values(6,38,'BHIMPUR');
insert into village(mandalId,villageCode,villageName) Values(6,29,'CHORGAON');
insert into village(mandalId,villageCode,villageName) Values(6,25,'DHABA (BUZURG)');
insert into village(mandalId,villageCode,villageName) Values(6,18,'DHABA(K)');
insert into village(mandalId,villageCode,villageName) Values(6,32,'DHUPAPUR');
insert into village(mandalId,villageCode,villageName) Values(6,4,'DONGARGAON');
insert into village(mandalId,villageCode,villageName) Values(6,33,'EMPALLE');
insert into village(mandalId,villageCode,villageName) Values(6,20,'GADIGUDA');
insert into village(mandalId,villageCode,villageName) Values(6,46,'GANGAPUR');
insert into village(mandalId,villageCode,villageName) Values(6,36,'GOURI');
insert into village(mandalId,villageCode,villageName) Values(6,41,'GUNDALA');
insert into village(mandalId,villageCode,villageName) Values(6,47,'GUNJALA');
insert into village(mandalId,villageCode,villageName) Values(6,17,'JHARI');
insert into village(mandalId,villageCode,villageName) Values(6,6,'KADODI');
insert into village(mandalId,villageCode,villageName) Values(6,27,'KHADKI');
insert into village(mandalId,villageCode,villageName) Values(6,40,'KHAIRDATWA');
insert into village(mandalId,villageCode,villageName) Values(6,3,'KHANDOW');
insert into village(mandalId,villageCode,villageName) Values(6,43,'KHANPUR');
insert into village(mandalId,villageCode,villageName) Values(6,22,'KOLAMA');
insert into village(mandalId,villageCode,villageName) Values(6,1,'KONDI');
insert into village(mandalId,villageCode,villageName) Values(6,8,'KOTHAPALLE (G)');
insert into village(mandalId,villageCode,villageName) Values(6,7,'KOUTHALA');
insert into village(mandalId,villageCode,villageName) Values(6,9,'KUNIKASA');
insert into village(mandalId,villageCode,villageName) Values(6,26,'LOKARI (B)');
insert into village(mandalId,villageCode,villageName) Values(6,16,'LOKARI (K)');
insert into village(mandalId,villageCode,villageName) Values(6,42,'MAHADAPUR');
insert into village(mandalId,villageCode,villageName) Values(6,44,'MAHAGAON');
insert into village(mandalId,villageCode,villageName) Values(6,52,'MALANGI');
insert into village(mandalId,villageCode,villageName) Values(6,53,'MALEPUR');
insert into village(mandalId,villageCode,villageName) Values(6,30,'MANJARI');
insert into village(mandalId,villageCode,villageName) Values(6,45,'MANKAPUR');
insert into village(mandalId,villageCode,villageName) Values(6,24,'MAREGAON');
insert into village(mandalId,villageCode,villageName) Values(6,51,'NAGOLKONDA');
insert into village(mandalId,villageCode,villageName) Values(6,39,'NARNOOR');
insert into village(mandalId,villageCode,villageName) Values(6,23,'PARASWADA (B)');
insert into village(mandalId,villageCode,villageName) Values(6,15,'PARASWADA (K)');
insert into village(mandalId,villageCode,villageName) Values(6,13,'PIPRI');
insert into village(mandalId,villageCode,villageName) Values(6,35,'POWNUR');
insert into village(mandalId,villageCode,villageName) Values(6,19,'PUNAGUDA');
insert into village(mandalId,villageCode,villageName) Values(6,2,'RAMPUR');
insert into village(mandalId,villageCode,villageName) Values(6,21,'RUPAPUR');
insert into village(mandalId,villageCode,villageName) Values(6,34,'SANGVI');
insert into village(mandalId,villageCode,villageName) Values(6,12,'SAWARI');
insert into village(mandalId,villageCode,villageName) Values(6,5,'SEDWAI');
insert into village(mandalId,villageCode,villageName) Values(6,50,'SONAPUR');
insert into village(mandalId,villageCode,villageName) Values(6,28,'SUNGAPUR');
insert into village(mandalId,villageCode,villageName) Values(6,48,'TADIHADPANUR');
insert into village(mandalId,villageCode,villageName) Values(6,37,'UMRI');
insert into village(mandalId,villageCode,villageName) Values(6,10,'WARKWAI');





insert into village(mandalId,villageCode,villageName) Values(7,33,'ANJI');
insert into village(mandalId,villageCode,villageName) Values(7,5,'BURSANPATAR');
insert into village(mandalId,villageCode,villageName) Values(7,31,'DASNAPUR');
insert into village(mandalId,villageCode,villageName) Values(7,2,'DEVAPUR');
insert into village(mandalId,villageCode,villageName) Values(7,11,'DHANNURA (B)');
insert into village(mandalId,villageCode,villageName) Values(7,12,'DHANNURA (K)');
insert into village(mandalId,villageCode,villageName) Values(7,20,'DHARMASAGAR');
insert into village(mandalId,villageCode,villageName) Values(7,7,'DODANA');
insert into village(mandalId,villageCode,villageName) Values(7,29,'DONGARGAON');
insert into village(mandalId,villageCode,villageName) Values(7,6,'GATTEPALLE');
insert into village(mandalId,villageCode,villageName) Values(7,3,'GINNERA');
insert into village(mandalId,villageCode,villageName) Values(7,13,'GOUREEPUR');
insert into village(mandalId,villageCode,villageName) Values(7,17,'HARKAPUR');
insert into village(mandalId,villageCode,villageName) Values(7,16,'HEERAPUR');
insert into village(mandalId,villageCode,villageName) Values(7,8,'INDERVELLY');
insert into village(mandalId,villageCode,villageName) Values(7,4,'INDERVELLY (K)');
insert into village(mandalId,villageCode,villageName) Values(7,18,'KESLAGUDA');
insert into village(mandalId,villageCode,villageName) Values(7,15,'KESLAPUR');
insert into village(mandalId,villageCode,villageName) Values(7,24,'KONDAPUR');
insert into village(mandalId,villageCode,villageName) Values(7,28,'LACHIMPUR (B)');
insert into village(mandalId,villageCode,villageName) Values(7,26,'LACHIMPUR (K)');
insert into village(mandalId,villageCode,villageName) Values(7,22,'LAKAMPUR');
insert into village(mandalId,villageCode,villageName) Values(7,19,'MALLAPUR');
insert into village(mandalId,villageCode,villageName) Values(7,32,'MAMIDIGUDA');
insert into village(mandalId,villageCode,villageName) Values(7,14,'MENDAPALLE');
insert into village(mandalId,villageCode,villageName) Values(7,10,'MUTHNUR');
insert into village(mandalId,villageCode,villageName) Values(7,1,'PIPRI');
insert into village(mandalId,villageCode,villageName) Values(7,25,'POCHAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(7,23,'RAMPUR (B)');
insert into village(mandalId,villageCode,villageName) Values(7,21,'TEJAPUR');
insert into village(mandalId,villageCode,villageName) Values(7,34,'WADAGAON');
insert into village(mandalId,villageCode,villageName) Values(7,27,'WAIPET');
insert into village(mandalId,villageCode,villageName) Values(7,30,'WALGANDA HEERAPUR');
insert into village(mandalId,villageCode,villageName) Values(7,9,'YAMAIKUNTA');




insert into village(mandalId,villageCode,villageName) Values(8,20,'BELLURI');
insert into village(mandalId,villageCode,villageName) Values(8,9,'DHAMPUR');
insert into village(mandalId,villageCode,villageName) Values(8,13,'DONGARGAON');
insert into village(mandalId,villageCode,villageName) Values(8,17,'GONDHARKAPUR');
insert into village(mandalId,villageCode,villageName) Values(8,7,'GUDIHATHINUR');
insert into village(mandalId,villageCode,villageName) Values(8,16,'GURUJ');
insert into village(mandalId,villageCode,villageName) Values(8,2,'KAMALAPUR');
insert into village(mandalId,villageCode,villageName) Values(8,14,'KOLHARI');
insert into village(mandalId,villageCode,villageName) Values(8,6,'LINGAPUR');
insert into village(mandalId,villageCode,villageName) Values(8,8,'MACHAPUR');
insert into village(mandalId,villageCode,villageName) Values(8,4,'MALKAPUR');
insert into village(mandalId,villageCode,villageName) Values(8,12,'MANNUR');
insert into village(mandalId,villageCode,villageName) Values(8,10,'MUTHNUR');
insert into village(mandalId,villageCode,villageName) Values(8,11,'NERADIGONDA');
insert into village(mandalId,villageCode,villageName) Values(8,18,'RENDLS BORI');
insert into village(mandalId,villageCode,villageName) Values(8,3,'SEETAGONDI');
insert into village(mandalId,villageCode,villageName) Values(8,19,'SHANTAPUR');
insert into village(mandalId,villageCode,villageName) Values(8,21,'TEJAPUR');
insert into village(mandalId,villageCode,villageName) Values(8,5,'TOSHAM');
insert into village(mandalId,villageCode,villageName) Values(8,15,'UMRI(B)');
insert into village(mandalId,villageCode,villageName) Values(8,1,'VAIJAPUR');






insert into village(mandalId,villageCode,villageName) Values(9,17,'ADEGAON BUZURG');
insert into village(mandalId,villageCode,villageName) Values(9,1,'ADEGAON KHURD');
insert into village(mandalId,villageCode,villageName) Values(9,38,'BABJEPET');
insert into village(mandalId,villageCode,villageName) Values(9,4,'BABULDHOLE');
insert into village(mandalId,villageCode,villageName) Values(9,5,'BOREGAON');
insert into village(mandalId,villageCode,villageName) Values(9,19,'CHINCHOLI');
insert into village(mandalId,villageCode,villageName) Values(9,21,'DHABA  KHURD');
insert into village(mandalId,villageCode,villageName) Values(9,25,'DHARMAPURI');
insert into village(mandalId,villageCode,villageName) Values(9,12,'DHOBA BUZURG');
insert into village(mandalId,villageCode,villageName) Values(9,36,'GAIDPALLE');
insert into village(mandalId,villageCode,villageName) Values(9,37,'GANDIWAGU');
insert into village(mandalId,villageCode,villageName) Values(9,18,'GIRJAM');
insert into village(mandalId,villageCode,villageName) Values(9,2,'GUBBA');
insert into village(mandalId,villageCode,villageName) Values(9,33,'GUNDALA');
insert into village(mandalId,villageCode,villageName) Values(9,30,'GUNDI');
insert into village(mandalId,villageCode,villageName) Values(9,10,'HEERAPUR');
insert into village(mandalId,villageCode,villageName) Values(9,16,'ICHODA');
insert into village(mandalId,villageCode,villageName) Values(9,26,'JALDA');
insert into village(mandalId,villageCode,villageName) Values(9,15,'JAMIDI');
insert into village(mandalId,villageCode,villageName) Values(9,39,'JOGIPET');
insert into village(mandalId,villageCode,villageName) Values(9,3,'JUNNI');
insert into village(mandalId,villageCode,villageName) Values(9,6,'KAMGIR');
insert into village(mandalId,villageCode,villageName) Values(9,31,'KESHA PATNAM');
insert into village(mandalId,villageCode,villageName) Values(9,27,'KOKASMANNAR');
insert into village(mandalId,villageCode,villageName) Values(9,35,'LINGAPUR');
insert into village(mandalId,villageCode,villageName) Values(9,14,'MADAPUR');
insert into village(mandalId,villageCode,villageName) Values(9,23,'MALYAL');
insert into village(mandalId,villageCode,villageName) Values(9,24,'MANKAPUR');
insert into village(mandalId,villageCode,villageName) Values(9,28,'MOKHRA BUZURG');
insert into village(mandalId,villageCode,villageName) Values(9,29,'MOKHRA KHURD');
insert into village(mandalId,villageCode,villageName) Values(9,41,'NARAYANAPUR');
insert into village(mandalId,villageCode,villageName) Values(9,32,'NARSAPUR');
insert into village(mandalId,villageCode,villageName) Values(9,20,'NAVAGAON');
insert into village(mandalId,villageCode,villageName) Values(9,42,'NERADIGONDA (K)');
insert into village(mandalId,villageCode,villageName) Values(9,34,'NERODIGONDA');
insert into village(mandalId,villageCode,villageName) Values(9,7,'PONNA');
insert into village(mandalId,villageCode,villageName) Values(9,22,'SALYADA');
insert into village(mandalId,villageCode,villageName) Values(9,40,'SIRICHALMA');
insert into village(mandalId,villageCode,villageName) Values(9,9,'SIRIKONDA');
insert into village(mandalId,villageCode,villageName) Values(9,11,'SOANPALLE');
insert into village(mandalId,villageCode,villageName) Values(9,8,'SUNKIDI');
insert into village(mandalId,villageCode,villageName) Values(9,13,'TALAMADRI');





insert into village(mandalId,villageCode,villageName) Values(10,6,'ANANTHAPUR');
insert into village(mandalId,villageCode,villageName) Values(10,23,'BALANPUR');
insert into village(mandalId,villageCode,villageName) Values(10,26,'BANDREW');
insert into village(mandalId,villageCode,villageName) Values(10,24,'BAZARHATHNUR');
insert into village(mandalId,villageCode,villageName) Values(10,17,'BHOSRA');
insert into village(mandalId,villageCode,villageName) Values(10,14,'BHUTAI BUZURG');
insert into village(mandalId,villageCode,villageName) Values(10,11,'BHUTAI KHURD');
insert into village(mandalId,villageCode,villageName) Values(10,15,'CHINTAL SANGVI');
insert into village(mandalId,villageCode,villageName) Values(10,18,'DASAMPURI');
insert into village(mandalId,villageCode,villageName) Values(10,16,'DEHGAON');
insert into village(mandalId,villageCode,villageName) Values(10,10,'DHABADI');
insert into village(mandalId,villageCode,villageName) Values(10,21,'DIGNOOR');
insert into village(mandalId,villageCode,villageName) Values(10,20,'GANGAPUR');
insert into village(mandalId,villageCode,villageName) Values(10,2,'GIRJAI');
insert into village(mandalId,villageCode,villageName) Values(10,22,'GIRNUR');
insert into village(mandalId,villageCode,villageName) Values(10,9,'GOKONDA');
insert into village(mandalId,villageCode,villageName) Values(10,4,'HARKAI');
insert into village(mandalId,villageCode,villageName) Values(10,13,'JATARLA');
insert into village(mandalId,villageCode,villageName) Values(10,29,'KANDLI');
insert into village(mandalId,villageCode,villageName) Values(10,28,'KANKADURGA');
insert into village(mandalId,villageCode,villageName) Values(10,5,'KINNERPALLE');
insert into village(mandalId,villageCode,villageName) Values(10,25,'KOLHARI');
insert into village(mandalId,villageCode,villageName) Values(10,12,'MANKAPUR (P)');
insert into village(mandalId,villageCode,villageName) Values(10,27,'MOHADA');
insert into village(mandalId,villageCode,villageName) Values(10,3,'MORKHANDI');
insert into village(mandalId,villageCode,villageName) Values(10,30,'PIPRI');
insert into village(mandalId,villageCode,villageName) Values(10,19,'RAMPUR');
insert into village(mandalId,villageCode,villageName) Values(10,7,'TEMBI');
insert into village(mandalId,villageCode,villageName) Values(10,1,'UMERDA BUZURG');
insert into village(mandalId,villageCode,villageName) Values(10,31,'WARTHAMANOOR');
insert into village(mandalId,villageCode,villageName) Values(10,8,'YESAPUR');



insert into village(mandalId,villageCode,villageName) Values(11,34,'ANDURU');
insert into village(mandalId,villageCode,villageName) Values(11,19,'BABERA');
insert into village(mandalId,villageCode,villageName) Values(11,38,'BAHRAPUR');
insert into village(mandalId,villageCode,villageName) Values(11,33,'BIRLAGONDI');
insert into village(mandalId,villageCode,villageName) Values(11,26,'BOATH (K)');
insert into village(mandalId,villageCode,villageName) Values(11,27,'BOATH BUZURG');
insert into village(mandalId,villageCode,villageName) Values(11,3,'CHINTALABORI');
insert into village(mandalId,villageCode,villageName) Values(11,11,'DEMMI');
insert into village(mandalId,villageCode,villageName) Values(11,8,'DERA (D)');
insert into village(mandalId,villageCode,villageName) Values(11,37,'DHANNUR BUZURG');
insert into village(mandalId,villageCode,villageName) Values(11,36,'DHANNUR KHURD');
insert into village(mandalId,villageCode,villageName) Values(11,4,'GHANPUR');
insert into village(mandalId,villageCode,villageName) Values(11,2,'GOLLAPUR');
insert into village(mandalId,villageCode,villageName) Values(11,12,'KANGUTTA');
insert into village(mandalId,villageCode,villageName) Values(11,20,'KANTEGAON');
insert into village(mandalId,villageCode,villageName) Values(11,16,'KARATHWADA');
insert into village(mandalId,villageCode,villageName) Values(11,25,'KESLAPUR');
insert into village(mandalId,villageCode,villageName) Values(11,28,'KISTAPUR');
insert into village(mandalId,villageCode,villageName) Values(11,10,'KOWTHA BUZURG');
insert into village(mandalId,villageCode,villageName) Values(11,7,'KOWTHA KHURD');
insert into village(mandalId,villageCode,villageName) Values(11,31,'KUCHALAPUR');
insert into village(mandalId,villageCode,villageName) Values(11,22,'MARLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(11,15,'MEDI');
insert into village(mandalId,villageCode,villageName) Values(11,39,'NAGAPUR');
insert into village(mandalId,villageCode,villageName) Values(11,18,'NAKKALAWADA');
insert into village(mandalId,villageCode,villageName) Values(11,35,'NARAYANPUR');
insert into village(mandalId,villageCode,villageName) Values(11,21,'NIGINI');
insert into village(mandalId,villageCode,villageName) Values(11,5,'PARDI BUZURG');
insert into village(mandalId,villageCode,villageName) Values(11,17,'PARDI KHURD');
insert into village(mandalId,villageCode,villageName) Values(11,24,'PATNAPUR');
insert into village(mandalId,villageCode,villageName) Values(11,32,'PIPPALADHARI');
insert into village(mandalId,villageCode,villageName) Values(11,30,'POCHERA');
insert into village(mandalId,villageCode,villageName) Values(11,13,'SAKHERA');
insert into village(mandalId,villageCode,villageName) Values(11,9,'SANGVI');
insert into village(mandalId,villageCode,villageName) Values(11,6,'SONALA');
insert into village(mandalId,villageCode,villageName) Values(11,23,'SURADAPUR');
insert into village(mandalId,villageCode,villageName) Values(11,14,'TEWTI');
insert into village(mandalId,villageCode,villageName) Values(11,29,'VENKATAPUR');
insert into village(mandalId,villageCode,villageName) Values(11,1,'WAJAR');





insert into village(mandalId,villageCode,villageName) Values(12,27,'BONDADI');
insert into village(mandalId,villageCode,villageName) Values(12,36,'BONDEMREGOD');
insert into village(mandalId,villageCode,villageName) Values(12,42,'BORAGAON');
insert into village(mandalId,villageCode,villageName) Values(12,25,'BUDDIKONDA');
insert into village(mandalId,villageCode,villageName) Values(12,20,'BUGGARAM');
insert into village(mandalId,villageCode,villageName) Values(12,17,'CHINCHOLI');
insert into village(mandalId,villageCode,villageName) Values(12,26,'DARBA');
insert into village(mandalId,villageCode,villageName) Values(12,41,'DHONNORA');
insert into village(mandalId,villageCode,villageName) Values(12,1,'GAJLI');
insert into village(mandalId,villageCode,villageName) Values(12,2,'GANDHARI');
insert into village(mandalId,villageCode,villageName) Values(12,33,'ISPUR');
insert into village(mandalId,villageCode,villageName) Values(12,10,'JASNAPUR');
insert into village(mandalId,villageCode,villageName) Values(12,31,'KISHTAPUR');
insert into village(mandalId,villageCode,villageName) Values(12,37,'KORATKAL BUZURG');
insert into village(mandalId,villageCode,villageName) Values(12,39,'KORATKAL KHURD');
insert into village(mandalId,villageCode,villageName) Values(12,13,'KUMARI');
insert into village(mandalId,villageCode,villageName) Values(12,7,'KUNTALA (BUZURG)');
insert into village(mandalId,villageCode,villageName) Values(12,8,'KUNTALA (KHURD)');
insert into village(mandalId,villageCode,villageName) Values(12,3,'KUPTI (BUZURG)');
insert into village(mandalId,villageCode,villageName) Values(12,12,'KUPTI (KHURD)');
insert into village(mandalId,villageCode,villageName) Values(12,40,'LINGATLA');
insert into village(mandalId,villageCode,villageName) Values(12,18,'LOKHAMPUR');
insert into village(mandalId,villageCode,villageName) Values(12,5,'MADHAPUR');
insert into village(mandalId,villageCode,villageName) Values(12,14,'MALKALPAHAD');
insert into village(mandalId,villageCode,villageName) Values(12,21,'NAGAMALYAL');
insert into village(mandalId,villageCode,villageName) Values(12,38,'NARAYANAPUR');
insert into village(mandalId,villageCode,villageName) Values(12,24,'NERADIGONDA');
insert into village(mandalId,villageCode,villageName) Values(12,22,'PEECHRA');
insert into village(mandalId,villageCode,villageName) Values(12,35,'PURUSHOTHAMPUR');
insert into village(mandalId,villageCode,villageName) Values(12,15,'RAIPUR');
insert into village(mandalId,villageCode,villageName) Values(12,32,'RAJURA');
insert into village(mandalId,villageCode,villageName) Values(12,23,'ROLMANDA');
insert into village(mandalId,villageCode,villageName) Values(12,30,'SHANKARAPUR');
insert into village(mandalId,villageCode,villageName) Values(12,19,'SOWERGAON');
insert into village(mandalId,villageCode,villageName) Values(12,28,'SURDAPUR');
insert into village(mandalId,villageCode,villageName) Values(12,4,'TARNAM (BUZURG)');
insert into village(mandalId,villageCode,villageName) Values(12,11,'TARNAM (KHURD)');
insert into village(mandalId,villageCode,villageName) Values(12,16,'TEJAPUR');
insert into village(mandalId,villageCode,villageName) Values(12,6,'VENKATAPUR');
insert into village(mandalId,villageCode,villageName) Values(12,29,'WADDUR');
insert into village(mandalId,villageCode,villageName) Values(12,9,'WAGDHARI');
insert into village(mandalId,villageCode,villageName) Values(12,34,'WANKIDI');




insert into village(mandalId,villageCode,villageName) Values(13,7,'ADELLI');
insert into village(mandalId,villageCode,villageName) Values(13,24,'ALUR');
insert into village(mandalId,villageCode,villageName) Values(13,27,'BAGIRATHIPUR');
insert into village(mandalId,villageCode,villageName) Values(13,17,'BEERVELLI');
insert into village(mandalId,villageCode,villageName) Values(13,21,'BOREGAON');
insert into village(mandalId,villageCode,villageName) Values(13,26,'CHINCHOLI (BUZURG)');
insert into village(mandalId,villageCode,villageName) Values(13,12,'CHINCHOLI (MALAK)');
insert into village(mandalId,villageCode,villageName) Values(13,22,'DHANI');
insert into village(mandalId,villageCode,villageName) Values(13,19,'GODSERA');
insert into village(mandalId,villageCode,villageName) Values(13,23,'GOPALPET');
insert into village(mandalId,villageCode,villageName) Values(13,9,'JAM');
insert into village(mandalId,villageCode,villageName) Values(13,1,'JEWLY');
insert into village(mandalId,villageCode,villageName) Values(13,13,'KAMKATI');
insert into village(mandalId,villageCode,villageName) Values(13,6,'KHARJI');
insert into village(mandalId,villageCode,villageName) Values(13,11,'KOWTLA (BUZURG)');
insert into village(mandalId,villageCode,villageName) Values(13,3,'KUPTI');
insert into village(mandalId,villageCode,villageName) Values(13,25,'LAKSHMIPUR');
insert into village(mandalId,villageCode,villageName) Values(13,8,'NAGAPUR');
insert into village(mandalId,villageCode,villageName) Values(13,5,'PENDALDHARI');
insert into village(mandalId,villageCode,villageName) Values(13,16,'PIYARAMUR');
insert into village(mandalId,villageCode,villageName) Values(13,4,'PONKUR');
insert into village(mandalId,villageCode,villageName) Values(13,2,'POTIA');
insert into village(mandalId,villageCode,villageName) Values(13,28,'RANAPUR');
insert into village(mandalId,villageCode,villageName) Values(13,10,'SARANGPUR');
insert into village(mandalId,villageCode,villageName) Values(13,15,'TANDRA');
insert into village(mandalId,villageCode,villageName) Values(13,14,'VAIKUNTAPUR');
insert into village(mandalId,villageCode,villageName) Values(13,18,'VANJAR');
insert into village(mandalId,villageCode,villageName) Values(13,20,'YAKARPALLE');



insert into village(mandalId,villageCode,villageName) Values(14,3,'AMBAGAON');
insert into village(mandalId,villageCode,villageName) Values(14,12,'AMBAKANTI');
insert into village(mandalId,villageCode,villageName) Values(14,19,'ANDHUR');
insert into village(mandalId,villageCode,villageName) Values(14,26,'ARLY (KHURD)');
insert into village(mandalId,villageCode,villageName) Values(14,20,'BAMINI (BUZURG)');
insert into village(mandalId,villageCode,villageName) Values(14,25,'BURGUPALLE');
insert into village(mandalId,villageCode,villageName) Values(14,8,'BURUGUPALLE');
insert into village(mandalId,villageCode,villageName) Values(14,28,'CHAKEPALLE');
insert into village(mandalId,villageCode,villageName) Values(14,27,'DONGARGAON');
insert into village(mandalId,villageCode,villageName) Values(14,6,'DOWNELLE');
insert into village(mandalId,villageCode,villageName) Values(14,10,'GULMADAGA');
insert into village(mandalId,villageCode,villageName) Values(14,23,'KALLUR');
insert into village(mandalId,villageCode,villageName) Values(14,13,'KUNTALA');
insert into village(mandalId,villageCode,villageName) Values(14,1,'LIMBA (BUZURG)');
insert into village(mandalId,villageCode,villageName) Values(14,15,'LIMBA (KHURD)');
insert into village(mandalId,villageCode,villageName) Values(14,2,'MEDANPUR');
insert into village(mandalId,villageCode,villageName) Values(14,24,'MUTAKAPALLE');
insert into village(mandalId,villageCode,villageName) Values(14,21,'NANDAN');
insert into village(mandalId,villageCode,villageName) Values(14,14,'OALA');
insert into village(mandalId,villageCode,villageName) Values(14,18,'PENCHIKALPAHAD');
insert into village(mandalId,villageCode,villageName) Values(14,11,'RAIPAHAD');
insert into village(mandalId,villageCode,villageName) Values(14,5,'RAJAPUR');
insert into village(mandalId,villageCode,villageName) Values(14,4,'SURYAPUR');
insert into village(mandalId,villageCode,villageName) Values(14,7,'TEKULPAHAD');
insert into village(mandalId,villageCode,villageName) Values(14,22,'TURATI');
insert into village(mandalId,villageCode,villageName) Values(14,9,'VELGUDHARI');
insert into village(mandalId,villageCode,villageName) Values(14,17,'VENKUR');
insert into village(mandalId,villageCode,villageName) Values(14,16,'VITTAPUR');


insert into village(mandalId,villageCode,villageName) Values(15,33,'ANTHARNI');
insert into village(mandalId,villageCode,villageName) Values(15,37,'BAKOT');
insert into village(mandalId,villageCode,villageName) Values(15,7,'BELGAON');
insert into village(mandalId,villageCode,villageName) Values(15,8,'BRAHMESWAR');
insert into village(mandalId,villageCode,villageName) Values(15,14,'CHATA');
insert into village(mandalId,villageCode,villageName) Values(15,20,'CHONDI');
insert into village(mandalId,villageCode,villageName) Values(15,16,'DARKUBEER');
insert into village(mandalId,villageCode,villageName) Values(15,6,'DODARNA');
insert into village(mandalId,villageCode,villageName) Values(15,27,'GODAPUR');
insert into village(mandalId,villageCode,villageName) Values(15,35,'GODSARA');
insert into village(mandalId,villageCode,villageName) Values(15,12,'HALDA');
insert into village(mandalId,villageCode,villageName) Values(15,26,'HAMPLI (BUZURG)');
insert into village(mandalId,villageCode,villageName) Values(15,3,'JAMGAON');
insert into village(mandalId,villageCode,villageName) Values(15,21,'JUMDA');
insert into village(mandalId,villageCode,villageName) Values(15,19,'KHASRA');
insert into village(mandalId,villageCode,villageName) Values(15,18,'KUBEER');
insert into village(mandalId,villageCode,villageName) Values(15,23,'KUPTI');
insert into village(mandalId,villageCode,villageName) Values(15,30,'LINGI');
insert into village(mandalId,villageCode,villageName) Values(15,34,'MALEGAON');
insert into village(mandalId,villageCode,villageName) Values(15,9,'MARLAGONDA');
insert into village(mandalId,villageCode,villageName) Values(15,29,'MOLA');
insert into village(mandalId,villageCode,villageName) Values(15,10,'NANDAPAHAD');
insert into village(mandalId,villageCode,villageName) Values(15,28,'NIGHWA');
insert into village(mandalId,villageCode,villageName) Values(15,1,'PALSI');
insert into village(mandalId,villageCode,villageName) Values(15,36,'PANGRA');
insert into village(mandalId,villageCode,villageName) Values(15,15,'PARDI (BUZURG)');
insert into village(mandalId,villageCode,villageName) Values(15,2,'PARDI KHURD');
insert into village(mandalId,villageCode,villageName) Values(15,17,'RAJURA');
insert into village(mandalId,villageCode,villageName) Values(15,4,'RANJANI');
insert into village(mandalId,villageCode,villageName) Values(15,22,'SANGVI');
insert into village(mandalId,villageCode,villageName) Values(15,32,'SANWALI');
insert into village(mandalId,villageCode,villageName) Values(15,38,'SAONA');
insert into village(mandalId,villageCode,villageName) Values(15,13,'SHIVANI');
insert into village(mandalId,villageCode,villageName) Values(15,5,'SIRPALLE');
insert into village(mandalId,villageCode,villageName) Values(15,25,'SONARI');
insert into village(mandalId,villageCode,villageName) Values(15,24,'VARNI');
insert into village(mandalId,villageCode,villageName) Values(15,11,'VEERAGOHAN');
insert into village(mandalId,villageCode,villageName) Values(15,31,'WAI');




insert into village(mandalId,villageCode,villageName) Values(16,17,'BABALGAON');
insert into village(mandalId,villageCode,villageName) Values(16,24,'BADGAON');
insert into village(mandalId,villageCode,villageName) Values(16,18,'BHAINSA');
insert into village(mandalId,villageCode,villageName) Values(16,11,'BIJJUR');
insert into village(mandalId,villageCode,villageName) Values(16,32,'BOREGAON (BUZURG)');
insert into village(mandalId,villageCode,villageName) Values(16,1,'CHICHOND');
insert into village(mandalId,villageCode,villageName) Values(16,9,'CHINTALABORI');
insert into village(mandalId,villageCode,villageName) Values(16,22,'DAHEGAON');
insert into village(mandalId,villageCode,villageName) Values(16,15,'EKGAON');
insert into village(mandalId,villageCode,villageName) Values(16,23,'ELEGAON');
insert into village(mandalId,villageCode,villageName) Values(16,7,'GUNDAGAON');
insert into village(mandalId,villageCode,villageName) Values(16,31,'HAMPOLI KHURD');
insert into village(mandalId,villageCode,villageName) Values(16,29,'HASGUL');
insert into village(mandalId,villageCode,villageName) Values(16,28,'KAMOL');
insert into village(mandalId,villageCode,villageName) Values(16,27,'KHATGAON');
insert into village(mandalId,villageCode,villageName) Values(16,10,'KOTALGAON');
insert into village(mandalId,villageCode,villageName) Values(16,2,'KUMBHI');
insert into village(mandalId,villageCode,villageName) Values(16,26,'KUMSARI');
insert into village(mandalId,villageCode,villageName) Values(16,4,'LINGA');
insert into village(mandalId,villageCode,villageName) Values(16,8,'MAHAGAON');
insert into village(mandalId,villageCode,villageName) Values(16,20,'MANJRI');
insert into village(mandalId,villageCode,villageName) Values(16,30,'MATEGAON');
insert into village(mandalId,villageCode,villageName) Values(16,5,'MIRZAPUR');
insert into village(mandalId,villageCode,villageName) Values(16,19,'PANGRI');
insert into village(mandalId,villageCode,villageName) Values(16,34,'PENDAPALLE');
insert into village(mandalId,villageCode,villageName) Values(16,16,'PIPRI');
insert into village(mandalId,villageCode,villageName) Values(16,6,'SIDDUR');
insert into village(mandalId,villageCode,villageName) Values(16,21,'SIRALA');
insert into village(mandalId,villageCode,villageName) Values(16,12,'SUNKLI');
insert into village(mandalId,villageCode,villageName) Values(16,3,'TAKLI');
insert into village(mandalId,villageCode,villageName) Values(16,13,'THIMMAPUR');
insert into village(mandalId,villageCode,villageName) Values(16,25,'WALEGAON');
insert into village(mandalId,villageCode,villageName) Values(16,14,'WANALPAHAD');
insert into village(mandalId,villageCode,villageName) Values(16,33,'WATOLI');



insert into village(mandalId,villageCode,villageName) Values(17,7,'BAMNI');
insert into village(mandalId,villageCode,villageName) Values(17,4,'BELTARODA');
insert into village(mandalId,villageCode,villageName) Values(17,15,'BEMBER');
insert into village(mandalId,villageCode,villageName) Values(17,5,'BHOSI');
insert into village(mandalId,villageCode,villageName) Values(17,9,'BOLSA');
insert into village(mandalId,villageCode,villageName) Values(17,8,'BONDRAT');
insert into village(mandalId,villageCode,villageName) Values(17,14,'BOREGAON (KHURD)');
insert into village(mandalId,villageCode,villageName) Values(17,10,'DHAGAON');
insert into village(mandalId,villageCode,villageName) Values(17,27,'DOULTABAD');
insert into village(mandalId,villageCode,villageName) Values(17,12,'ELVI');
insert into village(mandalId,villageCode,villageName) Values(17,11,'HANGIRGA');
insert into village(mandalId,villageCode,villageName) Values(17,16,'HIPNALLY');
insert into village(mandalId,villageCode,villageName) Values(17,29,'JEWLA (BUZURG)');
insert into village(mandalId,villageCode,villageName) Values(17,23,'JEWLA (KHURD)');
insert into village(mandalId,villageCode,villageName) Values(17,1,'JHARI (BUZURG)');
insert into village(mandalId,villageCode,villageName) Values(17,2,'JHARI (KHURD)');
insert into village(mandalId,villageCode,villageName) Values(17,22,'KALYANI');
insert into village(mandalId,villageCode,villageName) Values(17,31,'KESARELLY');
insert into village(mandalId,villageCode,villageName) Values(17,28,'KHARBALA');
insert into village(mandalId,villageCode,villageName) Values(17,17,'KOLUR');
insert into village(mandalId,villageCode,villageName) Values(17,20,'KUPLI');
insert into village(mandalId,villageCode,villageName) Values(17,6,'MAHALINGI');
insert into village(mandalId,villageCode,villageName) Values(17,18,'MASALGA');
insert into village(mandalId,villageCode,villageName) Values(17,19,'MUGLI');
insert into village(mandalId,villageCode,villageName) Values(17,26,'NANDGAM');
insert into village(mandalId,villageCode,villageName) Values(17,25,'SINGANGAM');
insert into village(mandalId,villageCode,villageName) Values(17,24,'TANUR');
insert into village(mandalId,villageCode,villageName) Values(17,30,'TONDALA');
insert into village(mandalId,villageCode,villageName) Values(17,13,'UMRI (KHURD)');
insert into village(mandalId,villageCode,villageName) Values(17,21,'WADGAON');
insert into village(mandalId,villageCode,villageName) Values(17,32,'WADHONE');
insert into village(mandalId,villageCode,villageName) Values(17,3,'WADJHARI');
insert into village(mandalId,villageCode,villageName) Values(17,33,'YELLAWAT');




insert into village(mandalId,villageCode,villageName) Values(18,21,'ASHTA');
insert into village(mandalId,villageCode,villageName) Values(18,34,'BASAR');
insert into village(mandalId,villageCode,villageName) Values(18,26,'BIDRALLI');
insert into village(mandalId,villageCode,villageName) Values(18,10,'BOREGAON');
insert into village(mandalId,villageCode,villageName) Values(18,20,'BRAHMANGAON');
insert into village(mandalId,villageCode,villageName) Values(18,15,'CHANDAPUR');
insert into village(mandalId,villageCode,villageName) Values(18,5,'CHINCHALA');
insert into village(mandalId,villageCode,villageName) Values(18,8,'CHINTAKUNTA');
insert into village(mandalId,villageCode,villageName) Values(18,28,'DHONDAPUR');
insert into village(mandalId,villageCode,villageName) Values(18,7,'EDBID');
insert into village(mandalId,villageCode,villageName) Values(18,18,'GANORA');
insert into village(mandalId,villageCode,villageName) Values(18,14,'GOVINDAPUR');
insert into village(mandalId,villageCode,villageName) Values(18,13,'GUDUR');
insert into village(mandalId,villageCode,villageName) Values(18,11,'JALALPUR');
insert into village(mandalId,villageCode,villageName) Values(18,12,'KAREGAON');
insert into village(mandalId,villageCode,villageName) Values(18,23,'KIRGUL (BUZURG)');
insert into village(mandalId,villageCode,villageName) Values(18,25,'KIRGUL (KHURD)');
insert into village(mandalId,villageCode,villageName) Values(18,36,'KOWTHA');
insert into village(mandalId,villageCode,villageName) Values(18,29,'LABDI');
insert into village(mandalId,villageCode,villageName) Values(18,2,'MACHKAL');
insert into village(mandalId,villageCode,villageName) Values(18,32,'MAHADPUR');
insert into village(mandalId,villageCode,villageName) Values(18,33,'MAILAPUR');
insert into village(mandalId,villageCode,villageName) Values(18,3,'MUDGAL');
insert into village(mandalId,villageCode,villageName) Values(18,17,'MUDHOLE');
insert into village(mandalId,villageCode,villageName) Values(18,40,'PIPRI');
insert into village(mandalId,villageCode,villageName) Values(18,1,'RAMTEK');
insert into village(mandalId,villageCode,villageName) Values(18,30,'RATNAPUR');
insert into village(mandalId,villageCode,villageName) Values(18,31,'RAVINDAPUR');
insert into village(mandalId,villageCode,villageName) Values(18,35,'RENUKAPUR');
insert into village(mandalId,villageCode,villageName) Values(18,19,'RIUVI');
insert into village(mandalId,villageCode,villageName) Values(18,38,'SALAPUR');
insert into village(mandalId,villageCode,villageName) Values(18,39,'SAWARGAON');
insert into village(mandalId,villageCode,villageName) Values(18,24,'SHETPALLE');
insert into village(mandalId,villageCode,villageName) Values(18,37,'SURLI');
insert into village(mandalId,villageCode,villageName) Values(18,27,'TAKLI');
insert into village(mandalId,villageCode,villageName) Values(18,4,'TARODA');
insert into village(mandalId,villageCode,villageName) Values(18,6,'VENKATAPUR');
insert into village(mandalId,villageCode,villageName) Values(18,16,'VITHOLI');
insert into village(mandalId,villageCode,villageName) Values(18,22,'VONI');
insert into village(mandalId,villageCode,villageName) Values(18,9,'WADTHALA');


insert into village(mandalId,villageCode,villageName) Values(19,22,'ABDULLAPUR');
insert into village(mandalId,villageCode,villageName) Values(19,31,'BAMNI (K)');
insert into village(mandalId,villageCode,villageName) Values(19,14,'BHAGAPUR');
insert into village(mandalId,villageCode,villageName) Values(19,4,'BILOLI');
insert into village(mandalId,villageCode,villageName) Values(19,23,'BRAHMESWAR');
insert into village(mandalId,villageCode,villageName) Values(19,26,'DHARMARA');
insert into village(mandalId,villageCode,villageName) Values(19,12,'GADCHANDA');
insert into village(mandalId,villageCode,villageName) Values(19,29,'GODESRA');
insert into village(mandalId,villageCode,villageName) Values(19,2,'HADGAON');
insert into village(mandalId,villageCode,villageName) Values(19,5,'HAWARGA');
insert into village(mandalId,villageCode,villageName) Values(19,20,'JOHARPUR');
insert into village(mandalId,villageCode,villageName) Values(19,21,'KANKAPUR');
insert into village(mandalId,villageCode,villageName) Values(19,15,'KISTAPUR');
insert into village(mandalId,villageCode,villageName) Values(19,18,'LOHESRA');
insert into village(mandalId,villageCode,villageName) Values(19,16,'MALKAPUR');
insert into village(mandalId,villageCode,villageName) Values(19,6,'MANMAD');
insert into village(mandalId,villageCode,villageName) Values(19,28,'MOHALLA');
insert into village(mandalId,villageCode,villageName) Values(19,13,'NAGAR');
insert into village(mandalId,villageCode,villageName) Values(19,19,'NEW RAIPUR (K) R.C');
insert into village(mandalId,villageCode,villageName) Values(19,27,'PANCHGUDI');
insert into village(mandalId,villageCode,villageName) Values(19,30,'PIPRI');
insert into village(mandalId,villageCode,villageName) Values(19,7,'POTPALLE (B)');
insert into village(mandalId,villageCode,villageName) Values(19,1,'POTPALLE (M)');
insert into village(mandalId,villageCode,villageName) Values(19,17,'PUSPUR');
insert into village(mandalId,villageCode,villageName) Values(19,11,'RAIPUR (K)');
insert into village(mandalId,villageCode,villageName) Values(19,10,'RAJURA');
insert into village(mandalId,villageCode,villageName) Values(19,3,'SATHGAON');
insert into village(mandalId,villageCode,villageName) Values(19,9,'SAVARGAON');
insert into village(mandalId,villageCode,villageName) Values(19,24,'WASTAPUR');
insert into village(mandalId,villageCode,villageName) Values(19,25,'WATOLI');
insert into village(mandalId,villageCode,villageName) Values(19,8,'YEDDUR');



insert into village(mandalId,villageCode,villageName) Values(20,2,'ANJANI');
insert into village(mandalId,villageCode,villageName) Values(20,15,'ARLI (BUZURG)');
insert into village(mandalId,villageCode,villageName) Values(20,19,'BANASPALLE');
insert into village(mandalId,villageCode,villageName) Values(20,10,'CHERLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(20,17,'CHINTALKUNTA');
insert into village(mandalId,villageCode,villageName) Values(20,9,'DARYAPUR');
insert into village(mandalId,villageCode,villageName) Values(20,8,'DILAWARPUR');
insert into village(mandalId,villageCode,villageName) Values(20,18,'GUNDAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(20,4,'KADILI');
insert into village(mandalId,villageCode,villageName) Values(20,6,'KALVA');
insert into village(mandalId,villageCode,villageName) Values(20,22,'KANJAR');
insert into village(mandalId,villageCode,villageName) Values(20,20,'KOTHUR');
insert into village(mandalId,villageCode,villageName) Values(20,3,'KURLI');
insert into village(mandalId,villageCode,villageName) Values(20,23,'LINGAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(20,5,'MALEGAON');
insert into village(mandalId,villageCode,villageName) Values(20,27,'MALLAPUR');
insert into village(mandalId,villageCode,villageName) Values(20,25,'MAYAPUR');
insert into village(mandalId,villageCode,villageName) Values(20,29,'MUJGI');
insert into village(mandalId,villageCode,villageName) Values(20,13,'NARSAPUR');
insert into village(mandalId,villageCode,villageName) Values(20,12,'NASEERABAD');
insert into village(mandalId,villageCode,villageName) Values(20,7,'NEW LOLAM (R.C.)');
insert into village(mandalId,villageCode,villageName) Values(20,28,'POTPALLE (KHURD)');
insert into village(mandalId,villageCode,villageName) Values(20,11,'RAMPUR');
insert into village(mandalId,villageCode,villageName) Values(20,21,'RATNAPUR (K)');
insert into village(mandalId,villageCode,villageName) Values(20,16,'SAMANDERPALLE');
insert into village(mandalId,villageCode,villageName) Values(20,26,'SANGVI');
insert into village(mandalId,villageCode,villageName) Values(20,1,'SHAKAPUR');
insert into village(mandalId,villageCode,villageName) Values(20,30,'SIDDANKUNTA');
insert into village(mandalId,villageCode,villageName) Values(20,24,'SIRGAPUR');
insert into village(mandalId,villageCode,villageName) Values(20,14,'TEMBORNI');
insert into village(mandalId,villageCode,villageName) Values(20,31,'VELMEL');




insert into village(mandalId,villageCode,villageName) Values(21,24,'MUJGI');
insert into village(mandalId,villageCode,villageName) Values(21,28,'AKKAPUR');
insert into village(mandalId,villageCode,villageName) Values(21,11,'ANANTHPET');
insert into village(mandalId,villageCode,villageName) Values(21,15,'BHAGYANAGAR');
insert into village(mandalId,villageCode,villageName) Values(21,41,'BOPPARAM');
insert into village(mandalId,villageCode,villageName) Values(21,22,'CHITYAL');
insert into village(mandalId,villageCode,villageName) Values(21,4,'DYANGAPUR');
insert into village(mandalId,villageCode,villageName) Values(21,20,'GAJULPET');
insert into village(mandalId,villageCode,villageName) Values(21,35,'GANJAL');
insert into village(mandalId,villageCode,villageName) Values(21,34,'JAFRAPUR');
insert into village(mandalId,villageCode,villageName) Values(21,30,'KADTHAL');
insert into village(mandalId,villageCode,villageName) Values(21,9,'KAMLAPUR');
insert into village(mandalId,villageCode,villageName) Values(21,17,'KONDAPUR');
insert into village(mandalId,villageCode,villageName) Values(21,32,'KOUTLA (K)');
insert into village(mandalId,villageCode,villageName) Values(21,1,'LANGDAPUR');
insert into village(mandalId,villageCode,villageName) Values(21,40,'LOLAM');
insert into village(mandalId,villageCode,villageName) Values(21,37,'MADHAPUR');
insert into village(mandalId,villageCode,villageName) Values(21,8,'MAMBAPUR');
insert into village(mandalId,villageCode,villageName) Values(21,25,'MANJLAPUR');
insert into village(mandalId,villageCode,villageName) Values(21,6,'MEDPALLE');
insert into village(mandalId,villageCode,villageName) Values(21,29,'MUKTAPUR');
insert into village(mandalId,villageCode,villageName) Values(21,12,'NAGNAIPET');
insert into village(mandalId,villageCode,villageName) Values(21,7,'NEELAIPET');
insert into village(mandalId,villageCode,villageName) Values(21,14,'NEW POCHAMPAD');
insert into village(mandalId,villageCode,villageName) Values(21,19,'NIRMAL');
insert into village(mandalId,villageCode,villageName) Values(21,38,'PAKPATLA');
insert into village(mandalId,villageCode,villageName) Values(21,39,'POCHAMPAD');
insert into village(mandalId,villageCode,villageName) Values(21,16,'RATNAPUR KONDLI');
insert into village(mandalId,villageCode,villageName) Values(21,31,'SHAKARI');
insert into village(mandalId,villageCode,villageName) Values(21,33,'SIDDANKUNTA');
insert into village(mandalId,villageCode,villageName) Values(21,26,'SIDDAPUR');
insert into village(mandalId,villageCode,villageName) Values(21,36,'SOAN');
insert into village(mandalId,villageCode,villageName) Values(21,21,'TALWADA');
insert into village(mandalId,villageCode,villageName) Values(21,23,'THAMSA');
insert into village(mandalId,villageCode,villageName) Values(21,18,'THIMMAPUR');
insert into village(mandalId,villageCode,villageName) Values(21,2,'VENGVAPETA');
insert into village(mandalId,villageCode,villageName) Values(21,27,'VENKATAPUR');
insert into village(mandalId,villageCode,villageName) Values(21,3,'VISWANATHPET');
insert into village(mandalId,villageCode,villageName) Values(21,10,'YEDLAPUR');
insert into village(mandalId,villageCode,villageName) Values(21,13,'YELLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(21,5,'YELLAREDDIPET');



insert into village(mandalId,villageCode,villageName) Values(22,5,'BABAPUR');
insert into village(mandalId,villageCode,villageName) Values(22,4,'BOREGAON');
insert into village(mandalId,villageCode,villageName) Values(22,18,'CHAMANPALLE');
insert into village(mandalId,villageCode,villageName) Values(22,19,'CHINTALCHANDA');
insert into village(mandalId,villageCode,villageName) Values(22,13,'DHARMARAM');
insert into village(mandalId,villageCode,villageName) Values(22,2,'KANKAPUR');
insert into village(mandalId,villageCode,villageName) Values(22,12,'KUCHANPALLE');
insert into village(mandalId,villageCode,villageName) Values(22,7,'LAXMANCHANDA');
insert into village(mandalId,villageCode,villageName) Values(22,16,'MACHAPUR');
insert into village(mandalId,villageCode,villageName) Values(22,15,'MALLAPUR');
insert into village(mandalId,villageCode,villageName) Values(22,17,'MUNIPALLE');
insert into village(mandalId,villageCode,villageName) Values(22,3,'NARSAPUR (B)');
insert into village(mandalId,villageCode,villageName) Values(22,11,'NEW BOPPARAM');
insert into village(mandalId,villageCode,villageName) Values(22,9,'NEW VELMAL');
insert into village(mandalId,villageCode,villageName) Values(22,14,'PARPALLE');
insert into village(mandalId,villageCode,villageName) Values(22,8,'PEECHARA');
insert into village(mandalId,villageCode,villageName) Values(22,20,'POLPALLY (B)');
insert into village(mandalId,villageCode,villageName) Values(22,10,'SANGAMPET');
insert into village(mandalId,villageCode,villageName) Values(22,6,'THIRPALLE');
insert into village(mandalId,villageCode,villageName) Values(22,1,'WADDYAL');



insert into village(mandalId,villageCode,villageName) Values(23,28,'ADARSANAGAR(R.C)KOTHUR@');
insert into village(mandalId,villageCode,villageName) Values(23,26,'ANANTHPET');
insert into village(mandalId,villageCode,villageName) Values(23,10,'AREPALLE');
insert into village(mandalId,villageCode,villageName) Values(23,24,'BANDAL KHANAPUR');
insert into village(mandalId,villageCode,villageName) Values(23,7,'BURUGUPALLE');
insert into village(mandalId,villageCode,villageName) Values(23,23,'CHANDARAM');
insert into village(mandalId,villageCode,villageName) Values(23,17,'DANTHAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(23,32,'DEVATHAPUR');
insert into village(mandalId,villageCode,villageName) Values(23,15,'DIMMADURTHY');
insert into village(mandalId,villageCode,villageName) Values(23,6,'GAYADPALLE');
insert into village(mandalId,villageCode,villageName) Values(23,29,'KAMAL KOT');
insert into village(mandalId,villageCode,villageName) Values(23,14,'KAPPANPALLE');
insert into village(mandalId,villageCode,villageName) Values(23,8,'KISHANRAOPET');
insert into village(mandalId,villageCode,villageName) Values(23,22,'KORTIKAL');
insert into village(mandalId,villageCode,villageName) Values(23,18,'KOTHA SANGVI (R.C)');
insert into village(mandalId,villageCode,villageName) Values(23,27,'KOTHA TIMBARENI (R.C)');
insert into village(mandalId,villageCode,villageName) Values(23,21,'KOTHALINGAMPALLE (R.C)');
insert into village(mandalId,villageCode,villageName) Values(23,16,'LACHAMPUR');
insert into village(mandalId,villageCode,villageName) Values(23,11,'LINGAPUR');
insert into village(mandalId,villageCode,villageName) Values(23,13,'LONKAPAD');
insert into village(mandalId,villageCode,villageName) Values(23,19,'MAMDA');
insert into village(mandalId,villageCode,villageName) Values(23,20,'MONDEPALLE');
insert into village(mandalId,villageCode,villageName) Values(23,31,'NALDURTHI');
insert into village(mandalId,villageCode,villageName) Values(23,9,'PARIMANDAL');
insert into village(mandalId,villageCode,villageName) Values(23,30,'PONKAL');
insert into village(mandalId,villageCode,villageName) Values(23,25,'POTHARAM');
insert into village(mandalId,villageCode,villageName) Values(23,1,'PULIMADUGU');
insert into village(mandalId,villageCode,villageName) Values(23,12,'RAIDHARI');
insert into village(mandalId,villageCode,villageName) Values(23,4,'RAMPUR');
insert into village(mandalId,villageCode,villageName) Values(23,5,'RASIMATLA');
insert into village(mandalId,villageCode,villageName) Values(23,2,'TANDRA');
insert into village(mandalId,villageCode,villageName) Values(23,3,'VASTHAPUR');
insert into village(mandalId,villageCode,villageName) Values(23,33,'VENKATAPUR');



insert into village(mandalId,villageCode,villageName) Values(24,17,'ADVISARANGAPUR');
insert into village(mandalId,villageCode,villageName) Values(24,29,'BADANKURTHY');
insert into village(mandalId,villageCode,villageName) Values(24,27,'BAVAPUR (K)');
insert into village(mandalId,villageCode,villageName) Values(24,16,'BEERNANDI');
insert into village(mandalId,villageCode,villageName) Values(24,11,'BEVAPUR �');
insert into village(mandalId,villageCode,villageName) Values(24,10,'BURUGPALLE');
insert into village(mandalId,villageCode,villageName) Values(24,15,'CHAMANPALLE');
insert into village(mandalId,villageCode,villageName) Values(24,4,'DHOMDARI');
insert into village(mandalId,villageCode,villageName) Values(24,26,'DILWARPUR');
insert into village(mandalId,villageCode,villageName) Values(24,14,'ERVACHINTAL');
insert into village(mandalId,villageCode,villageName) Values(24,21,'GANGAIPET');
insert into village(mandalId,villageCode,villageName) Values(24,3,'GUMMANUYENGLAPUR');
insert into village(mandalId,villageCode,villageName) Values(24,20,'IQBALPUR');
insert into village(mandalId,villageCode,villageName) Values(24,2,'ITIKYAL');
insert into village(mandalId,villageCode,villageName) Values(24,28,'KHANAPUR');
insert into village(mandalId,villageCode,villageName) Values(24,6,'KOSAGUTTA');
insert into village(mandalId,villageCode,villageName) Values(24,25,'KOTHAPET');
insert into village(mandalId,villageCode,villageName) Values(24,13,'MANDAPALLE');
insert into village(mandalId,villageCode,villageName) Values(24,30,'MASKAPUR');
insert into village(mandalId,villageCode,villageName) Values(24,32,'MEDAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(24,18,'NAGPUR');
insert into village(mandalId,villageCode,villageName) Values(24,1,'PASPULA');
insert into village(mandalId,villageCode,villageName) Values(24,24,'PATHA YELLAPUR');
insert into village(mandalId,villageCode,villageName) Values(24,8,'PEMBI');
insert into village(mandalId,villageCode,villageName) Values(24,12,'RAJURA');
insert into village(mandalId,villageCode,villageName) Values(24,23,'SATHNAPALLE');
insert into village(mandalId,villageCode,villageName) Values(24,7,'SHETPALLE');
insert into village(mandalId,villageCode,villageName) Values(24,31,'SURJAPUR');
insert into village(mandalId,villageCode,villageName) Values(24,19,'TARLAPAD');
insert into village(mandalId,villageCode,villageName) Values(24,22,'THIMMAPUR');
insert into village(mandalId,villageCode,villageName) Values(24,5,'VASPALLE');
insert into village(mandalId,villageCode,villageName) Values(24,9,'VENKAMPOCHAMPAD');



insert into village(mandalId,villageCode,villageName) Values(25,2,'ALLAMPALLY');
insert into village(mandalId,villageCode,villageName) Values(25,20,'AMBARIPET');
insert into village(mandalId,villageCode,villageName) Values(25,34,'BELLAL');
insert into village(mandalId,villageCode,villageName) Values(25,35,'BHUTHKUR');
insert into village(mandalId,villageCode,villageName) Values(25,17,'BHUTTAPUR');
insert into village(mandalId,villageCode,villageName) Values(25,37,'CHENNUR');
insert into village(mandalId,villageCode,villageName) Values(25,33,'CHITTIAL');
insert into village(mandalId,villageCode,villageName) Values(25,19,'DASTURABAD');
insert into village(mandalId,villageCode,villageName) Values(25,23,'DHARMAIPET');
insert into village(mandalId,villageCode,villageName) Values(25,8,'DHARMAJIPET');
insert into village(mandalId,villageCode,villageName) Values(25,32,'DILDARNAGAR');
insert into village(mandalId,villageCode,villageName) Values(25,4,'GANDIGOPALPUR');
insert into village(mandalId,villageCode,villageName) Values(25,1,'GANGAPUR');
insert into village(mandalId,villageCode,villageName) Values(25,38,'GONDSERIAL');
insert into village(mandalId,villageCode,villageName) Values(25,5,'ISTAMPUR');
insert into village(mandalId,villageCode,villageName) Values(25,9,'KALLEDA');
insert into village(mandalId,villageCode,villageName) Values(25,22,'KANNAPUR');
insert into village(mandalId,villageCode,villageName) Values(25,21,'KONDKURU');
insert into village(mandalId,villageCode,villageName) Values(25,10,'LAXMIPUR');
insert into village(mandalId,villageCode,villageName) Values(25,27,'LAXMISAGAR');
insert into village(mandalId,villageCode,villageName) Values(25,30,'LINGAPUR');
insert into village(mandalId,villageCode,villageName) Values(25,26,'MADDIPADGA');
insert into village(mandalId,villageCode,villageName) Values(25,7,'MAISAMPET');
insert into village(mandalId,villageCode,villageName) Values(25,16,'MALLAPUR');
insert into village(mandalId,villageCode,villageName) Values(25,29,'MASAIPET');
insert into village(mandalId,villageCode,villageName) Values(25,36,'MUNNIAL');
insert into village(mandalId,villageCode,villageName) Values(25,25,'NACHAN YELLAPUR');
insert into village(mandalId,villageCode,villageName) Values(25,24,'NARSAPUR');
insert into village(mandalId,villageCode,villageName) Values(25,15,'NAWABPET');
insert into village(mandalId,villageCode,villageName) Values(25,14,'PANDWAPUR');
insert into village(mandalId,villageCode,villageName) Values(25,13,'PEDDUR');
insert into village(mandalId,villageCode,villageName) Values(25,3,'RAMPUR');
insert into village(mandalId,villageCode,villageName) Values(25,11,'REVAJIPET (OLD)');
insert into village(mandalId,villageCode,villageName) Values(25,18,'REVOJIPET (NEW)');
insert into village(mandalId,villageCode,villageName) Values(25,31,'SARANGAPUR');
insert into village(mandalId,villageCode,villageName) Values(25,12,'SINGAPUR');
insert into village(mandalId,villageCode,villageName) Values(25,6,'UDUMPUR');
insert into village(mandalId,villageCode,villageName) Values(25,28,'YELAGADAPA');



insert into village(mandalId,villageCode,villageName) Values(26,11,'ANDHOLI');
insert into village(mandalId,villageCode,villageName) Values(26,38,'BALAMPUR');
insert into village(mandalId,villageCode,villageName) Values(26,37,'BHUPET');
insert into village(mandalId,villageCode,villageName) Values(26,39,'BIRSAIPET');
insert into village(mandalId,villageCode,villageName) Values(26,5,'CHANDUR');
insert into village(mandalId,villageCode,villageName) Values(26,1,'CHINTAKARRA');
insert into village(mandalId,villageCode,villageName) Values(26,34,'DANTHANPALLE');
insert into village(mandalId,villageCode,villageName) Values(26,25,'DURGAPUR');
insert into village(mandalId,villageCode,villageName) Values(26,28,'GANGAMAPET');
insert into village(mandalId,villageCode,villageName) Values(26,30,'GANGAPUR');
insert into village(mandalId,villageCode,villageName) Values(26,35,'GHANPUR');
insert into village(mandalId,villageCode,villageName) Values(26,3,'GHATTI');
insert into village(mandalId,villageCode,villageName) Values(26,6,'HASNAPUR');
insert into village(mandalId,villageCode,villageName) Values(26,32,'HEERAPUR');
insert into village(mandalId,villageCode,villageName) Values(26,23,'HEERAPUR - J');
insert into village(mandalId,villageCode,villageName) Values(26,31,'KAMNIPET');
insert into village(mandalId,villageCode,villageName) Values(26,17,'KOPERGADH');
insert into village(mandalId,villageCode,villageName) Values(26,27,'LAKKARAM');
insert into village(mandalId,villageCode,villageName) Values(26,21,'LUXETTIPET');
insert into village(mandalId,villageCode,villageName) Values(26,22,'NAGAPUR');
insert into village(mandalId,villageCode,villageName) Values(26,2,'NARSAPUR (BUZURG)');
insert into village(mandalId,villageCode,villageName) Values(26,9,'NARSAPUR (KHURD)');
insert into village(mandalId,villageCode,villageName) Values(26,36,'NARSAPUR (NEW)');
insert into village(mandalId,villageCode,villageName) Values(26,12,'PULIMADGU');
insert into village(mandalId,villageCode,villageName) Values(26,24,'RAMLINGAMPET');
insert into village(mandalId,villageCode,villageName) Values(26,26,'RAMPUR (KHURD)');
insert into village(mandalId,villageCode,villageName) Values(26,10,'SAKHERA');
insert into village(mandalId,villageCode,villageName) Values(26,15,'SALEWADA (BUZURG)');
insert into village(mandalId,villageCode,villageName) Values(26,16,'SALEWADA (KHURD)');
insert into village(mandalId,villageCode,villageName) Values(26,14,'SHAMPUR');
insert into village(mandalId,villageCode,villageName) Values(26,20,'TANDRA');
insert into village(mandalId,villageCode,villageName) Values(26,33,'TEJAPUR - J');
insert into village(mandalId,villageCode,villageName) Values(26,8,'UMRI');
insert into village(mandalId,villageCode,villageName) Values(26,29,'UTNUR');
insert into village(mandalId,villageCode,villageName) Values(26,19,'WADGALPUR (BUZURG)');
insert into village(mandalId,villageCode,villageName) Values(26,18,'WADGALPUR (KHURD)');
insert into village(mandalId,villageCode,villageName) Values(26,4,'WADONI');
insert into village(mandalId,villageCode,villageName) Values(26,13,'YENDA');
insert into village(mandalId,villageCode,villageName) Values(26,7,'YENKA');



insert into village(mandalId,villageCode,villageName) Values(27,4,'ADDESAR');
insert into village(mandalId,villageCode,villageName) Values(27,1,'ASHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(27,5,'BHUSIMATTA');
insert into village(mandalId,villageCode,villageName) Values(27,7,'DABDLI');
insert into village(mandalId,villageCode,villageName) Values(27,12,'DUBBAGUDA');
insert into village(mandalId,villageCode,villageName) Values(27,3,'GUDAMAMDA');
insert into village(mandalId,villageCode,villageName) Values(27,13,'JAINOOR');
insert into village(mandalId,villageCode,villageName) Values(27,15,'JAMNI');
insert into village(mandalId,villageCode,villageName) Values(27,8,'LENDIGUDA');
insert into village(mandalId,villageCode,villageName) Values(27,11,'MARLAWAI');
insert into village(mandalId,villageCode,villageName) Values(27,2,'PATNAPUR');
insert into village(mandalId,villageCode,villageName) Values(27,16,'POLASA');
insert into village(mandalId,villageCode,villageName) Values(27,14,'POWERGUDA');
insert into village(mandalId,villageCode,villageName) Values(27,6,'RASIMATTA');
insert into village(mandalId,villageCode,villageName) Values(27,10,'SHIVANUR');
insert into village(mandalId,villageCode,villageName) Values(27,9,'USHEGAON');



insert into village(mandalId,villageCode,villageName) Values(28,18,'ANAKAPALLE');
insert into village(mandalId,villageCode,villageName) Values(28,5,'ANTHAPUR');
insert into village(mandalId,villageCode,villageName) Values(28,8,'AREKEPALLE');
insert into village(mandalId,villageCode,villageName) Values(28,9,'AREPALLE');
insert into village(mandalId,villageCode,villageName) Values(28,42,'BABEJHERI');
insert into village(mandalId,villageCode,villageName) Values(28,31,'BHEEMANGONDI');
insert into village(mandalId,villageCode,villageName) Values(28,15,'BHOLEPATHUR');
insert into village(mandalId,villageCode,villageName) Values(28,40,'CHALBORDI');
insert into village(mandalId,villageCode,villageName) Values(28,38,'CHINTAKARRA');
insert into village(mandalId,villageCode,villageName) Values(28,10,'DEVADPALLE');
insert into village(mandalId,villageCode,villageName) Values(28,19,'DEVAPUR');
insert into village(mandalId,villageCode,villageName) Values(28,32,'DHANORA');
insert into village(mandalId,villageCode,villageName) Values(28,7,'GOWRI');
insert into village(mandalId,villageCode,villageName) Values(28,30,'GOYAGAON');
insert into village(mandalId,villageCode,villageName) Values(28,36,'HATTI');
insert into village(mandalId,villageCode,villageName) Values(28,26,'INDAPUR');
insert into village(mandalId,villageCode,villageName) Values(28,6,'ISAPUR');
insert into village(mandalId,villageCode,villageName) Values(28,35,'JHARI');
insert into village(mandalId,villageCode,villageName) Values(28,46,'JODAGHAT');
insert into village(mandalId,villageCode,villageName) Values(28,12,'KALI BUZURG');
insert into village(mandalId,villageCode,villageName) Values(28,14,'KALI KHURD');
insert into village(mandalId,villageCode,villageName) Values(28,45,'KALLEGAON');
insert into village(mandalId,villageCode,villageName) Values(28,4,'KARANJIWADA');
insert into village(mandalId,villageCode,villageName) Values(28,20,'KERAMERI');
insert into village(mandalId,villageCode,villageName) Values(28,23,'KHAIRI');
insert into village(mandalId,villageCode,villageName) Values(28,2,'KOTHA');
insert into village(mandalId,villageCode,villageName) Values(28,28,'KOTHARI');
insert into village(mandalId,villageCode,villageName) Values(28,1,'LAKAMPUR');
insert into village(mandalId,villageCode,villageName) Values(28,37,'METTAPIPRI');
insert into village(mandalId,villageCode,villageName) Values(28,22,'MODI');
insert into village(mandalId,villageCode,villageName) Values(28,43,'MURIKILANKA');
insert into village(mandalId,villageCode,villageName) Values(28,44,'NAGAPUR');
insert into village(mandalId,villageCode,villageName) Values(28,33,'NARSAPUR');
insert into village(mandalId,villageCode,villageName) Values(28,27,'NISHANI');
insert into village(mandalId,villageCode,villageName) Values(28,3,'PARANDOL');
insert into village(mandalId,villageCode,villageName) Values(28,17,'PARASWADA');
insert into village(mandalId,villageCode,villageName) Values(28,34,'PARDA');
insert into village(mandalId,villageCode,villageName) Values(28,41,'PATNAPUR');
insert into village(mandalId,villageCode,villageName) Values(28,29,'PIPRI');
insert into village(mandalId,villageCode,villageName) Values(28,21,'SAKADA');
insert into village(mandalId,villageCode,villageName) Values(28,13,'SANGVI');
insert into village(mandalId,villageCode,villageName) Values(28,16,'SANKARAGUDA');
insert into village(mandalId,villageCode,villageName) Values(28,24,'SURDAPUR');
insert into village(mandalId,villageCode,villageName) Values(28,25,'SWARKHEDA');
insert into village(mandalId,villageCode,villageName) Values(28,39,'TUKYAN MOVAD');



insert into village(mandalId,villageCode,villageName) Values(29,7,'BABJIPET');
insert into village(mandalId,villageCode,villageName) Values(29,2,'BHURNUR');
insert into village(mandalId,villageCode,villageName) Values(29,16,'CHAPRI');
insert into village(mandalId,villageCode,villageName) Values(29,8,'CHORPALLE');
insert into village(mandalId,villageCode,villageName) Values(29,4,'DEVADPALLE');
insert into village(mandalId,villageCode,villageName) Values(29,17,'DHANORA');
insert into village(mandalId,villageCode,villageName) Values(29,20,'GHUMNUR BUZURG');
insert into village(mandalId,villageCode,villageName) Values(29,19,'GHUMNUR KHURD');
insert into village(mandalId,villageCode,villageName) Values(29,26,'JAMULDHARA');
insert into village(mandalId,villageCode,villageName) Values(29,21,'KANCHANPALLE');
insert into village(mandalId,villageCode,villageName) Values(29,13,'KOHENUR BUZURG');
insert into village(mandalId,villageCode,villageName) Values(29,14,'KOHENUR KHURD');
insert into village(mandalId,villageCode,villageName) Values(29,22,'KOTHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(29,24,'LINGAPUR');
insert into village(mandalId,villageCode,villageName) Values(29,18,'MAHAGAON');
insert into village(mandalId,villageCode,villageName) Values(29,23,'MAMIDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(29,10,'NETNUR');
insert into village(mandalId,villageCode,villageName) Values(29,11,'PAMULAWADA');
insert into village(mandalId,villageCode,villageName) Values(29,6,'PANGDI');
insert into village(mandalId,villageCode,villageName) Values(29,3,'PHULLARA');
insert into village(mandalId,villageCode,villageName) Values(29,1,'RAGHAPUR');
insert into village(mandalId,villageCode,villageName) Values(29,5,'SEETAGONDI');
insert into village(mandalId,villageCode,villageName) Values(29,15,'SHETTIHADAPNUR');
insert into village(mandalId,villageCode,villageName) Values(29,12,'SIRPUR');
insert into village(mandalId,villageCode,villageName) Values(29,9,'VANKAMADDI');
insert into village(mandalId,villageCode,villageName) Values(29,25,'YELLAPATAR');




insert into village(mandalId,villageCode,villageName) Values(30,18,'BADAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(30,20,'BOMMENA');
insert into village(mandalId,villageCode,villageName) Values(30,23,'CHINTAGUDA');
insert into village(mandalId,villageCode,villageName) Values(30,16,'DHARMARAM');
insert into village(mandalId,villageCode,villageName) Values(30,13,'DONGAPALLE');
insert into village(mandalId,villageCode,villageName) Values(30,1,'INDHANPALLE');
insert into village(mandalId,villageCode,villageName) Values(30,11,'JANNARAM');
insert into village(mandalId,villageCode,villageName) Values(30,12,'JUVIGUDA');
insert into village(mandalId,villageCode,villageName) Values(30,17,'KALMADAGU');
insert into village(mandalId,villageCode,villageName) Values(30,5,'KAMANPALLE');
insert into village(mandalId,villageCode,villageName) Values(30,3,'KAWAL');
insert into village(mandalId,villageCode,villageName) Values(30,4,'KISHTAPUR');
insert into village(mandalId,villageCode,villageName) Values(30,2,'KOTHAPET');
insert into village(mandalId,villageCode,villageName) Values(30,21,'MALYAL');
insert into village(mandalId,villageCode,villageName) Values(30,9,'MARRIGUDA');
insert into village(mandalId,villageCode,villageName) Values(30,6,'MURIMADUGU');
insert into village(mandalId,villageCode,villageName) Values(30,8,'NARSINGAPUR');
insert into village(mandalId,villageCode,villageName) Values(30,14,'PAIDPALLE');
insert into village(mandalId,villageCode,villageName) Values(30,22,'PAPAMMAGUDA');
insert into village(mandalId,villageCode,villageName) Values(30,15,'PONAKAL');
insert into village(mandalId,villageCode,villageName) Values(30,19,'PUTTIGUDA');
insert into village(mandalId,villageCode,villageName) Values(30,10,'RAINDLAGUDA');
insert into village(mandalId,villageCode,villageName) Values(30,26,'RAMPUR');
insert into village(mandalId,villageCode,villageName) Values(30,24,'SINGARAIPET');
insert into village(mandalId,villageCode,villageName) Values(30,25,'THIMMAPUR');
insert into village(mandalId,villageCode,villageName) Values(30,7,'VENKATAPURUM');



insert into village(mandalId,villageCode,villageName) Values(31,2,'ALLIPUR');
insert into village(mandalId,villageCode,villageName) Values(31,24,'ANDUGULPET');
insert into village(mandalId,villageCode,villageName) Values(31,13,'BIKKANGUDA');
insert into village(mandalId,villageCode,villageName) Values(31,20,'CHINTAPALLE');
insert into village(mandalId,villageCode,villageName) Values(31,10,'DANDEPALLE');
insert into village(mandalId,villageCode,villageName) Values(31,17,'DHARMARAOPET');
insert into village(mandalId,villageCode,villageName) Values(31,15,'DWARAKA');
insert into village(mandalId,villageCode,villageName) Values(31,30,'GUDAM');
insert into village(mandalId,villageCode,villageName) Values(31,1,'GURREVU');
insert into village(mandalId,villageCode,villageName) Values(31,28,'JAIDAPET');
insert into village(mandalId,villageCode,villageName) Values(31,31,'KAMEPALLE');
insert into village(mandalId,villageCode,villageName) Values(31,26,'KASIPET');
insert into village(mandalId,villageCode,villageName) Values(31,21,'KAVVICHELMA');
insert into village(mandalId,villageCode,villageName) Values(31,25,'KONDAPUR');
insert into village(mandalId,villageCode,villageName) Values(31,8,'KUNDELAPAHAD');
insert into village(mandalId,villageCode,villageName) Values(31,14,'LAXMIKANTAPUR');
insert into village(mandalId,villageCode,villageName) Values(31,12,'LINGAPUR');
insert into village(mandalId,villageCode,villageName) Values(31,5,'MAKULPET');
insert into village(mandalId,villageCode,villageName) Values(31,6,'MAMIDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(31,11,'MEDARIPET');
insert into village(mandalId,villageCode,villageName) Values(31,22,'MUTYAMPET');
insert into village(mandalId,villageCode,villageName) Values(31,3,'NAGASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(31,29,'NAMBAL');
insert into village(mandalId,villageCode,villageName) Values(31,18,'NARSAPUR');
insert into village(mandalId,villageCode,villageName) Values(31,16,'PEDDAPET');
insert into village(mandalId,villageCode,villageName) Values(31,23,'REBBENPALLE');
insert into village(mandalId,villageCode,villageName) Values(31,7,'ROLLPAHAD');
insert into village(mandalId,villageCode,villageName) Values(31,4,'TALLAPET');
insert into village(mandalId,villageCode,villageName) Values(31,9,'TANIMADUGU');
insert into village(mandalId,villageCode,villageName) Values(31,27,'VELGANOOR');
insert into village(mandalId,villageCode,villageName) Values(31,19,'VENKATAPUR');



insert into village(mandalId,villageCode,villageName) Values(32,4,'BALRAOPET');
insert into village(mandalId,villageCode,villageName) Values(32,3,'CHALLAMPET');
insert into village(mandalId,villageCode,villageName) Values(32,7,'CHANDRAM');
insert into village(mandalId,villageCode,villageName) Values(32,1,'DOWDEPALLE');
insert into village(mandalId,villageCode,villageName) Values(32,9,'ELLARAM');
insert into village(mandalId,villageCode,villageName) Values(32,19,'GULLAKOTA');
insert into village(mandalId,villageCode,villageName) Values(32,14,'ITKYAL');
insert into village(mandalId,villageCode,villageName) Values(32,5,'JENDAVENKATAPUR');
insert into village(mandalId,villageCode,villageName) Values(32,10,'KOTHUR');
insert into village(mandalId,villageCode,villageName) Values(32,17,'LAXMIPUR');
insert into village(mandalId,villageCode,villageName) Values(32,15,'LINGAPUR');
insert into village(mandalId,villageCode,villageName) Values(32,13,'LUXETTIPET');
insert into village(mandalId,villageCode,villageName) Values(32,20,'MITTAPALLY');
insert into village(mandalId,villageCode,villageName) Values(32,12,'MODELA');
insert into village(mandalId,villageCode,villageName) Values(32,18,'POTHEPALLE');
insert into village(mandalId,villageCode,villageName) Values(32,6,'RANGAPET');
insert into village(mandalId,villageCode,villageName) Values(32,2,'TALAMALLA');
insert into village(mandalId,villageCode,villageName) Values(32,16,'THIMMAPUR');
insert into village(mandalId,villageCode,villageName) Values(32,11,'UTUKUR');
insert into village(mandalId,villageCode,villageName) Values(32,8,'VENKATARAOPET');




insert into village(mandalId,villageCode,villageName) Values(33,18,'CHANDANAPUR');
insert into village(mandalId,villageCode,villageName) Values(33,8,'DONABANDA');
insert into village(mandalId,villageCode,villageName) Values(33,4,'GADHPUR');
insert into village(mandalId,villageCode,villageName) Values(33,23,'GARMILLA');
insert into village(mandalId,villageCode,villageName) Values(33,16,'GUDIPET');
insert into village(mandalId,villageCode,villageName) Values(33,14,'HAJIPUR');
insert into village(mandalId,villageCode,villageName) Values(33,6,'HUSSAINSAGAR');
insert into village(mandalId,villageCode,villageName) Values(33,11,'KARNAMAMIDI');
insert into village(mandalId,villageCode,villageName) Values(33,7,'KONDAPUR');
insert into village(mandalId,villageCode,villageName) Values(33,12,'KONDEPALLE');
insert into village(mandalId,villageCode,villageName) Values(33,20,'KOTHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(33,22,'MANCHERIYAL');
insert into village(mandalId,villageCode,villageName) Values(33,19,'MULKALLA');
insert into village(mandalId,villageCode,villageName) Values(33,3,'NAGARAM');
insert into village(mandalId,villageCode,villageName) Values(33,17,'NAMNUR');
insert into village(mandalId,villageCode,villageName) Values(33,15,'NARSINGAPUR');
insert into village(mandalId,villageCode,villageName) Values(33,24,'NASPUR');
insert into village(mandalId,villageCode,villageName) Values(33,10,'PADTHENPALLE');
insert into village(mandalId,villageCode,villageName) Values(33,1,'PEDDAMPET');
insert into village(mandalId,villageCode,villageName) Values(33,5,'POCHAMMAPAD');
insert into village(mandalId,villageCode,villageName) Values(33,13,'RAPALLE');
insert into village(mandalId,villageCode,villageName) Values(33,2,'RYALI');
insert into village(mandalId,villageCode,villageName) Values(33,25,'SEETARAMAPALLE');
insert into village(mandalId,villageCode,villageName) Values(33,28,'SINGAPUR');
insert into village(mandalId,villageCode,villageName) Values(33,9,'SUBBAPALLE');
insert into village(mandalId,villageCode,villageName) Values(33,26,'TEEGALPAHAD');
insert into village(mandalId,villageCode,villageName) Values(33,27,'THALLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(33,21,'VEMPALLE');



insert into village(mandalId,villageCode,villageName) Values(34,7,'AMERWADI');
insert into village(mandalId,villageCode,villageName) Values(34,1,'ANDGULAPET');
insert into village(mandalId,villageCode,villageName) Values(34,3,'CHIRRAKUNTA');
insert into village(mandalId,villageCode,villageName) Values(34,5,'KYETHENPALLE');
insert into village(mandalId,villageCode,villageName) Values(34,11,'LEMUR');
insert into village(mandalId,villageCode,villageName) Values(34,10,'MAMIDIGHAT');
insert into village(mandalId,villageCode,villageName) Values(34,2,'MANDAMARRI');
insert into village(mandalId,villageCode,villageName) Values(34,9,'PONNARAM');
insert into village(mandalId,villageCode,villageName) Values(34,4,'SARANGAPALLE');
insert into village(mandalId,villageCode,villageName) Values(34,6,'THIMMAPUR');
insert into village(mandalId,villageCode,villageName) Values(34,8,'VENKATAPUR');



insert into village(mandalId,villageCode,villageName) Values(35,17,'ANKISETTIPALLE');
insert into village(mandalId,villageCode,villageName) Values(35,6,'BANDAMEEDA KAMMAPALLE @');
insert into village(mandalId,villageCode,villageName) Values(35,12,'BASINIKONDA');
insert into village(mandalId,villageCode,villageName) Values(35,1,'CHINNATHIPPASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(35,9,'CHIPPILI');
insert into village(mandalId,villageCode,villageName) Values(35,11,'KAMMAPALLE');
insert into village(mandalId,villageCode,villageName) Values(35,2,'KASIRAOPETA');
insert into village(mandalId,villageCode,villageName) Values(35,7,'KOLLABYLU');
insert into village(mandalId,villageCode,villageName) Values(35,3,'KOTHAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(35,16,'MADANAPALLE (RURAL)');
insert into village(mandalId,villageCode,villageName) Values(35,19,'MALEPADU');
insert into village(mandalId,villageCode,villageName) Values(35,14,'MOLAKALADINNE');
insert into village(mandalId,villageCode,villageName) Values(35,13,'PAMAIAHGARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(35,10,'PAPPIREDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(35,20,'PENCHUPADU');
insert into village(mandalId,villageCode,villageName) Values(35,8,'PONNETIPALEM');
insert into village(mandalId,villageCode,villageName) Values(35,4,'POTHAPOLU');
insert into village(mandalId,villageCode,villageName) Values(35,21,'THENEEGALAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(35,15,'VALASAPALLE');
insert into village(mandalId,villageCode,villageName) Values(35,18,'VEMPALLE');
insert into village(mandalId,villageCode,villageName) Values(35,5,'VENKAPPAKOTA');



insert into village(mandalId,villageCode,villageName) Values(36,12,'AREGUDA');
insert into village(mandalId,villageCode,villageName) Values(36,38,'BHEEMAPUR');
insert into village(mandalId,villageCode,villageName) Values(36,10,'BOARDHAM');
insert into village(mandalId,villageCode,villageName) Values(36,34,'CHINTAPALLE');
insert into village(mandalId,villageCode,villageName) Values(36,13,'CHOPIDI');
insert into village(mandalId,villageCode,villageName) Values(36,3,'DANTANPALLE');
insert into village(mandalId,villageCode,villageName) Values(36,9,'DEVAIGUDA');
insert into village(mandalId,villageCode,villageName) Values(36,31,'DONDLA');
insert into village(mandalId,villageCode,villageName) Values(36,16,'DONGARGAON');
insert into village(mandalId,villageCode,villageName) Values(36,27,'DUGGAPUR');
insert into village(mandalId,villageCode,villageName) Values(36,30,'EDULPAD');
insert into village(mandalId,villageCode,villageName) Values(36,26,'GAMBHIRAOPET');
insert into village(mandalId,villageCode,villageName) Values(36,25,'GANGAPUR');
insert into village(mandalId,villageCode,villageName) Values(36,21,'GINNEDARI');
insert into village(mandalId,villageCode,villageName) Values(36,20,'GODELPALLE');
insert into village(mandalId,villageCode,villageName) Values(36,2,'GOENA');
insert into village(mandalId,villageCode,villageName) Values(36,15,'GOYAGAON');
insert into village(mandalId,villageCode,villageName) Values(36,39,'GUNDALA');
insert into village(mandalId,villageCode,villageName) Values(36,33,'IRKAPALLE');
insert into village(mandalId,villageCode,villageName) Values(36,32,'ISLAMPUR');
insert into village(mandalId,villageCode,villageName) Values(36,14,'JEWNI');
insert into village(mandalId,villageCode,villageName) Values(36,28,'KANNEPALLE');
insert into village(mandalId,villageCode,villageName) Values(36,35,'KORLANKA');
insert into village(mandalId,villageCode,villageName) Values(36,17,'KOYA TALANDI');
insert into village(mandalId,villageCode,villageName) Values(36,6,'LINGIGUDA');
insert into village(mandalId,villageCode,villageName) Values(36,1,'LODDIGUDA');
insert into village(mandalId,villageCode,villageName) Values(36,23,'MAINDAGUDIPET');
insert into village(mandalId,villageCode,villageName) Values(36,8,'MANDRUMEDA');
insert into village(mandalId,villageCode,villageName) Values(36,36,'MANGI');
insert into village(mandalId,villageCode,villageName) Values(36,40,'MANKAPUR');
insert into village(mandalId,villageCode,villageName) Values(36,4,'PANGIDI MADRA');
insert into village(mandalId,villageCode,villageName) Values(36,11,'PEDAKUNTA');
insert into village(mandalId,villageCode,villageName) Values(36,19,'RALLAKAMEPALLE');
insert into village(mandalId,villageCode,villageName) Values(36,37,'ROMPALLE');
insert into village(mandalId,villageCode,villageName) Values(36,7,'RUDRAPUR');
insert into village(mandalId,villageCode,villageName) Values(36,22,'SANGAPUR');
insert into village(mandalId,villageCode,villageName) Values(36,29,'SONAPUR');
insert into village(mandalId,villageCode,villageName) Values(36,18,'TALANDI');
insert into village(mandalId,villageCode,villageName) Values(36,24,'TIRYANI');
insert into village(mandalId,villageCode,villageName) Values(36,5,'ULLIPITADORLI');




insert into village(mandalId,villageCode,villageName) Values(37,2,'ADA');
insert into village(mandalId,villageCode,villageName) Values(37,48,'ADA -DASNAPUR');
insert into village(mandalId,villageCode,villageName) Values(37,64,'ADDAGHAT');
insert into village(mandalId,villageCode,villageName) Values(37,26,'ANKUSAPUR');
insert into village(mandalId,villageCode,villageName) Values(37,31,'APPEPALLE');
insert into village(mandalId,villageCode,villageName) Values(37,27,'ASIFABAD');
insert into village(mandalId,villageCode,villageName) Values(37,25,'BABAPUR');
insert into village(mandalId,villageCode,villageName) Values(37,41,'BALEGAON');
insert into village(mandalId,villageCode,villageName) Values(37,53,'BALHANPUR');
insert into village(mandalId,villageCode,villageName) Values(37,29,'BURUGUDA');
insert into village(mandalId,villageCode,villageName) Values(37,10,'CHERPALLE');
insert into village(mandalId,villageCode,villageName) Values(37,35,'CHILATIGUDA');
insert into village(mandalId,villageCode,villageName) Values(37,58,'CHIRRAKUNTA');
insert into village(mandalId,villageCode,villageName) Values(37,22,'DADPAPUR');
insert into village(mandalId,villageCode,villageName) Values(37,38,'DAGLESHWAR');
insert into village(mandalId,villageCode,villageName) Values(37,60,'DANABOINAPETA');
insert into village(mandalId,villageCode,villageName) Values(37,4,'DANAPUR');
insert into village(mandalId,villageCode,villageName) Values(37,42,'DEMMIDIGUDA');
insert into village(mandalId,villageCode,villageName) Values(37,57,'DEVADURGAM');
insert into village(mandalId,villageCode,villageName) Values(37,3,'EDAS GUNDI');
insert into village(mandalId,villageCode,villageName) Values(37,33,'EDULWADA');
insert into village(mandalId,villageCode,villageName) Values(37,28,'GODVELLI');
insert into village(mandalId,villageCode,villageName) Values(37,8,'GOVINDAPUR');
insert into village(mandalId,villageCode,villageName) Values(37,7,'GUDI GUDI');
insert into village(mandalId,villageCode,villageName) Values(37,9,'GUNDI');
insert into village(mandalId,villageCode,villageName) Values(37,5,'IPPALNAVEGAON');
insert into village(mandalId,villageCode,villageName) Values(37,40,'ITUKYAL');
insert into village(mandalId,villageCode,villageName) Values(37,18,'JANKAPUR');
insert into village(mandalId,villageCode,villageName) Values(37,16,'KESLAPUR');
insert into village(mandalId,villageCode,villageName) Values(37,23,'KHAPRI');
insert into village(mandalId,villageCode,villageName) Values(37,21,'KOMMUGUDA');
insert into village(mandalId,villageCode,villageName) Values(37,32,'KOMMUGUDA');
insert into village(mandalId,villageCode,villageName) Values(37,39,'KOSARA');
insert into village(mandalId,villageCode,villageName) Values(37,55,'KOWDIAN MOWAD');
insert into village(mandalId,villageCode,villageName) Values(37,45,'KUTODA');
insert into village(mandalId,villageCode,villageName) Values(37,46,'MALAN GONDI');
insert into village(mandalId,villageCode,villageName) Values(37,44,'MANKAPUR');
insert into village(mandalId,villageCode,villageName) Values(37,15,'MEKALWADA');
insert into village(mandalId,villageCode,villageName) Values(37,61,'MONDEPALLI');
insert into village(mandalId,villageCode,villageName) Values(37,30,'MOTUGUDA');
insert into village(mandalId,villageCode,villageName) Values(37,51,'MOWAD');
insert into village(mandalId,villageCode,villageName) Values(37,12,'NANDUPA');
insert into village(mandalId,villageCode,villageName) Values(37,24,'NIMBADA');
insert into village(mandalId,villageCode,villageName) Values(37,59,'PADI BONDA');
insert into village(mandalId,villageCode,villageName) Values(37,63,'PARASNAMBAL');
insert into village(mandalId,villageCode,villageName) Values(37,19,'PIPPALGAON');
insert into village(mandalId,villageCode,villageName) Values(37,13,'RAHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(37,17,'RAJAMPET');
insert into village(mandalId,villageCode,villageName) Values(37,14,'RAJURA');
insert into village(mandalId,villageCode,villageName) Values(37,62,'ROUTSANKEPALLE');
insert into village(mandalId,villageCode,villageName) Values(37,6,'SALEGUDA');
insert into village(mandalId,villageCode,villageName) Values(37,36,'SAMELA');
insert into village(mandalId,villageCode,villageName) Values(37,11,'SANKEPALLE');
insert into village(mandalId,villageCode,villageName) Values(37,47,'SHAKANGONDI');
insert into village(mandalId,villageCode,villageName) Values(37,34,'SINGARAOPET');
insert into village(mandalId,villageCode,villageName) Values(37,49,'SIRASGAON');
insert into village(mandalId,villageCode,villageName) Values(37,52,'SIRYAN MOWAD');
insert into village(mandalId,villageCode,villageName) Values(37,56,'SUDDA GHAT');
insert into village(mandalId,villageCode,villageName) Values(37,54,'TEMRIAN MOVAD');
insert into village(mandalId,villageCode,villageName) Values(37,37,'TUMPALLE');
insert into village(mandalId,villageCode,villageName) Values(37,50,'WADIGONDI');
insert into village(mandalId,villageCode,villageName) Values(37,1,'WADIGUDA');
insert into village(mandalId,villageCode,villageName) Values(37,43,'WAVUDHAM');
insert into village(mandalId,villageCode,villageName) Values(37,20,'YELLARAM');




insert into village(mandalId,villageCode,villageName) Values(38,17,'AKINI');
insert into village(mandalId,villageCode,villageName) Values(38,7,'ARLI');
insert into village(mandalId,villageCode,villageName) Values(38,8,'BAMBARA');
insert into village(mandalId,villageCode,villageName) Values(38,31,'BENDERA');
insert into village(mandalId,villageCode,villageName) Values(38,24,'BORANJIGUDA');
insert into village(mandalId,villageCode,villageName) Values(38,36,'BORDA');
insert into village(mandalId,villageCode,villageName) Values(38,5,'CHAVPANGUDA');
insert into village(mandalId,villageCode,villageName) Values(38,4,'CHICHPALLE');
insert into village(mandalId,villageCode,villageName) Values(38,34,'CHINCHOLI');
insert into village(mandalId,villageCode,villageName) Values(38,11,'DEVADPALLE');
insert into village(mandalId,villageCode,villageName) Values(38,1,'DHABA');
insert into village(mandalId,villageCode,villageName) Values(38,33,'GHAT JANGAON');
insert into village(mandalId,villageCode,villageName) Values(38,3,'GOAGAON');
insert into village(mandalId,villageCode,villageName) Values(38,6,'GUNJADA');
insert into village(mandalId,villageCode,villageName) Values(38,21,'INDHANI');
insert into village(mandalId,villageCode,villageName) Values(38,35,'JAITHPUR');
insert into village(mandalId,villageCode,villageName) Values(38,12,'JAMBULDHARI');
insert into village(mandalId,villageCode,villageName) Values(38,37,'KANNERAGAON');
insert into village(mandalId,villageCode,villageName) Values(38,25,'KHAMANA');
insert into village(mandalId,villageCode,villageName) Values(38,28,'KHEDEGAON');
insert into village(mandalId,villageCode,villageName) Values(38,22,'KHIRDI');
insert into village(mandalId,villageCode,villageName) Values(38,29,'KOMATIGUDA');
insert into village(mandalId,villageCode,villageName) Values(38,13,'LANJANVEERA');
insert into village(mandalId,villageCode,villageName) Values(38,10,'MAHAGAON');
insert into village(mandalId,villageCode,villageName) Values(38,18,'NARLAPUR');
insert into village(mandalId,villageCode,villageName) Values(38,20,'NAVEGAON');
insert into village(mandalId,villageCode,villageName) Values(38,16,'NEEMGAON');
insert into village(mandalId,villageCode,villageName) Values(38,19,'NUKEWADA');
insert into village(mandalId,villageCode,villageName) Values(38,30,'SAMELA');
insert into village(mandalId,villageCode,villageName) Values(38,23,'SARANDI');
insert into village(mandalId,villageCode,villageName) Values(38,27,'SARKEPALLE');
insert into village(mandalId,villageCode,villageName) Values(38,2,'SAWATHI');
insert into village(mandalId,villageCode,villageName) Values(38,9,'SONAPUR');
insert into village(mandalId,villageCode,villageName) Values(38,32,'TEJAPUR');
insert into village(mandalId,villageCode,villageName) Values(38,26,'VELGI');
insert into village(mandalId,villageCode,villageName) Values(38,15,'WANKDI KALAN');
insert into village(mandalId,villageCode,villageName) Values(38,14,'WANKDIKHURD');



insert into village(mandalId,villageCode,villageName) Values(39,24,'ANDAVELLI');
insert into village(mandalId,villageCode,villageName) Values(39,20,'ANKHODA');
insert into village(mandalId,villageCode,villageName) Values(39,11,'ANKUSAPUR');
insert into village(mandalId,villageCode,villageName) Values(39,15,'BAREGUDA');
insert into village(mandalId,villageCode,villageName) Values(39,25,'BHAT PALLE');
insert into village(mandalId,villageCode,villageName) Values(39,27,'BODEPALLE');
insert into village(mandalId,villageCode,villageName) Values(39,6,'BOREGAON');
insert into village(mandalId,villageCode,villageName) Values(39,28,'BOREGAON');
insert into village(mandalId,villageCode,villageName) Values(39,17,'CHINTHAGUDA');
insert into village(mandalId,villageCode,villageName) Values(39,10,'DUBBAGUDA');
insert into village(mandalId,villageCode,villageName) Values(39,18,'EASGAON');
insert into village(mandalId,villageCode,villageName) Values(39,22,'GANNARAM');
insert into village(mandalId,villageCode,villageName) Values(39,7,'GONDI');
insert into village(mandalId,villageCode,villageName) Values(39,38,'GUNTLAPET');
insert into village(mandalId,villageCode,villageName) Values(39,26,'JAGANNATHPUR');
insert into village(mandalId,villageCode,villageName) Values(39,30,'JAMBUGA');
insert into village(mandalId,villageCode,villageName) Values(39,36,'JANKAPUR');
insert into village(mandalId,villageCode,villageName) Values(39,35,'KADAMBA');
insert into village(mandalId,villageCode,villageName) Values(39,5,'KOSNI');
insert into village(mandalId,villageCode,villageName) Values(39,16,'KOTHAPET');
insert into village(mandalId,villageCode,villageName) Values(39,13,'LANJAGUDA');
insert into village(mandalId,villageCode,villageName) Values(39,12,'MAHAJANGUDA');
insert into village(mandalId,villageCode,villageName) Values(39,1,'MALNI');
insert into village(mandalId,villageCode,villageName) Values(39,21,'MANDVA');
insert into village(mandalId,villageCode,villageName) Values(39,3,'MAREPALLE');
insert into village(mandalId,villageCode,villageName) Values(39,2,'METINDHANI');
insert into village(mandalId,villageCode,villageName) Values(39,9,'METPALLE');
insert into village(mandalId,villageCode,villageName) Values(39,32,'MOSAM');
insert into village(mandalId,villageCode,villageName) Values(39,31,'NAGAMPET');
insert into village(mandalId,villageCode,villageName) Values(39,8,'NARAPUR');
insert into village(mandalId,villageCode,villageName) Values(39,19,'NAZRULNAGAR');
insert into village(mandalId,villageCode,villageName) Values(39,37,'POTHEPALLE');
insert into village(mandalId,villageCode,villageName) Values(39,33,'RASPALLI');
insert into village(mandalId,villageCode,villageName) Values(39,4,'REGULGUDA');
insert into village(mandalId,villageCode,villageName) Values(39,34,'SARSALA');
insert into village(mandalId,villageCode,villageName) Values(39,29,'SEETANAGAR');
insert into village(mandalId,villageCode,villageName) Values(39,23,'VALLAKONDA');
insert into village(mandalId,villageCode,villageName) Values(39,14,'VANJIRI');



insert into village(mandalId,villageCode,villageName) Values(40,12,'DHARMARAM');
insert into village(mandalId,villageCode,villageName) Values(40,1,'EDVALLI');
insert into village(mandalId,villageCode,villageName) Values(40,20,'FAKEERPALLE');
insert into village(mandalId,villageCode,villageName) Values(40,13,'GANGAPUR');
insert into village(mandalId,villageCode,villageName) Values(40,17,'GOLLET');
insert into village(mandalId,villageCode,villageName) Values(40,31,'GOVINDAPUR');
insert into village(mandalId,villageCode,villageName) Values(40,24,'GUDEPALLE');
insert into village(mandalId,villageCode,villageName) Values(40,23,'JAKKALPALLE');
insert into village(mandalId,villageCode,villageName) Values(40,2,'KHAIRGAON');
insert into village(mandalId,villageCode,villageName) Values(40,22,'KISTAPUR');
insert into village(mandalId,villageCode,villageName) Values(40,26,'KOMARVALLI');
insert into village(mandalId,villageCode,villageName) Values(40,6,'KONDAPALLE');
insert into village(mandalId,villageCode,villageName) Values(40,3,'MEDIGUDA');
insert into village(mandalId,villageCode,villageName) Values(40,19,'NAMBAL');
insert into village(mandalId,villageCode,villageName) Values(40,21,'NARAYANPUR');
insert into village(mandalId,villageCode,villageName) Values(40,4,'NAVEGAON');
insert into village(mandalId,villageCode,villageName) Values(40,15,'NERPALLE');
insert into village(mandalId,villageCode,villageName) Values(40,9,'PASSIGAON');
insert into village(mandalId,villageCode,villageName) Values(40,11,'POTHPALLE');
insert into village(mandalId,villageCode,villageName) Values(40,18,'PULTIKUNTA');
insert into village(mandalId,villageCode,villageName) Values(40,27,'RAJARAM');
insert into village(mandalId,villageCode,villageName) Values(40,7,'RAMPUR');
insert into village(mandalId,villageCode,villageName) Values(40,25,'RANGAPUR');
insert into village(mandalId,villageCode,villageName) Values(40,14,'REBBANA');
insert into village(mandalId,villageCode,villageName) Values(40,29,'ROLLAPAHAD');
insert into village(mandalId,villageCode,villageName) Values(40,8,'ROLLAPET');
insert into village(mandalId,villageCode,villageName) Values(40,30,'SEETHANAGAR');
insert into village(mandalId,villageCode,villageName) Values(40,16,'SONAPUR');
insert into village(mandalId,villageCode,villageName) Values(40,28,'TAKKALLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(40,10,'TUNGEDA');
insert into village(mandalId,villageCode,villageName) Values(40,5,'VENKULAM');




insert into village(mandalId,villageCode,villageName) Values(41,10,'ACHALAPUR');
insert into village(mandalId,villageCode,villageName) Values(41,19,'ANKEPALLE');
insert into village(mandalId,villageCode,villageName) Values(41,9,'ANNARAM');
insert into village(mandalId,villageCode,villageName) Values(41,7,'BALHANPUR');
insert into village(mandalId,villageCode,villageName) Values(41,22,'BOYAPALLE');
insert into village(mandalId,villageCode,villageName) Values(41,12,'CHANDRAPALLE');
insert into village(mandalId,villageCode,villageName) Values(41,20,'CHOUTPALLE');
insert into village(mandalId,villageCode,villageName) Values(41,16,'DWARAKAPUR');
insert into village(mandalId,villageCode,villageName) Values(41,11,'GAMPALPALLE');
insert into village(mandalId,villageCode,villageName) Values(41,13,'GOPALNAGAR');
insert into village(mandalId,villageCode,villageName) Values(41,14,'KASIPET');
insert into village(mandalId,villageCode,villageName) Values(41,24,'KATHERLA');
insert into village(mandalId,villageCode,villageName) Values(41,17,'KISTAMPET');
insert into village(mandalId,villageCode,villageName) Values(41,6,'KOTHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(41,1,'MADARAM');
insert into village(mandalId,villageCode,villageName) Values(41,21,'MADNAPUR');
insert into village(mandalId,villageCode,villageName) Values(41,2,'NARSAPUR');
insert into village(mandalId,villageCode,villageName) Values(41,4,'PEGADAPALLE');
insert into village(mandalId,villageCode,villageName) Values(41,23,'RAMPUR');
insert into village(mandalId,villageCode,villageName) Values(41,8,'RECHINI');
insert into village(mandalId,villageCode,villageName) Values(41,5,'REPALLEWADA');
insert into village(mandalId,villageCode,villageName) Values(41,15,'TANDUR');
insert into village(mandalId,villageCode,villageName) Values(41,18,'VENKAIPALLE');




insert into village(mandalId,villageCode,villageName) Values(42,10,'AKENIPALLE');
insert into village(mandalId,villageCode,villageName) Values(42,2,'ANKUSAM');
insert into village(mandalId,villageCode,villageName) Values(42,11,'BATWANPALLE');
insert into village(mandalId,villageCode,villageName) Values(42,5,'BUDHA KALAN');
insert into village(mandalId,villageCode,villageName) Values(42,4,'BUDHA KHURD');
insert into village(mandalId,villageCode,villageName) Values(42,3,'CHAKEPALLE');
insert into village(mandalId,villageCode,villageName) Values(42,6,'CHANDRAVELLI');
insert into village(mandalId,villageCode,villageName) Values(42,8,'DUGNEPALLE');
insert into village(mandalId,villageCode,villageName) Values(42,9,'GURJAL');
insert into village(mandalId,villageCode,villageName) Values(42,1,'KANNAL');
insert into village(mandalId,villageCode,villageName) Values(42,12,'PERKAPALLE');
insert into village(mandalId,villageCode,villageName) Values(42,7,'RANGAPET');
insert into village(mandalId,villageCode,villageName) Values(42,17,'BABALGAON');
insert into village(mandalId,villageCode,villageName) Values(42,24,'BADGAON');
insert into village(mandalId,villageCode,villageName) Values(42,18,'BHAINSA');



insert into village(mandalId,villageCode,villageName) Values(43,21,'AVADAM');
insert into village(mandalId,villageCode,villageName) Values(43,18,'BHADRAPUR');
insert into village(mandalId,villageCode,villageName) Values(43,23,'BHAGIRATHIPET');
insert into village(mandalId,villageCode,villageName) Values(43,20,'BODHAPUR');
insert into village(mandalId,villageCode,villageName) Values(43,27,'CHINAVENKATAPUR');
insert into village(mandalId,villageCode,villageName) Values(43,22,'CHITTAPUR');
insert into village(mandalId,villageCode,villageName) Values(43,7,'DAMMIREDDIPET');
insert into village(mandalId,villageCode,villageName) Values(43,11,'GHANPUR');
insert into village(mandalId,villageCode,villageName) Values(43,9,'GOLLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(43,24,'GUDIPET');
insert into village(mandalId,villageCode,villageName) Values(43,14,'GUNDLASOMARAM');
insert into village(mandalId,villageCode,villageName) Values(43,6,'JANGALPET');
insert into village(mandalId,villageCode,villageName) Values(43,25,'JHANDAVENKATAPUR');
insert into village(mandalId,villageCode,villageName) Values(43,13,'JOGAPUR');
insert into village(mandalId,villageCode,villageName) Values(43,16,'KHAMMAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(43,8,'KHARJI');
insert into village(mandalId,villageCode,villageName) Values(43,4,'KONAMPET');
insert into village(mandalId,villageCode,villageName) Values(43,29,'KOTTUR');
insert into village(mandalId,villageCode,villageName) Values(43,5,'KUSHENAPALLE');
insert into village(mandalId,villageCode,villageName) Values(43,17,'MAILARAM');
insert into village(mandalId,villageCode,villageName) Values(43,19,'MANKAPUR');
insert into village(mandalId,villageCode,villageName) Values(43,2,'MANNEGUDA');
insert into village(mandalId,villageCode,villageName) Values(43,15,'METPALLE');
insert into village(mandalId,villageCode,villageName) Values(43,10,'NANDULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(43,1,'NENNAL');
insert into village(mandalId,villageCode,villageName) Values(43,28,'POTTIYAL');
insert into village(mandalId,villageCode,villageName) Values(43,3,'PUPPULAWANIPET');
insert into village(mandalId,villageCode,villageName) Values(43,26,'SINGAPUR');
insert into village(mandalId,villageCode,villageName) Values(43,12,'SITANAGAR');



insert into village(mandalId,villageCode,villageName) Values(44,2,'AKKALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(44,32,'ANKANNAPETA');
insert into village(mandalId,villageCode,villageName) Values(44,7,'BABAPUR');
insert into village(mandalId,villageCode,villageName) Values(44,10,'BHEEMINI');
insert into village(mandalId,villageCode,villageName) Values(44,13,'BITTURPALLE');
insert into village(mandalId,villageCode,villageName) Values(44,27,'DAMPUR');
insert into village(mandalId,villageCode,villageName) Values(44,18,'GOLLAGHAT');
insert into village(mandalId,villageCode,villageName) Values(44,28,'JAJJARVELLY');
insert into village(mandalId,villageCode,villageName) Values(44,25,'JANKAPUR');
insert into village(mandalId,villageCode,villageName) Values(44,11,'KAMALAPUR');
insert into village(mandalId,villageCode,villageName) Values(44,34,'KANNEPALLE');
insert into village(mandalId,villageCode,villageName) Values(44,1,'KARJIBHEEMPUR');
insert into village(mandalId,villageCode,villageName) Values(44,29,'KOTHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(44,3,'LAXMIPUR');
insert into village(mandalId,villageCode,villageName) Values(44,16,'LINGAPUR');
insert into village(mandalId,villageCode,villageName) Values(44,14,'MALLIDI');
insert into village(mandalId,villageCode,villageName) Values(44,35,'METPALLE');
insert into village(mandalId,villageCode,villageName) Values(44,17,'MOTKUPALLE');
insert into village(mandalId,villageCode,villageName) Values(44,33,'MUTHAPUR');
insert into village(mandalId,villageCode,villageName) Values(44,5,'PEDDA GUDIPET');
insert into village(mandalId,villageCode,villageName) Values(44,9,'PEDDAPETA');
insert into village(mandalId,villageCode,villageName) Values(44,20,'POLAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(44,12,'POTHEPALLE');
insert into village(mandalId,villageCode,villageName) Values(44,8,'RAJARAM');
insert into village(mandalId,villageCode,villageName) Values(44,23,'RAMPUR');
insert into village(mandalId,villageCode,villageName) Values(44,22,'RAMRAOPETA');
insert into village(mandalId,villageCode,villageName) Values(44,30,'REBBENA');
insert into village(mandalId,villageCode,villageName) Values(44,21,'SHIKNAM');
insert into village(mandalId,villageCode,villageName) Values(44,6,'SURJAPUR');
insert into village(mandalId,villageCode,villageName) Values(44,24,'TEKULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(44,19,'VEEGAON');
insert into village(mandalId,villageCode,villageName) Values(44,31,'VEERAPUR');
insert into village(mandalId,villageCode,villageName) Values(44,15,'VENKATAPUR');
insert into village(mandalId,villageCode,villageName) Values(44,4,'WADAL');
insert into village(mandalId,villageCode,villageName) Values(44,26,'YELLARAM');



insert into village(mandalId,villageCode,villageName) Values(45,16,'ACHALLI');
insert into village(mandalId,villageCode,villageName) Values(45,26,'ADEPALLE');
insert into village(mandalId,villageCode,villageName) Values(45,18,'AREPALLE');
insert into village(mandalId,villageCode,villageName) Values(45,22,'BHUPALAPATNAM');
insert into village(mandalId,villageCode,villageName) Values(45,21,'BONKI');
insert into village(mandalId,villageCode,villageName) Values(45,14,'CHEELAPALLE');
insert into village(mandalId,villageCode,villageName) Values(45,19,'CHINTAKUNTA');
insert into village(mandalId,villageCode,villageName) Values(45,17,'CHUNCHUPALLE');
insert into village(mandalId,villageCode,villageName) Values(45,25,'DABBA');
insert into village(mandalId,villageCode,villageName) Values(45,24,'DHORPALLE');
insert into village(mandalId,villageCode,villageName) Values(45,1,'GARLAPET');
insert into village(mandalId,villageCode,villageName) Values(45,20,'HEERAPUR');
insert into village(mandalId,villageCode,villageName) Values(45,3,'HUDKILI');
insert into village(mandalId,villageCode,villageName) Values(45,5,'JAKKAPUR');
insert into village(mandalId,villageCode,villageName) Values(45,9,'LAXMIPUR');
insert into village(mandalId,villageCode,villageName) Values(45,6,'LOANVELLI');
insert into village(mandalId,villageCode,villageName) Values(45,4,'MAKIDI');
insert into village(mandalId,villageCode,villageName) Values(45,2,'MEDPALLE');
insert into village(mandalId,villageCode,villageName) Values(45,11,'NAVEGAON');
insert into village(mandalId,villageCode,villageName) Values(45,7,'PARIGAON');
insert into village(mandalId,villageCode,villageName) Values(45,23,'RAJARAM');
insert into village(mandalId,villageCode,villageName) Values(45,13,'RUDRARAM');
insert into village(mandalId,villageCode,villageName) Values(45,12,'SIRPUR');
insert into village(mandalId,villageCode,villageName) Values(45,8,'TONKINI');
insert into village(mandalId,villageCode,villageName) Values(45,15,'VEMPALLE');
insert into village(mandalId,villageCode,villageName) Values(45,10,'VENKATRAOPET');




insert into village(mandalId,villageCode,villageName) Values(46,21,'BABAPUR');
insert into village(mandalId,villageCode,villageName) Values(46,25,'BABASAGAR');
insert into village(mandalId,villageCode,villageName) Values(46,23,'BALAJI ANKODA');
insert into village(mandalId,villageCode,villageName) Values(46,30,'BANDEPALLE');
insert into village(mandalId,villageCode,villageName) Values(46,7,'BHALEPALLE');
insert into village(mandalId,villageCode,villageName) Values(46,28,'BUREPALLE');
insert into village(mandalId,villageCode,villageName) Values(46,14,'CHANDARAM');
insert into village(mandalId,villageCode,villageName) Values(46,26,'CHINTALA  MANEPALLE');
insert into village(mandalId,villageCode,villageName) Values(46,24,'CHIPURUDUBBA');
insert into village(mandalId,villageCode,villageName) Values(46,27,'GANGAPUR');
insert into village(mandalId,villageCode,villageName) Values(46,12,'GUDLABORI');
insert into village(mandalId,villageCode,villageName) Values(46,9,'GUNDAIPETA');
insert into village(mandalId,villageCode,villageName) Values(46,18,'GURUDPETA');
insert into village(mandalId,villageCode,villageName) Values(46,16,'KANKI');
insert into village(mandalId,villageCode,villageName) Values(46,17,'KANNEPALLE');
insert into village(mandalId,villageCode,villageName) Values(46,29,'KORISINI');
insert into village(mandalId,villageCode,villageName) Values(46,13,'KOUTHALA');
insert into village(mandalId,villageCode,villageName) Values(46,1,'KUMBARI');
insert into village(mandalId,villageCode,villageName) Values(46,6,'MOGADAGAD');
insert into village(mandalId,villageCode,villageName) Values(46,15,'MUTHAMPET');
insert into village(mandalId,villageCode,villageName) Values(46,20,'NAGEPALLE');
insert into village(mandalId,villageCode,villageName) Values(46,11,'RANVALLI');
insert into village(mandalId,villageCode,villageName) Values(46,22,'RAVINDRANAGAR');
insert into village(mandalId,villageCode,villageName) Values(46,3,'SANDGAON');
insert into village(mandalId,villageCode,villageName) Values(46,19,'TALODI');
insert into village(mandalId,villageCode,villageName) Values(46,5,'TATIPALLE');
insert into village(mandalId,villageCode,villageName) Values(46,10,'THUMBADIHATTI');
insert into village(mandalId,villageCode,villageName) Values(46,8,'VEERDANDI');
insert into village(mandalId,villageCode,villageName) Values(46,2,'VEERVALLI');
insert into village(mandalId,villageCode,villageName) Values(46,4,'WARDI');




insert into village(mandalId,villageCode,villageName) Values(47,37,'AGARGUDA');
insert into village(mandalId,villageCode,villageName) Values(47,10,'AMBHAGHAT');
insert into village(mandalId,villageCode,villageName) Values(47,20,'BEJJUR');
insert into village(mandalId,villageCode,villageName) Values(47,33,'BHATPALLE');
insert into village(mandalId,villageCode,villageName) Values(47,16,'BOMBAIGUDA');
insert into village(mandalId,villageCode,villageName) Values(47,26,'BURUGUDA');
insert into village(mandalId,villageCode,villageName) Values(47,22,'CHINNASIDDAPUR');
insert into village(mandalId,villageCode,villageName) Values(47,7,'CHITTAM');
insert into village(mandalId,villageCode,villageName) Values(47,6,'DIMDA');
insert into village(mandalId,villageCode,villageName) Values(47,32,'GABBAI');
insert into village(mandalId,villageCode,villageName) Values(47,36,'GANNARAM');
insert into village(mandalId,villageCode,villageName) Values(47,8,'GUDEM');
insert into village(mandalId,villageCode,villageName) Values(47,43,'GUNDEPALLE');
insert into village(mandalId,villageCode,villageName) Values(47,46,'JILLEDA');
insert into village(mandalId,villageCode,villageName) Values(47,44,'KAMMERGAON');
insert into village(mandalId,villageCode,villageName) Values(47,4,'KARJAVELLI');
insert into village(mandalId,villageCode,villageName) Values(47,11,'KATEPALLE');
insert into village(mandalId,villageCode,villageName) Values(47,5,'KETHINI');
insert into village(mandalId,villageCode,villageName) Values(47,15,'KONDAPALLE');
insert into village(mandalId,villageCode,villageName) Values(47,34,'KOYACHICHAL');
insert into village(mandalId,villageCode,villageName) Values(47,27,'KOYAPALLE');
insert into village(mandalId,villageCode,villageName) Values(47,23,'KUKUDA');
insert into village(mandalId,villageCode,villageName) Values(47,31,'KUSHNEPALLE');
insert into village(mandalId,villageCode,villageName) Values(47,18,'LODPALLE');
insert into village(mandalId,villageCode,villageName) Values(47,13,'MARTHADI');
insert into village(mandalId,villageCode,villageName) Values(47,25,'MOGAVELLY');
insert into village(mandalId,villageCode,villageName) Values(47,3,'MUNJAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(47,42,'MURALIGUDA');
insert into village(mandalId,villageCode,villageName) Values(47,28,'NAGEPALLE');
insert into village(mandalId,villageCode,villageName) Values(47,45,'NANDIGAON');
insert into village(mandalId,villageCode,villageName) Values(47,14,'OUTSARANGIPALLE');
insert into village(mandalId,villageCode,villageName) Values(47,38,'PAPANPET');
insert into village(mandalId,villageCode,villageName) Values(47,21,'PEDDASIDDAPUR');
insert into village(mandalId,villageCode,villageName) Values(47,35,'PENCHIKALPET');
insert into village(mandalId,villageCode,villageName) Values(47,12,'POTHEPALLE');
insert into village(mandalId,villageCode,villageName) Values(47,1,'REBBENA');
insert into village(mandalId,villageCode,villageName) Values(47,24,'RECHINI');
insert into village(mandalId,villageCode,villageName) Values(47,2,'RUDRAPUR');
insert into village(mandalId,villageCode,villageName) Values(47,9,'SHIVAPALLE');
insert into village(mandalId,villageCode,villageName) Values(47,29,'SOMINI');
insert into village(mandalId,villageCode,villageName) Values(47,30,'SUSHMEER');
insert into village(mandalId,villageCode,villageName) Values(47,40,'TALAI');
insert into village(mandalId,villageCode,villageName) Values(47,41,'TELAPALLE');
insert into village(mandalId,villageCode,villageName) Values(47,39,'TIKKAPALLE');
insert into village(mandalId,villageCode,villageName) Values(47,17,'YELKAPALLE');
insert into village(mandalId,villageCode,villageName) Values(47,19,'YELLUR');





insert into village(mandalId,villageCode,villageName) Values(48,9,'AINAM');
insert into village(mandalId,villageCode,villageName) Values(48,28,'AMARGONDA');
insert into village(mandalId,villageCode,villageName) Values(48,6,'BEEBRA');
insert into village(mandalId,villageCode,villageName) Values(48,29,'BHAMANAGAR');
insert into village(mandalId,villageCode,villageName) Values(48,23,'BHOGARAM');
insert into village(mandalId,villageCode,villageName) Values(48,3,'BORLA KUNTA');
insert into village(mandalId,villageCode,villageName) Values(48,25,'BRAHMANCHICHAL');
insert into village(mandalId,villageCode,villageName) Values(48,31,'CHANDRAPALLE');
insert into village(mandalId,villageCode,villageName) Values(48,8,'CHEDVAI');
insert into village(mandalId,villageCode,villageName) Values(48,13,'CHINNA THIMMAPUR');
insert into village(mandalId,villageCode,villageName) Values(48,11,'CHINNAGUDIPET');
insert into village(mandalId,villageCode,villageName) Values(48,27,'CHINNARASPALLE');
insert into village(mandalId,villageCode,villageName) Values(48,19,'DAHEGAON');
insert into village(mandalId,villageCode,villageName) Values(48,34,'DIGIDA');
insert into village(mandalId,villageCode,villageName) Values(48,38,'DUBBAGUDA');
insert into village(mandalId,villageCode,villageName) Values(48,32,'ETAPALLE');
insert into village(mandalId,villageCode,villageName) Values(48,30,'GIRVELLI');
insert into village(mandalId,villageCode,villageName) Values(48,1,'GORRIGUTTA');
insert into village(mandalId,villageCode,villageName) Values(48,15,'HATHNI');
insert into village(mandalId,villageCode,villageName) Values(48,2,'ITIAL');
insert into village(mandalId,villageCode,villageName) Values(48,18,'KALWADA');
insert into village(mandalId,villageCode,villageName) Values(48,20,'KAMMARPALLE');
insert into village(mandalId,villageCode,villageName) Values(48,4,'KESLAPUR');
insert into village(mandalId,villageCode,villageName) Values(48,5,'KOTHMIR');
insert into village(mandalId,villageCode,villageName) Values(48,24,'KUNCHAVELLI');
insert into village(mandalId,villageCode,villageName) Values(48,22,'LAGGAON');
insert into village(mandalId,villageCode,villageName) Values(48,33,'LOHA');
insert into village(mandalId,villageCode,villageName) Values(48,16,'MADAVELLI');
insert into village(mandalId,villageCode,villageName) Values(48,37,'MOTLAGUDA');
insert into village(mandalId,villageCode,villageName) Values(48,21,'PAMBAPUR');
insert into village(mandalId,villageCode,villageName) Values(48,14,'PEDDA THIMMAPUR');
insert into village(mandalId,villageCode,villageName) Values(48,7,'PESARKUNTA');
insert into village(mandalId,villageCode,villageName) Values(48,10,'POLAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(48,36,'RAMPUR');
insert into village(mandalId,villageCode,villageName) Values(48,39,'RAVALPALLE');
insert into village(mandalId,villageCode,villageName) Values(48,17,'SALIGAON');
insert into village(mandalId,villageCode,villageName) Values(48,35,'TEEPERGAON');
insert into village(mandalId,villageCode,villageName) Values(48,12,'THANGALLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(48,26,'VODDUGUDA');




insert into village(mandalId,villageCode,villageName) Values(49,13,'BADDAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(49,31,'BADDEVELLI');
insert into village(mandalId,villageCode,villageName) Values(49,1,'BAYYARAM');
insert into village(mandalId,villageCode,villageName) Values(49,11,'BOMMENA');
insert into village(mandalId,villageCode,villageName) Values(49,12,'CHAMANPALLE');
insert into village(mandalId,villageCode,villageName) Values(49,6,'CHINTAPUDI');
insert into village(mandalId,villageCode,villageName) Values(49,15,'DASNAPUR');
insert into village(mandalId,villageCode,villageName) Values(49,19,'GODAMPET');
insert into village(mandalId,villageCode,villageName) Values(49,26,'GORLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(49,14,'GUDEPALLE');
insert into village(mandalId,villageCode,villageName) Values(49,22,'JAJULPET');
insert into village(mandalId,villageCode,villageName) Values(49,2,'JILLEDA');
insert into village(mandalId,villageCode,villageName) Values(49,24,'KALLAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(49,25,'KALMALPET');
insert into village(mandalId,villageCode,villageName) Values(49,27,'KATEPALLE');
insert into village(mandalId,villageCode,villageName) Values(49,16,'KOTHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(49,30,'KYATHANPALLE');
insert into village(mandalId,villageCode,villageName) Values(49,5,'LINGALA');
insert into village(mandalId,villageCode,villageName) Values(49,7,'MADDUPALLE');
insert into village(mandalId,villageCode,villageName) Values(49,28,'MAMIDA');
insert into village(mandalId,villageCode,villageName) Values(49,23,'MUKKIDIGUDEM');
insert into village(mandalId,villageCode,villageName) Values(49,32,'MULKALPET');
insert into village(mandalId,villageCode,villageName) Values(49,9,'NAGARAM');
insert into village(mandalId,villageCode,villageName) Values(49,4,'NAGEPALLE');
insert into village(mandalId,villageCode,villageName) Values(49,29,'NEELWAI');
insert into village(mandalId,villageCode,villageName) Values(49,20,'ODDUGUDEM');
insert into village(mandalId,villageCode,villageName) Values(49,33,'RACHERLA');
insert into village(mandalId,villageCode,villageName) Values(49,18,'RAJARAM');
insert into village(mandalId,villageCode,villageName) Values(49,21,'SUMPUTUM');
insert into village(mandalId,villageCode,villageName) Values(49,10,'SURARAM');
insert into village(mandalId,villageCode,villageName) Values(49,3,'TAKKEPALLE');
insert into village(mandalId,villageCode,villageName) Values(49,8,'UPPARLAPAHAD');
insert into village(mandalId,villageCode,villageName) Values(49,17,'VEMANPALLE');



insert into village(mandalId,villageCode,villageName) Values(50,4,'ADKAPALLE');
insert into village(mandalId,villageCode,villageName) Values(50,17,'ALGAON');
insert into village(mandalId,villageCode,villageName) Values(50,30,'ANNARAM');
insert into village(mandalId,villageCode,villageName) Values(50,31,'ARJUNGUTTA');
insert into village(mandalId,villageCode,villageName) Values(50,18,'AYEPALLE');
insert into village(mandalId,villageCode,villageName) Values(50,13,'BOPPARAM');
insert into village(mandalId,villageCode,villageName) Values(50,28,'BORAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(50,3,'BRAHMANPALLE');
insert into village(mandalId,villageCode,villageName) Values(50,23,'CHINTAKUNTA');
insert into village(mandalId,villageCode,villageName) Values(50,36,'DEWALWADA');
insert into village(mandalId,villageCode,villageName) Values(50,21,'EDULA BANDAM');
insert into village(mandalId,villageCode,villageName) Values(50,24,'ERRAGATTA');
insert into village(mandalId,villageCode,villageName) Values(50,16,'JANGAON');
insert into village(mandalId,villageCode,villageName) Values(50,29,'KAWARKOTHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(50,34,'KOLLUR');
insert into village(mandalId,villageCode,villageName) Values(50,6,'KONDAMPET');
insert into village(mandalId,villageCode,villageName) Values(50,9,'KOTAPALLE');
insert into village(mandalId,villageCode,villageName) Values(50,22,'LINGANNAPET');
insert into village(mandalId,villageCode,villageName) Values(50,5,'MALLAMPET');
insert into village(mandalId,villageCode,villageName) Values(50,12,'NAGAMPET');
insert into village(mandalId,villageCode,villageName) Values(50,2,'NAKKALPALLE');
insert into village(mandalId,villageCode,villageName) Values(50,1,'PANGADISOMARAM');
insert into village(mandalId,villageCode,villageName) Values(50,26,'PARPALLE');
insert into village(mandalId,villageCode,villageName) Values(50,25,'PINNARAM');
insert into village(mandalId,villageCode,villageName) Values(50,19,'PULLAGAON');
insert into village(mandalId,villageCode,villageName) Values(50,32,'RAJARAM');
insert into village(mandalId,villageCode,villageName) Values(50,33,'RAMPUR');
insert into village(mandalId,villageCode,villageName) Values(50,37,'RAPANPALLE');
insert into village(mandalId,villageCode,villageName) Values(50,35,'RAWALPALLE');
insert into village(mandalId,villageCode,villageName) Values(50,10,'SARVAIPET');
insert into village(mandalId,villageCode,villageName) Values(50,7,'SHANKARPUR');
insert into village(mandalId,villageCode,villageName) Values(50,8,'SHETPALLE');
insert into village(mandalId,villageCode,villageName) Values(50,20,'SIRSA');
insert into village(mandalId,villageCode,villageName) Values(50,15,'SUPAK');
insert into village(mandalId,villageCode,villageName) Values(50,14,'VENCHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(50,11,'VESONVAI');





insert into village(mandalId,villageCode,villageName) Values(51,5,'ADIWARPET');
insert into village(mandalId,villageCode,villageName) Values(51,8,'AKKAPALLE');
insert into village(mandalId,villageCode,villageName) Values(51,19,'AMUDALPALLE');
insert into village(mandalId,villageCode,villageName) Values(51,22,'ANGARAJPALLE');
insert into village(mandalId,villageCode,villageName) Values(51,25,'ASNAD');
insert into village(mandalId,villageCode,villageName) Values(51,34,'BEERVELLI');
insert into village(mandalId,villageCode,villageName) Values(51,15,'BHAMRAOPET');
insert into village(mandalId,villageCode,villageName) Values(51,2,'BUDDARAM');
insert into village(mandalId,villageCode,villageName) Values(51,30,'CHAKEPALLE');
insert into village(mandalId,villageCode,villageName) Values(51,10,'CHENNUR');
insert into village(mandalId,villageCode,villageName) Values(51,9,'CHINTAPALLE');
insert into village(mandalId,villageCode,villageName) Values(51,20,'DUGNEPALLE');
insert into village(mandalId,villageCode,villageName) Values(51,24,'GANGARAM');
insert into village(mandalId,villageCode,villageName) Values(51,7,'GUDDIRAMPUR');
insert into village(mandalId,villageCode,villageName) Values(51,23,'KACHANPALLE');
insert into village(mandalId,villageCode,villageName) Values(51,4,'KANNEPALLE');
insert into village(mandalId,villageCode,villageName) Values(51,16,'KATHERSALA');
insert into village(mandalId,villageCode,villageName) Values(51,1,'KHAMBOJIPET');
insert into village(mandalId,villageCode,villageName) Values(51,12,'KISTAMPET');
insert into village(mandalId,villageCode,villageName) Values(51,26,'KOMMERA');
insert into village(mandalId,villageCode,villageName) Values(51,17,'KONAMPET');
insert into village(mandalId,villageCode,villageName) Values(51,13,'LINGAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(51,33,'NAGAPUR');
insert into village(mandalId,villageCode,villageName) Values(51,28,'NARASAKKAPET');
insert into village(mandalId,villageCode,villageName) Values(51,18,'NARAYANPUR');
insert into village(mandalId,villageCode,villageName) Values(51,29,'POKKUR');
insert into village(mandalId,villageCode,villageName) Values(51,31,'PONNARAM');
insert into village(mandalId,villageCode,villageName) Values(51,21,'RAIPET');
insert into village(mandalId,villageCode,villageName) Values(51,3,'SANKARAM');
insert into village(mandalId,villageCode,villageName) Values(51,6,'SHIVALINGAPUR');
insert into village(mandalId,villageCode,villageName) Values(51,32,'SOMANPALLE');
insert into village(mandalId,villageCode,villageName) Values(51,14,'SUDDAL');
insert into village(mandalId,villageCode,villageName) Values(51,27,'SUNDERSALA');
insert into village(mandalId,villageCode,villageName) Values(51,11,'YELLAKKAPET');




insert into village(mandalId,villageCode,villageName) Values(52,21,'ANKUSHAPUR');
insert into village(mandalId,villageCode,villageName) Values(52,8,'ARKAPALLE');
insert into village(mandalId,villageCode,villageName) Values(52,25,'BEJJAL');
insert into village(mandalId,villageCode,villageName) Values(52,9,'BHIMARAM');
insert into village(mandalId,villageCode,villageName) Values(52,6,'BURUGUPALLE');
insert into village(mandalId,villageCode,villageName) Values(52,4,'DAMPUR');
insert into village(mandalId,villageCode,villageName) Values(52,20,'GANGIPALLE');
insert into village(mandalId,villageCode,villageName) Values(52,33,'GOPALPUR');
insert into village(mandalId,villageCode,villageName) Values(52,16,'INDARAM');
insert into village(mandalId,villageCode,villageName) Values(52,11,'JAIPUR');
insert into village(mandalId,villageCode,villageName) Values(52,15,'KACHANPALLE');
insert into village(mandalId,villageCode,villageName) Values(52,1,'KANKUR');
insert into village(mandalId,villageCode,villageName) Values(52,30,'KISTAPUR');
insert into village(mandalId,villageCode,villageName) Values(52,31,'KOTHPALLE');
insert into village(mandalId,villageCode,villageName) Values(52,26,'KUNDARAM');
insert into village(mandalId,villageCode,villageName) Values(52,29,'MADDIKAL');
insert into village(mandalId,villageCode,villageName) Values(52,13,'MADDIKUNTA');
insert into village(mandalId,villageCode,villageName) Values(52,22,'MADDULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(52,2,'MITTAPALLE');
insert into village(mandalId,villageCode,villageName) Values(52,23,'NARASINGAPURAM');
insert into village(mandalId,villageCode,villageName) Values(52,12,'NARVA');
insert into village(mandalId,villageCode,villageName) Values(52,19,'PEGDAPALLE');
insert into village(mandalId,villageCode,villageName) Values(52,10,'POLAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(52,7,'POTHANPALLE');
insert into village(mandalId,villageCode,villageName) Values(52,34,'POWNUR');
insert into village(mandalId,villageCode,villageName) Values(52,14,'RAMARAOPET');
insert into village(mandalId,villageCode,villageName) Values(52,3,'REDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(52,28,'ROMMIPUR');
insert into village(mandalId,villageCode,villageName) Values(52,24,'SHETPALLE');
insert into village(mandalId,villageCode,villageName) Values(52,35,'SIVVARAM');
insert into village(mandalId,villageCode,villageName) Values(52,17,'TEKMATLA');
insert into village(mandalId,villageCode,villageName) Values(52,32,'VELAL');
insert into village(mandalId,villageCode,villageName) Values(52,5,'VELLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(52,27,'YAREPALLE');
insert into village(mandalId,villageCode,villageName) Values(52,18,'YELKANTI');





insert into village(mandalId,villageCode,villageName) Values(53,4,'D.HIREHAL');
insert into village(mandalId,villageCode,villageName) Values(53,15,'DODAGHATTA');
insert into village(mandalId,villageCode,villageName) Values(53,5,'H.HOSSAHALLI');
insert into village(mandalId,villageCode,villageName) Values(53,2,'H.SIDDAPURAM');
insert into village(mandalId,villageCode,villageName) Values(53,10,'HIREDAHAL');
insert into village(mandalId,villageCode,villageName) Values(53,17,'HULIKAL');
insert into village(mandalId,villageCode,villageName) Values(53,9,'JAJARAKAL');
insert into village(mandalId,villageCode,villageName) Values(53,16,'KADALUR');
insert into village(mandalId,villageCode,villageName) Values(53,14,'KUDULUR');
insert into village(mandalId,villageCode,villageName) Values(53,6,'LAKSHMIPURAM');
insert into village(mandalId,villageCode,villageName) Values(53,8,'LINGAMANAHALLI');
insert into village(mandalId,villageCode,villageName) Values(53,7,'MADENAHALLI');
insert into village(mandalId,villageCode,villageName) Values(53,1,'MALAPANAGUDI');
insert into village(mandalId,villageCode,villageName) Values(53,18,'MURADI');
insert into village(mandalId,villageCode,villageName) Values(53,12,'NAGALAPURAM');
insert into village(mandalId,villageCode,villageName) Values(53,3,'OBULAPURAM');
insert into village(mandalId,villageCode,villageName) Values(53,11,'PULAKURTHI');
insert into village(mandalId,villageCode,villageName) Values(53,13,'SOMALAPURAM');



insert into village(mandalId,villageCode,villageName) Values(54,9,'BANDUR');
insert into village(mandalId,villageCode,villageName) Values(54,3,'BOLLANAGUDDAM');
insert into village(mandalId,villageCode,villageName) Values(54,5,'BOMMANAHAL');
insert into village(mandalId,villageCode,villageName) Values(54,12,'D.HONNUR');
insert into village(mandalId,villageCode,villageName) Values(54,16,'ELANJI');
insert into village(mandalId,villageCode,villageName) Values(54,11,'GOVINDAWADA');
insert into village(mandalId,villageCode,villageName) Values(54,4,'HARESAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(54,10,'KALLUDEVANAHALLI');
insert into village(mandalId,villageCode,villageName) Values(54,15,'KOLAGANAHALLI');
insert into village(mandalId,villageCode,villageName) Values(54,1,'KURUVALLI');
insert into village(mandalId,villageCode,villageName) Values(54,6,'NEMAKALLU');
insert into village(mandalId,villageCode,villageName) Values(54,2,'SIDDARAMPURAM');
insert into village(mandalId,villageCode,villageName) Values(54,13,'SINGANAHALLI');
insert into village(mandalId,villageCode,villageName) Values(54,14,'SREEDHARAGHATTA');
insert into village(mandalId,villageCode,villageName) Values(54,8,'UDDEHAL');
insert into village(mandalId,villageCode,villageName) Values(54,7,'UNTHAKAL');




insert into village(mandalId,villageCode,villageName) Values(55,12,'CHEEKALAGURIKI');
insert into village(mandalId,villageCode,villageName) Values(55,1,'DONEKAL');
insert into village(mandalId,villageCode,villageName) Values(55,3,'GODEKAL');
insert into village(mandalId,villageCode,villageName) Values(55,4,'HANCHANAHAL');
insert into village(mandalId,villageCode,villageName) Values(55,9,'HAVALIGI');
insert into village(mandalId,villageCode,villageName) Values(55,2,'KADADARABENCHI');
insert into village(mandalId,villageCode,villageName) Values(55,10,'KARAKAMUKKALA');
insert into village(mandalId,villageCode,villageName) Values(55,11,'MALAPURAM');
insert into village(mandalId,villageCode,villageName) Values(55,6,'N.THIMMAPURAM');
insert into village(mandalId,villageCode,villageName) Values(55,13,'PALTHURU');
insert into village(mandalId,villageCode,villageName) Values(55,5,'POLIKI');
insert into village(mandalId,villageCode,villageName) Values(55,14,'UNDABANDA');
insert into village(mandalId,villageCode,villageName) Values(55,7,'VELPUMADUGU');
insert into village(mandalId,villageCode,villageName) Values(55,8,'VIDAPANAKAL');





insert into village(mandalId,villageCode,villageName) Values(56,8,'CHABALA');
insert into village(mandalId,villageCode,villageName) Values(56,9,'CHINNA HOTHUR');
insert into village(mandalId,villageCode,villageName) Values(56,7,'GADE HOTHUR');
insert into village(mandalId,villageCode,villageName) Values(56,5,'GANJIKUNTA');
insert into village(mandalId,villageCode,villageName) Values(56,2,'GULAPALEM');
insert into village(mandalId,villageCode,villageName) Values(56,16,'JARUTLA RAMPURAM');
insert into village(mandalId,villageCode,villageName) Values(56,11,'KADAMALAKUNTA');
insert into village(mandalId,villageCode,villageName) Values(56,3,'KAMALAPADI');
insert into village(mandalId,villageCode,villageName) Values(56,1,'KONAKONDLA');
insert into village(mandalId,villageCode,villageName) Values(56,14,'PANDIKUNTA');
insert into village(mandalId,villageCode,villageName) Values(56,10,'PEDDA CHINNA PYAPILI');
insert into village(mandalId,villageCode,villageName) Values(56,6,'POTTIPADU');
insert into village(mandalId,villageCode,villageName) Values(56,12,'RAGULAPADU');
insert into village(mandalId,villageCode,villageName) Values(56,13,'THATRAKAL');
insert into village(mandalId,villageCode,villageName) Values(56,4,'VAJRAKARUR');
insert into village(mandalId,villageCode,villageName) Values(56,15,'VENKATAMPALLE');



insert into village(mandalId,villageCode,villageName) Values(57,12,'AMEENPALLE');
insert into village(mandalId,villageCode,villageName) Values(57,19,'AYYAVARIPALLI');
insert into village(mandalId,villageCode,villageName) Values(57,17,'DANCHERLA');
insert into village(mandalId,villageCode,villageName) Values(57,15,'DONIMUKKALA');
insert into village(mandalId,villageCode,villageName) Values(57,5,'DOSALUDIKI');
insert into village(mandalId,villageCode,villageName) Values(57,6,'GUNDALA');
insert into village(mandalId,villageCode,villageName) Values(57,1,'GUNTAKAL');
insert into village(mandalId,villageCode,villageName) Values(57,3,'KASAPURAM');
insert into village(mandalId,villageCode,villageName) Values(57,7,'KOGANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(57,18,'NAGASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(57,13,'NAKKANADODDI');
insert into village(mandalId,villageCode,villageName) Values(57,16,'NELAGONDA');
insert into village(mandalId,villageCode,villageName) Values(57,9,'OBULAPURAM');
insert into village(mandalId,villageCode,villageName) Values(57,11,'PATHA KOTHACHERVU');
insert into village(mandalId,villageCode,villageName) Values(57,4,'SANGALA');
insert into village(mandalId,villageCode,villageName) Values(57,8,'SANKARABANDA');
insert into village(mandalId,villageCode,villageName) Values(57,2,'THIMMANACHERLA');
insert into village(mandalId,villageCode,villageName) Values(57,14,'THIMMAPURAM');
insert into village(mandalId,villageCode,villageName) Values(57,10,'YERRATHIMMARAJU CHERUVU');


insert into village(mandalId,villageCode,villageName) Values(58,17,'ABBEDODDI');
insert into village(mandalId,villageCode,villageName) Values(58,16,'ANIGANIDODDI');
insert into village(mandalId,villageCode,villageName) Values(58,7,'BASINEPALLE');
insert into village(mandalId,villageCode,villageName) Values(58,1,'BETHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(58,12,'BRAHMANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(58,6,'CHETNEPALLE');
insert into village(mandalId,villageCode,villageName) Values(58,5,'DHARMAPURAM');
insert into village(mandalId,villageCode,villageName) Values(58,21,'ENGILIBANDA');
insert into village(mandalId,villageCode,villageName) Values(58,15,'ERRAGUDI');
insert into village(mandalId,villageCode,villageName) Values(58,13,'ESWARAPALLE');
insert into village(mandalId,villageCode,villageName) Values(58,19,'GOOTY (RURAL)');
insert into village(mandalId,villageCode,villageName) Values(58,23,'JAKKALACHERUVU');
insert into village(mandalId,villageCode,villageName) Values(58,4,'KARADIKONDA');
insert into village(mandalId,villageCode,villageName) Values(58,8,'KOJJEPALLE');
insert into village(mandalId,villageCode,villageName) Values(58,20,'KOTHAPETA');
insert into village(mandalId,villageCode,villageName) Values(58,14,'MAMADUR');
insert into village(mandalId,villageCode,villageName) Values(58,10,'MARNEPALLE');
insert into village(mandalId,villageCode,villageName) Values(58,11,'PEDDODDI');
insert into village(mandalId,villageCode,villageName) Values(58,9,'RAJAPURAM');
insert into village(mandalId,villageCode,villageName) Values(58,22,'THONDAPADU');
insert into village(mandalId,villageCode,villageName) Values(58,18,'TURAKAPALLE');
insert into village(mandalId,villageCode,villageName) Values(58,3,'UBICHERLA');
insert into village(mandalId,villageCode,villageName) Values(58,2,'UTAKALLU');


insert into village(mandalId,villageCode,villageName) Values(59,4,'APPECHERLA');
insert into village(mandalId,villageCode,villageName) Values(59,8,'AVULAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(59,16,'CHINNAVADUGUR');
insert into village(mandalId,villageCode,villageName) Values(59,14,'CHINTALA CHERUVU');
insert into village(mandalId,villageCode,villageName) Values(59,23,'CHITRACHEDU');
insert into village(mandalId,villageCode,villageName) Values(59,21,'CHITTOOR');
insert into village(mandalId,villageCode,villageName) Values(59,20,'DIMMAGUDI');
insert into village(mandalId,villageCode,villageName) Values(59,1,'GOOTY ANANTAPURAM');
insert into village(mandalId,villageCode,villageName) Values(59,22,'KANDLAGUDURU');
insert into village(mandalId,villageCode,villageName) Values(59,13,'KONAPURAM');
insert into village(mandalId,villageCode,villageName) Values(59,18,'KONDURU');
insert into village(mandalId,villageCode,villageName) Values(59,5,'KRISTIPADU');
insert into village(mandalId,villageCode,villageName) Values(59,7,'MALLENIPALLE');
insert into village(mandalId,villageCode,villageName) Values(59,11,'MEDIMAKULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(59,9,'MIDUTHURU');
insert into village(mandalId,villageCode,villageName) Values(59,24,'MOLAKATHALLA');
insert into village(mandalId,villageCode,villageName) Values(59,6,'MUPPALAGUTHI');
insert into village(mandalId,villageCode,villageName) Values(59,19,'NAGALAPURAM');
insert into village(mandalId,villageCode,villageName) Values(59,10,'PEDDAVADUGUR');
insert into village(mandalId,villageCode,villageName) Values(59,17,'PENAKALAPADU');
insert into village(mandalId,villageCode,villageName) Values(59,12,'RAVULUDIKI');
insert into village(mandalId,villageCode,villageName) Values(59,15,'TELIKI');
insert into village(mandalId,villageCode,villageName) Values(59,3,'VENKATAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(59,2,'VIRUPAPURAM');


insert into village(mandalId,villageCode,villageName) Values(60,2,'CHANDANA');
insert into village(mandalId,villageCode,villageName) Values(60,8,'GUDIPADU');
insert into village(mandalId,villageCode,villageName) Values(60,6,'KAMALAPADU');
insert into village(mandalId,villageCode,villageName) Values(60,5,'KONUPPALAPADU');
insert into village(mandalId,villageCode,villageName) Values(60,7,'KUNDANAKOTA');
insert into village(mandalId,villageCode,villageName) Values(60,13,'NAGARUR');
insert into village(mandalId,villageCode,villageName) Values(60,9,'NITTURU');
insert into village(mandalId,villageCode,villageName) Values(60,4,'OBULAPURAM');
insert into village(mandalId,villageCode,villageName) Values(60,14,'PUPPALA');
insert into village(mandalId,villageCode,villageName) Values(60,1,'RAYALACHERUVU');
insert into village(mandalId,villageCode,villageName) Values(60,10,'THIMMAPURAM');
insert into village(mandalId,villageCode,villageName) Values(60,12,'TUTRALLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(60,11,'VEMULAPADU');
insert into village(mandalId,villageCode,villageName) Values(60,3,'YADIKI');


insert into village(mandalId,villageCode,villageName) Values(61,7,'ALUR');
insert into village(mandalId,villageCode,villageName) Values(61,3,'BHOGASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(61,25,'BODAIPALLE');
insert into village(mandalId,villageCode,villageName) Values(61,27,'BONDALADINNE');
insert into village(mandalId,villageCode,villageName) Values(61,1,'BRAHMANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(61,21,'CHALLAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(61,17,'CHINNAPOLAMADA');
insert into village(mandalId,villageCode,villageName) Values(61,10,'CHUKKALUR');
insert into village(mandalId,villageCode,villageName) Values(61,19,'DIGUVAPALLE');
insert into village(mandalId,villageCode,villageName) Values(61,12,'GANGADEVIPALLE');
insert into village(mandalId,villageCode,villageName) Values(61,23,'HUSSAIN PURAM');
insert into village(mandalId,villageCode,villageName) Values(61,13,'IGUDUR');
insert into village(mandalId,villageCode,villageName) Values(61,22,'JAMBULAPADU');
insert into village(mandalId,villageCode,villageName) Values(61,9,'KAVERISAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(61,15,'KOMALI');
insert into village(mandalId,villageCode,villageName) Values(61,18,'NANDALAPADU');
insert into village(mandalId,villageCode,villageName) Values(61,16,'PEDDAPOLAMADA');
insert into village(mandalId,villageCode,villageName) Values(61,11,'PULIPRODDATUR');
insert into village(mandalId,villageCode,villageName) Values(61,8,'SAJJALADINNE');
insert into village(mandalId,villageCode,villageName) Values(61,14,'SEETHARAMPURAM');
insert into village(mandalId,villageCode,villageName) Values(61,20,'TADPATRI �');
insert into village(mandalId,villageCode,villageName) Values(61,4,'TALARICHERUVU');
insert into village(mandalId,villageCode,villageName) Values(61,5,'URUCHINTHALA');
insert into village(mandalId,villageCode,villageName) Values(61,26,'VANGANUR');
insert into village(mandalId,villageCode,villageName) Values(61,24,'VEERAPURAM');
insert into village(mandalId,villageCode,villageName) Values(61,6,'VELAMAKUR');
insert into village(mandalId,villageCode,villageName) Values(61,2,'VENKATAMPALLE');



insert into village(mandalId,villageCode,villageName) Values(62,17,'AMALLADINNE');
insert into village(mandalId,villageCode,villageName) Values(62,6,'ATHIRALLADINNE');
insert into village(mandalId,villageCode,villageName) Values(62,14,'CHAGALLU');
insert into village(mandalId,villageCode,villageName) Values(62,2,'CHINNAYAKKALURU');
insert into village(mandalId,villageCode,villageName) Values(62,1,'DEVANUPPALAPADU');
insert into village(mandalId,villageCode,villageName) Values(62,12,'DHARMAPURAM');
insert into village(mandalId,villageCode,villageName) Values(62,4,'GARLADINNE');
insert into village(mandalId,villageCode,villageName) Values(62,10,'JODIDHARMAPURAM');
insert into village(mandalId,villageCode,villageName) Values(62,11,'JUTUR');
insert into village(mandalId,villageCode,villageName) Values(62,8,'KUMMETHA');
insert into village(mandalId,villageCode,villageName) Values(62,16,'MUCHUKOTA');
insert into village(mandalId,villageCode,villageName) Values(62,9,'NARASAPURAM');
insert into village(mandalId,villageCode,villageName) Values(62,5,'PASALUR');
insert into village(mandalId,villageCode,villageName) Values(62,13,'PEDDAPAPPUR');
insert into village(mandalId,villageCode,villageName) Values(62,3,'PEDDAYAKKALURU');
insert into village(mandalId,villageCode,villageName) Values(62,7,'PENDEKALLU');
insert into village(mandalId,villageCode,villageName) Values(62,15,'TABJULA');


insert into village(mandalId,villageCode,villageName) Values(63,21,'AKULEDU');
insert into village(mandalId,villageCode,villageName) Values(63,4,'ANANDARAOPETA');
insert into village(mandalId,villageCode,villageName) Values(63,11,'BUDDEPALLE');
insert into village(mandalId,villageCode,villageName) Values(63,18,'CHAKRAYAPETA');
insert into village(mandalId,villageCode,villageName) Values(63,9,'CHEELEPALLI');
insert into village(mandalId,villageCode,villageName) Values(63,13,'CHENNAVARAM');
insert into village(mandalId,villageCode,villageName) Values(63,8,'JOOLAKALVA');
insert into village(mandalId,villageCode,villageName) Values(63,1,'KALLUMADI');
insert into village(mandalId,villageCode,villageName) Values(63,20,'KARNATAKAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(63,7,'KORIVIPALLE');
insert into village(mandalId,villageCode,villageName) Values(63,22,'LOLUR');
insert into village(mandalId,villageCode,villageName) Values(63,15,'MATLAGONDI');
insert into village(mandalId,villageCode,villageName) Values(63,14,'NARSAPURAM');
insert into village(mandalId,villageCode,villageName) Values(63,3,'NIDHANAWADA');
insert into village(mandalId,villageCode,villageName) Values(63,12,'PEDDAJALALPURAM');
insert into village(mandalId,villageCode,villageName) Values(63,17,'PERAVALI');
insert into village(mandalId,villageCode,villageName) Values(63,5,'RACHEPALLE');
insert into village(mandalId,villageCode,villageName) Values(63,10,'SALAKAMCHERUVU');
insert into village(mandalId,villageCode,villageName) Values(63,19,'SINGANAMALA');
insert into village(mandalId,villageCode,villageName) Values(63,16,'SODANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(63,2,'TARIMELA');
insert into village(mandalId,villageCode,villageName) Values(63,6,'ULLIKALLU');


insert into village(mandalId,villageCode,villageName) Values(64,4,'AKKAJAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(64,1,'ANUMPALLE');
insert into village(mandalId,villageCode,villageName) Values(64,16,'APPAJIPETA');
insert into village(mandalId,villageCode,villageName) Values(64,3,'DEVARAPALLE');
insert into village(mandalId,villageCode,villageName) Values(64,12,'EDDULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(64,5,'EDURUR');
insert into village(mandalId,villageCode,villageName) Values(64,7,'GAJARAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(64,9,'GULIMIKONDLA');
insert into village(mandalId,villageCode,villageName) Values(64,10,'KALAPURAM');
insert into village(mandalId,villageCode,villageName) Values(64,17,'KANDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(64,15,'KATRIMALA');
insert into village(mandalId,villageCode,villageName) Values(64,2,'KHADARPETA');
insert into village(mandalId,villageCode,villageName) Values(64,21,'NEELUR');
insert into village(mandalId,villageCode,villageName) Values(64,11,'NEMALLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(64,14,'PALYAM');
insert into village(mandalId,villageCode,villageName) Values(64,20,'PAMIDI');
insert into village(mandalId,villageCode,villageName) Values(64,8,'POGARUR');
insert into village(mandalId,villageCode,villageName) Values(64,13,'RAMAGIRI');
insert into village(mandalId,villageCode,villageName) Values(64,6,'RAMARAJUPALLE');
insert into village(mandalId,villageCode,villageName) Values(64,18,'SORAKAYALAPETA');
insert into village(mandalId,villageCode,villageName) Values(64,22,'THAMBALLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(64,19,'VANKARAJUKALVA');



insert into village(mandalId,villageCode,villageName) Values(65,9,'ANKAMPETA');
insert into village(mandalId,villageCode,villageName) Values(65,15,'BUDEDU');
insert into village(mandalId,villageCode,villageName) Values(65,16,'GARLADINNE');
insert into village(mandalId,villageCode,villageName) Values(65,7,'ILLURU');
insert into village(mandalId,villageCode,villageName) Values(65,17,'JAMBULADINNE');
insert into village(mandalId,villageCode,villageName) Values(65,6,'KALLURU');
insert into village(mandalId,villageCode,villageName) Values(65,5,'KALLURU AGRAHARAM');
insert into village(mandalId,villageCode,villageName) Values(65,8,'KANAMPALLI');
insert into village(mandalId,villageCode,villageName) Values(65,3,'KESAVAPURAM');
insert into village(mandalId,villageCode,villageName) Values(65,2,'KOPPALAKONDA');
insert into village(mandalId,villageCode,villageName) Values(65,13,'KOTANKA');
insert into village(mandalId,villageCode,villageName) Values(65,11,'KRISHNAPURAM');
insert into village(mandalId,villageCode,villageName) Values(65,14,'MARTHADU');
insert into village(mandalId,villageCode,villageName) Values(65,4,'MUNTIMADUGU');
insert into village(mandalId,villageCode,villageName) Values(65,18,'OBULAPURAM');
insert into village(mandalId,villageCode,villageName) Values(65,1,'PENAKACHERLA');
insert into village(mandalId,villageCode,villageName) Values(65,10,'SIRIVARAM');
insert into village(mandalId,villageCode,villageName) Values(65,12,'YERRAGUNTLA');


insert into village(mandalId,villageCode,villageName) Values(66,8,'BRAHMANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(66,10,'CHOLASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(66,7,'GOTUKURU');
insert into village(mandalId,villageCode,villageName) Values(66,5,'IPPERU');
insert into village(mandalId,villageCode,villageName) Values(66,11,'JAYAPURAM');
insert into village(mandalId,villageCode,villageName) Values(66,3,'KALAGALLA');
insert into village(mandalId,villageCode,villageName) Values(66,6,'KAMMURU');
insert into village(mandalId,villageCode,villageName) Values(66,4,'KORRAKODU');
insert into village(mandalId,villageCode,villageName) Values(66,9,'KUDAIR');
insert into village(mandalId,villageCode,villageName) Values(66,2,'MARUTLA');
insert into village(mandalId,villageCode,villageName) Values(66,12,'MAVINAMARDHANAHALLI');
insert into village(mandalId,villageCode,villageName) Values(66,13,'THIMMAPURAM');
insert into village(mandalId,villageCode,villageName) Values(66,1,'UDIRIPIKONDA');


insert into village(mandalId,villageCode,villageName) Values(67,18,'AMIDALA');
insert into village(mandalId,villageCode,villageName) Values(67,1,'BUDAGAVI');
insert into village(mandalId,villageCode,villageName) Values(67,11,'CHINNAMUSTURU');
insert into village(mandalId,villageCode,villageName) Values(67,9,'INDRAVATHI');
insert into village(mandalId,villageCode,villageName) Values(67,14,'KONAPURAM');
insert into village(mandalId,villageCode,villageName) Values(67,12,'LATHAVARAM');
insert into village(mandalId,villageCode,villageName) Values(67,17,'MOPIDI');
insert into village(mandalId,villageCode,villageName) Values(67,20,'MYLARAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(67,6,'NERIMETLA');
insert into village(mandalId,villageCode,villageName) Values(67,8,'NIMBAGAL');
insert into village(mandalId,villageCode,villageName) Values(67,10,'PEDDA MUSTURU');
insert into village(mandalId,villageCode,villageName) Values(67,21,'PEDDAKOWKUNTLA');
insert into village(mandalId,villageCode,villageName) Values(67,16,'PENNAHOBILAM');
insert into village(mandalId,villageCode,villageName) Values(67,15,'RACHARLA');
insert into village(mandalId,villageCode,villageName) Values(67,19,'RAKETLA');
insert into village(mandalId,villageCode,villageName) Values(67,5,'RAYAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(67,4,'RENIMAKULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(67,13,'SHAIKSANIPALLE');
insert into village(mandalId,villageCode,villageName) Values(67,3,'URAVAKONDA');
insert into village(mandalId,villageCode,villageName) Values(67,2,'VELIGONDA');
insert into village(mandalId,villageCode,villageName) Values(67,7,'VYASAPURAM');
insert into village(mandalId,villageCode,villageName) Values(67,22,'Y.RAMPURAM');



insert into village(mandalId,villageCode,villageName) Values(68,6,'ANKAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(68,8,'AVULENNA');
insert into village(mandalId,villageCode,villageName) Values(68,3,'BELUGUPPA');
insert into village(mandalId,villageCode,villageName) Values(68,5,'BUDIGUMMA');
insert into village(mandalId,villageCode,villageName) Values(68,11,'DUDDEKUNTA');
insert into village(mandalId,villageCode,villageName) Values(68,13,'GANGAVARAM');
insert into village(mandalId,villageCode,villageName) Values(68,14,'KALVAPALLE');
insert into village(mandalId,villageCode,villageName) Values(68,12,'KONAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(68,9,'NARASAPURAM');
insert into village(mandalId,villageCode,villageName) Values(68,1,'NARINJAGUNDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(68,10,'SEERPI');
insert into village(mandalId,villageCode,villageName) Values(68,2,'SREERANGAPURAM');
insert into village(mandalId,villageCode,villageName) Values(68,4,'THAGGUPARTHY');
insert into village(mandalId,villageCode,villageName) Values(68,7,'YERRAGUDI');



insert into village(mandalId,villageCode,villageName) Values(69,4,'BENNIKAL');
insert into village(mandalId,villageCode,villageName) Values(69,5,'BIDURUKONTHAM');
insert into village(mandalId,villageCode,villageName) Values(69,3,'BRAHMASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(69,1,'GANIGERA');
insert into village(mandalId,villageCode,villageName) Values(69,7,'GARUDACHEDU');
insert into village(mandalId,villageCode,villageName) Values(69,17,'HANAKANAHAL');
insert into village(mandalId,villageCode,villageName) Values(69,11,'HULIKERA');
insert into village(mandalId,villageCode,villageName) Values(69,13,'KALEKURTHY');
insert into village(mandalId,villageCode,villageName) Values(69,10,'KANEKAL');
insert into village(mandalId,villageCode,villageName) Values(69,14,'MALYAM');
insert into village(mandalId,villageCode,villageName) Values(69,6,'MEENAHALLI');
insert into village(mandalId,villageCode,villageName) Values(69,15,'N.HANUMAPURAM');
insert into village(mandalId,villageCode,villageName) Values(69,12,'RATCHUMARRI');
insert into village(mandalId,villageCode,villageName) Values(69,16,'SOLLAPURAM');
insert into village(mandalId,villageCode,villageName) Values(69,8,'THUMBIGANUR');
insert into village(mandalId,villageCode,villageName) Values(69,9,'UDEGOLAM');
insert into village(mandalId,villageCode,villageName) Values(69,2,'YERRAGUNTA');



insert into village(mandalId,villageCode,villageName) Values(70,12,'AVULADATLA');
insert into village(mandalId,villageCode,villageName) Values(70,11,'BAGINAYAKANA HALLI');
insert into village(mandalId,villageCode,villageName) Values(70,4,'BONDANAKAL');
insert into village(mandalId,villageCode,villageName) Values(70,3,'CHADAM');
insert into village(mandalId,villageCode,villageName) Values(70,6,'D.KONDAPURAM');
insert into village(mandalId,villageCode,villageName) Values(70,5,'GRAMADATLA');
insert into village(mandalId,villageCode,villageName) Values(70,15,'JUNJURAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(70,8,'KONTANAHALLI');
insert into village(mandalId,villageCode,villageName) Values(70,2,'MALLAPURAM');
insert into village(mandalId,villageCode,villageName) Values(70,1,'MECHIRI');
insert into village(mandalId,villageCode,villageName) Values(70,13,'NAGIREDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(70,9,'RAYADURG');
insert into village(mandalId,villageCode,villageName) Values(70,10,'UDEGOLAM');
insert into village(mandalId,villageCode,villageName) Values(70,7,'VADRAHONNUR');
insert into village(mandalId,villageCode,villageName) Values(70,14,'VEPARALLA');



insert into village(mandalId,villageCode,villageName) Values(71,4,'BELODU');
insert into village(mandalId,villageCode,villageName) Values(71,6,'BHUPASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(71,1,'GALAGALA');
insert into village(mandalId,villageCode,villageName) Values(71,2,'GOLLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(71,11,'GUMMAGATTA');
insert into village(mandalId,villageCode,villageName) Values(71,7,'KALUGODU');
insert into village(mandalId,villageCode,villageName) Values(71,8,'PULAKUNTA');
insert into village(mandalId,villageCode,villageName) Values(71,9,'RANGASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(71,5,'S.HOSAHALLI');
insert into village(mandalId,villageCode,villageName) Values(71,10,'TALLAKERA');
insert into village(mandalId,villageCode,villageName) Values(71,3,'VEERAPURAM');



insert into village(mandalId,villageCode,villageName) Values(72,4,'BHAIRASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(72,8,'BHYRAVANITHIPPA');
insert into village(mandalId,villageCode,villageName) Values(72,11,'BRAHMASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(72,5,'CHELIMENAHALLI');
insert into village(mandalId,villageCode,villageName) Values(72,10,'ERADIKERA');
insert into village(mandalId,villageCode,villageName) Values(72,7,'GUNDIGANIHALLI');
insert into village(mandalId,villageCode,villageName) Values(72,1,'KANNEPALLE');
insert into village(mandalId,villageCode,villageName) Values(72,3,'PILLALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(72,13,'SANTHEKONDAPURAM');
insert into village(mandalId,villageCode,villageName) Values(72,2,'THEETAKAL');
insert into village(mandalId,villageCode,villageName) Values(72,6,'VEPALAPARTHY');
insert into village(mandalId,villageCode,villageName) Values(72,9,'WEST KODIPALLE');
insert into village(mandalId,villageCode,villageName) Values(72,12,'YERRAKONDAPURAM');


insert into village(mandalId,villageCode,villageName) Values(73,2,'ADIVIGOLLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(73,6,'AYYAGARLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(73,4,'BACHEPALLI');
insert into village(mandalId,villageCode,villageName) Values(73,8,'CHINTARLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(73,5,'KAMTHANAHALLI');
insert into village(mandalId,villageCode,villageName) Values(73,9,'KHAIREVU');
insert into village(mandalId,villageCode,villageName) Values(73,10,'LAKSHMAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(73,7,'MULAKALEDU');
insert into village(mandalId,villageCode,villageName) Values(73,1,'SETTUR');
insert into village(mandalId,villageCode,villageName) Values(73,3,'YATAKAL');


insert into village(mandalId,villageCode,villageName) Values(74,2,'APPILEPALLE');
insert into village(mandalId,villageCode,villageName) Values(74,1,'BASAPURAM');
insert into village(mandalId,villageCode,villageName) Values(74,6,'BESTHARAPALLE');
insert into village(mandalId,villageCode,villageName) Values(74,10,'JAMBUGUMPALA');
insert into village(mandalId,villageCode,villageName) Values(74,3,'KARIGANIPALLE');
insert into village(mandalId,villageCode,villageName) Values(74,7,'KUNDURPI');
insert into village(mandalId,villageCode,villageName) Values(74,8,'MALAYANUR');
insert into village(mandalId,villageCode,villageName) Values(74,5,'MALLAPURAM');
insert into village(mandalId,villageCode,villageName) Values(74,9,'NIJAVALLI');
insert into village(mandalId,villageCode,villageName) Values(74,4,'YENUMALADODDI');


insert into village(mandalId,villageCode,villageName) Values(75,13,'BEDRAHALLI');
insert into village(mandalId,villageCode,villageName) Values(75,2,'CHAPIRI');
insert into village(mandalId,villageCode,villageName) Values(75,15,'DURADAKUNTA');
insert into village(mandalId,villageCode,villageName) Values(75,9,'EAST KODIPALLE');
insert into village(mandalId,villageCode,villageName) Values(75,11,'GARUDAPURAM');
insert into village(mandalId,villageCode,villageName) Values(75,4,'GOLLA');
insert into village(mandalId,villageCode,villageName) Values(75,1,'HULIKAL');
insert into village(mandalId,villageCode,villageName) Values(75,10,'KALYANDURG (RURAL)');
insert into village(mandalId,villageCode,villageName) Values(75,12,'KURUBARAHALLI');
insert into village(mandalId,villageCode,villageName) Values(75,5,'MANIREVU');
insert into village(mandalId,villageCode,villageName) Values(75,7,'MUDDINAYANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(75,3,'MUDIGAL');
insert into village(mandalId,villageCode,villageName) Values(75,14,'PALAVOY');
insert into village(mandalId,villageCode,villageName) Values(75,6,'THIMMASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(75,8,'VARLI');


insert into village(mandalId,villageCode,villageName) Values(76,1,'ATMAKUR');
insert into village(mandalId,villageCode,villageName) Values(76,12,'BAIRLUTYGUDEM');
insert into village(mandalId,villageCode,villageName) Values(76,4,'INDIRESWARAM');
insert into village(mandalId,villageCode,villageName) Values(76,11,'INDIRESWARAMGUDEM');
insert into village(mandalId,villageCode,villageName) Values(76,10,'KARIVENA');
insert into village(mandalId,villageCode,villageName) Values(76,7,'KRISHNAPURAM');
insert into village(mandalId,villageCode,villageName) Values(76,2,'KURUKUNDA');
insert into village(mandalId,villageCode,villageName) Values(76,14,'NAGALOOTY GUDEM');
insert into village(mandalId,villageCode,villageName) Values(76,9,'NALLAKALVA');
insert into village(mandalId,villageCode,villageName) Values(76,5,'PINNAPURAM');
insert into village(mandalId,villageCode,villageName) Values(76,13,'SANJEEVANAGAR THANDA');
insert into village(mandalId,villageCode,villageName) Values(76,6,'SIDDAPURAM');
insert into village(mandalId,villageCode,villageName) Values(76,8,'SIDDEPALLE');
insert into village(mandalId,villageCode,villageName) Values(76,3,'VADLA RAMAPURAM');


insert into village(mandalId,villageCode,villageName) Values(77,10,'ALAMURU');
insert into village(mandalId,villageCode,villageName) Values(77,14,'ANANTAPUR (RURAL)');
insert into village(mandalId,villageCode,villageName) Values(77,20,'CHIYYEDU');
insert into village(mandalId,villageCode,villageName) Values(77,8,'GOLLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(77,16,'ITIKALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(77,18,'JANGALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(77,12,'KAKKALAPALLE (RURAL)');
insert into village(mandalId,villageCode,villageName) Values(77,9,'KAMARUPALLE');
insert into village(mandalId,villageCode,villageName) Values(77,17,'KANDAKUR');
insert into village(mandalId,villageCode,villageName) Values(77,11,'KATIGANIKALVA');
insert into village(mandalId,villageCode,villageName) Values(77,4,'KODIMI');
insert into village(mandalId,villageCode,villageName) Values(77,7,'KURUGUNTA');
insert into village(mandalId,villageCode,villageName) Values(77,19,'MANNILA');
insert into village(mandalId,villageCode,villageName) Values(77,3,'NARAYANAPURAM');
insert into village(mandalId,villageCode,villageName) Values(77,13,'PAPAMPETA (RURAL)');
insert into village(mandalId,villageCode,villageName) Values(77,5,'RACHANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(77,6,'SAJJALAKALVA');
insert into village(mandalId,villageCode,villageName) Values(77,2,'SOMALADODDI');
insert into village(mandalId,villageCode,villageName) Values(77,1,'THATICHERLA');
insert into village(mandalId,villageCode,villageName) Values(77,15,'UPPARAPALLE');


insert into village(mandalId,villageCode,villageName) Values(78,6,'BODIGANIDODDI');
insert into village(mandalId,villageCode,villageName) Values(78,10,'BUKKARAYASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(78,3,'CHEDULLA');
insert into village(mandalId,villageCode,villageName) Values(78,5,'CHENNAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(78,7,'CHENNARAYAUNIPALLE');
insert into village(mandalId,villageCode,villageName) Values(78,13,'DANDUVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(78,9,'GOVINDAPALLE');
insert into village(mandalId,villageCode,villageName) Values(78,2,'JANTHULUR');
insert into village(mandalId,villageCode,villageName) Values(78,12,'KONDAKINDA AGRAHARAM');
insert into village(mandalId,villageCode,villageName) Values(78,4,'KORRAPADU');
insert into village(mandalId,villageCode,villageName) Values(78,1,'PODARALLA');
insert into village(mandalId,villageCode,villageName) Values(78,11,'REDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(78,8,'SIDDAMPETA');
insert into village(mandalId,villageCode,villageName) Values(78,14,'SIDDARAMPURAM');



insert into village(mandalId,villageCode,villageName) Values(79,11,'B.PAPPURU');
insert into village(mandalId,villageCode,villageName) Values(79,10,'BANDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(79,7,'BONDALAWADA');
insert into village(mandalId,villageCode,villageName) Values(79,3,'CHAMALURU');
insert into village(mandalId,villageCode,villageName) Values(79,6,'DUGGUMARRI');
insert into village(mandalId,villageCode,villageName) Values(79,9,'DURGAM');
insert into village(mandalId,villageCode,villageName) Values(79,5,'GUGUDU');
insert into village(mandalId,villageCode,villageName) Values(79,12,'HAVELI SODANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(79,2,'NADIMIDODDI');
insert into village(mandalId,villageCode,villageName) Values(79,4,'NARPALA');
insert into village(mandalId,villageCode,villageName) Values(79,8,'SIDDARASCHERLA');
insert into village(mandalId,villageCode,villageName) Values(79,1,'VENKATAMPALLE');



insert into village(mandalId,villageCode,villageName) Values(80,2,'ARAKATIVEMULA');
insert into village(mandalId,villageCode,villageName) Values(80,15,'CHALAVEMULA');
insert into village(mandalId,villageCode,villageName) Values(80,9,'CHERLOPALLE');
insert into village(mandalId,villageCode,villageName) Values(80,17,'CHINNA MALLE PALLI');
insert into village(mandalId,villageCode,villageName) Values(80,3,'CHINTAKUNTA');
insert into village(mandalId,villageCode,villageName) Values(80,11,'DOSALEDU');
insert into village(mandalId,villageCode,villageName) Values(80,13,'ELLUTLA');
insert into village(mandalId,villageCode,villageName) Values(80,6,'GANDLAPADU');
insert into village(mandalId,villageCode,villageName) Values(80,16,'GARUGU CHINTALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(80,10,'KADAVAKAL');
insert into village(mandalId,villageCode,villageName) Values(80,4,'KANDIKAPULA');
insert into village(mandalId,villageCode,villageName) Values(80,8,'KOMATIKUNTLA');
insert into village(mandalId,villageCode,villageName) Values(80,14,'KUMMANAMALA');
insert into village(mandalId,villageCode,villageName) Values(80,12,'MADUGUPALLE');
insert into village(mandalId,villageCode,villageName) Values(80,5,'PUTLUR');
insert into village(mandalId,villageCode,villageName) Values(80,7,'SANGALAGUDURU');
insert into village(mandalId,villageCode,villageName) Values(80,1,'SUREPALLE');


insert into village(mandalId,villageCode,villageName) Values(81,14,'ARAVEDU');
insert into village(mandalId,villageCode,villageName) Values(81,12,'BOPPEPALLE');
insert into village(mandalId,villageCode,villageName) Values(81,13,'BUKKAPURAM');
insert into village(mandalId,villageCode,villageName) Values(81,11,'CHILAMAKURU');
insert into village(mandalId,villageCode,villageName) Values(81,17,'CHINTAKAYAMANDA');
insert into village(mandalId,villageCode,villageName) Values(81,18,'GODDUMARRI');
insert into village(mandalId,villageCode,villageName) Values(81,7,'KACHARLAKUNTA');
insert into village(mandalId,villageCode,villageName) Values(81,16,'KALLURU');
insert into village(mandalId,villageCode,villageName) Values(81,10,'KODUMURTHY');
insert into village(mandalId,villageCode,villageName) Values(81,15,'MALLAGUNDLA');
insert into village(mandalId,villageCode,villageName) Values(81,2,'MEDUKURTHY');
insert into village(mandalId,villageCode,villageName) Values(81,3,'NITTURU');
insert into village(mandalId,villageCode,villageName) Values(81,8,'PATHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(81,4,'PEDDAMALLEPALLE');
insert into village(mandalId,villageCode,villageName) Values(81,19,'SINGAVARAM');
insert into village(mandalId,villageCode,villageName) Values(81,5,'THIRUMALAPURAM');
insert into village(mandalId,villageCode,villageName) Values(81,1,'VEMULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(81,9,'VENNAPUSAPALLE');
insert into village(mandalId,villageCode,villageName) Values(81,6,'YELLANUR');



insert into village(mandalId,villageCode,villageName) Values(82,8,'(SHRO) THIRUMALAPURAM');
insert into village(mandalId,villageCode,villageName) Values(82,3,'CHILLAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(82,12,'CHINNACHIGULLAREVU');
insert into village(mandalId,villageCode,villageName) Values(82,4,'DADITHOTA');
insert into village(mandalId,villageCode,villageName) Values(82,7,'KONDAMANAYANIPALEM');
insert into village(mandalId,villageCode,villageName) Values(82,2,'KUNUKUNTLA');
insert into village(mandalId,villageCode,villageName) Values(82,11,'MARAVAPALLE AGRAHARAM');
insert into village(mandalId,villageCode,villageName) Values(82,10,'NIDIGALLU');
insert into village(mandalId,villageCode,villageName) Values(82,6,'PEDDAKOTLA');
insert into village(mandalId,villageCode,villageName) Values(82,5,'PERNAPALLE');
insert into village(mandalId,villageCode,villageName) Values(82,1,'PINNADARI');
insert into village(mandalId,villageCode,villageName) Values(82,9,'TADIMARRI');


insert into village(mandalId,villageCode,villageName) Values(83,5,'APPARACHERUVU');
insert into village(mandalId,villageCode,villageName) Values(83,4,'BATHALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(83,6,'CHENNARAYAPATNAM');
insert into village(mandalId,villageCode,villageName) Values(83,11,'D.CHERLOPALLE');
insert into village(mandalId,villageCode,villageName) Values(83,10,'DAMPETLA');
insert into village(mandalId,villageCode,villageName) Values(83,1,'EDULA MUSTUR');
insert into village(mandalId,villageCode,villageName) Values(83,9,'GARISELAPALLE');
insert into village(mandalId,villageCode,villageName) Values(83,2,'MALYAVANTHAM');
insert into village(mandalId,villageCode,villageName) Values(83,8,'OBULAPURAM');
insert into village(mandalId,villageCode,villageName) Values(83,3,'RAGHAVAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(83,7,'SANGALA');


insert into village(mandalId,villageCode,villageName) Values(84,9,'BANDAMEEDAPALLE');
insert into village(mandalId,villageCode,villageName) Values(84,3,'BOMMEPARTHY');
insert into village(mandalId,villageCode,villageName) Values(84,6,'BUKKACHERLA');
insert into village(mandalId,villageCode,villageName) Values(84,8,'GANDLAPARTHI');
insert into village(mandalId,villageCode,villageName) Values(84,2,'GANGULAKUNTA');
insert into village(mandalId,villageCode,villageName) Values(84,5,'GONDIREDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(84,4,'HAMPAPURAM');
insert into village(mandalId,villageCode,villageName) Values(84,11,'MARUR');
insert into village(mandalId,villageCode,villageName) Values(84,7,'PALACHERLA');
insert into village(mandalId,villageCode,villageName) Values(84,1,'RAPTADU');
insert into village(mandalId,villageCode,villageName) Values(84,10,'YERRAGUNTA');


insert into village(mandalId,villageCode,villageName) Values(85,10,'DADULUR');
insert into village(mandalId,villageCode,villageName) Values(85,6,'ELUKUNTLA');
insert into village(mandalId,villageCode,villageName) Values(85,8,'KANAGANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(85,4,'KONETINAYANIPALYAM');
insert into village(mandalId,villageCode,villageName) Values(85,3,'MADDULACHERUVU');
insert into village(mandalId,villageCode,villageName) Values(85,9,'MUKTHAPURAM');
insert into village(mandalId,villageCode,villageName) Values(85,7,'MUTHAVAKUNTLA');
insert into village(mandalId,villageCode,villageName) Values(85,5,'NARASAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(85,2,'THOGARAKUNTA');
insert into village(mandalId,villageCode,villageName) Values(85,1,'THUMUCHERLA');


insert into village(mandalId,villageCode,villageName) Values(86,11,'CHENNAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(86,6,'GULYAM');
insert into village(mandalId,villageCode,villageName) Values(86,9,'KAMBADUR');
insert into village(mandalId,villageCode,villageName) Values(86,5,'KARTHANAPARTHI');
insert into village(mandalId,villageCode,villageName) Values(86,12,'KURAKULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(86,1,'MULAKANUR');
insert into village(mandalId,villageCode,villageName) Values(86,4,'NUTHIMADUGU');
insert into village(mandalId,villageCode,villageName) Values(86,2,'PALLUR');
insert into village(mandalId,villageCode,villageName) Values(86,7,'RALLAANANTAPURAM');
insert into village(mandalId,villageCode,villageName) Values(86,8,'RALLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(86,3,'RAMAPURAM');
insert into village(mandalId,villageCode,villageName) Values(86,10,'THIMMAPURAM');


insert into village(mandalId,villageCode,villageName) Values(87,10,'GANTHIMARRI');
insert into village(mandalId,villageCode,villageName) Values(87,3,'KONDAPURAM');
insert into village(mandalId,villageCode,villageName) Values(87,9,'KUNTIMADDI');
insert into village(mandalId,villageCode,villageName) Values(87,2,'MAKKINAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(87,4,'MOTARCHINTALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(87,5,'NASANAKOTA');
insert into village(mandalId,villageCode,villageName) Values(87,1,'PERUR');
insert into village(mandalId,villageCode,villageName) Values(87,7,'POLEPALLE');
insert into village(mandalId,villageCode,villageName) Values(87,6,'RAMAGIRI');
insert into village(mandalId,villageCode,villageName) Values(87,8,'SESHADRIBHATRA HALLI');



insert into village(mandalId,villageCode,villageName) Values(88,11,'BRAMHANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(88,3,'CHENNEKOTHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(88,10,'GANGINEPALLE');
insert into village(mandalId,villageCode,villageName) Values(88,8,'KANUMUKKALA');
insert into village(mandalId,villageCode,villageName) Values(88,6,'MEDAPURAM');
insert into village(mandalId,villageCode,villageName) Values(88,1,'MUSTIKOVILA');
insert into village(mandalId,villageCode,villageName) Values(88,4,'NAGASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(88,2,'NYAMADDALA');
insert into village(mandalId,villageCode,villageName) Values(88,7,'POLETIPALLE');
insert into village(mandalId,villageCode,villageName) Values(88,5,'PYADINDI');
insert into village(mandalId,villageCode,villageName) Values(88,9,'VELDURTHI');


insert into village(mandalId,villageCode,villageName) Values(89,13,'BUDDAREDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(89,1,'CHIGICHERLA');
insert into village(mandalId,villageCode,villageName) Values(89,11,'DARSIMALA');
insert into village(mandalId,villageCode,villageName) Values(89,6,'DHARMAVARAM');
insert into village(mandalId,villageCode,villageName) Values(89,14,'ELUKUNTLA');
insert into village(mandalId,villageCode,villageName) Values(89,2,'GOTLUR');
insert into village(mandalId,villageCode,villageName) Values(89,7,'KUNUTHURU (RURAL)');
insert into village(mandalId,villageCode,villageName) Values(89,10,'MALLAKALVA');
insert into village(mandalId,villageCode,villageName) Values(89,12,'NELAKOTA');
insert into village(mandalId,villageCode,villageName) Values(89,9,'POTHULANAGEPALLE');
insert into village(mandalId,villageCode,villageName) Values(89,4,'RAVULACHERUVU');
insert into village(mandalId,villageCode,villageName) Values(89,8,'REGATIPALLE');
insert into village(mandalId,villageCode,villageName) Values(89,3,'SUBBARAOPETA');
insert into village(mandalId,villageCode,villageName) Values(89,5,'THUMMALA');



insert into village(mandalId,villageCode,villageName) Values(90,7,'BRAHMADEVARAMARRI');
insert into village(mandalId,villageCode,villageName) Values(90,2,'BUDANAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(90,16,'CHAGAPURAM');
insert into village(mandalId,villageCode,villageName) Values(90,1,'CHINNAKOTLA');
insert into village(mandalId,villageCode,villageName) Values(90,27,'DEVARAGUDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(90,14,'DORIGALLU');
insert into village(mandalId,villageCode,villageName) Values(90,22,'ELLAREDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(90,6,'GANDLAVANDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(90,15,'GUNJEPALLE');
insert into village(mandalId,villageCode,villageName) Values(90,11,'JONNALAKOTHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(90,3,'KODAVANDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(90,26,'KONDAGATTUPALLE');
insert into village(mandalId,villageCode,villageName) Values(90,20,'MALAKAVEMULA');
insert into village(mandalId,villageCode,villageName) Values(90,24,'MALLEPALLE');
insert into village(mandalId,villageCode,villageName) Values(90,17,'MANGALAMADAKA');
insert into village(mandalId,villageCode,villageName) Values(90,4,'MARTHADU');
insert into village(mandalId,villageCode,villageName) Values(90,10,'MUKTHAPURAM');
insert into village(mandalId,villageCode,villageName) Values(90,19,'NAGAREDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(90,18,'NAKKALAGUTTAPALLE');
insert into village(mandalId,villageCode,villageName) Values(90,8,'PEDDACHIGULLAREVU');
insert into village(mandalId,villageCode,villageName) Values(90,5,'S.BANDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(90,21,'SANEVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(90,12,'SANKEPALLE');
insert into village(mandalId,villageCode,villageName) Values(90,13,'SANKEPALLE BRAHMANAPALL');
insert into village(mandalId,villageCode,villageName) Values(90,25,'THAPPETAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(90,23,'THIMMANAYANIPALEM');
insert into village(mandalId,villageCode,villageName) Values(90,9,'UPPALAPADU');


insert into village(mandalId,villageCode,villageName) Values(91,10,'BANDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(91,3,'KURLI');
insert into village(mandalId,villageCode,villageName) Values(91,1,'LAKKASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(91,11,'NUTHANAKALVA');
insert into village(mandalId,villageCode,villageName) Values(91,9,'OBULAREDDEPALLE');
insert into village(mandalId,villageCode,villageName) Values(91,2,'ODULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(91,7,'PEDDANNAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(91,8,'PULIGUNDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(91,5,'TALUPULA');
insert into village(mandalId,villageCode,villageName) Values(91,4,'UDUMULAKURTHY');
insert into village(mandalId,villageCode,villageName) Values(91,6,'VEPAMANIPETA');


insert into village(mandalId,villageCode,villageName) Values(92,6,'DHANIYANICHERUVU');
insert into village(mandalId,villageCode,villageName) Values(92,10,'EDURUDONA');
insert into village(mandalId,villageCode,villageName) Values(92,9,'GOOTIBYLU');
insert into village(mandalId,villageCode,villageName) Values(92,2,'GOWKANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(92,3,'MARRIKOMMADINNE');
insert into village(mandalId,villageCode,villageName) Values(92,8,'MEKALACHERUVU');
insert into village(mandalId,villageCode,villageName) Values(92,7,'MUDUPALAJEEVI');
insert into village(mandalId,villageCode,villageName) Values(92,4,'NAMBULAPULIKUNTA');
insert into village(mandalId,villageCode,villageName) Values(92,11,'PEDABALLI');
insert into village(mandalId,villageCode,villageName) Values(92,12,'PEDABALLIKOTHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(92,5,'VANKAMADDI');
insert into village(mandalId,villageCode,villageName) Values(92,1,'VELICHELIMALA');


insert into village(mandalId,villageCode,villageName) Values(93,8,'AGRAHARAMPALLI');
insert into village(mandalId,villageCode,villageName) Values(93,10,'BALASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(93,19,'BONTHALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(93,20,'CHEEKATIMANIPALLE');
insert into village(mandalId,villageCode,villageName) Values(93,1,'CHINNARAMANNAGARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(93,16,'DANDUVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(93,4,'DIGUVAMANDALAPALLI');
insert into village(mandalId,villageCode,villageName) Values(93,12,'ETHODU');
insert into village(mandalId,villageCode,villageName) Values(93,18,'GURRAMBAILU');
insert into village(mandalId,villageCode,villageName) Values(93,5,'KOKKANTI');
insert into village(mandalId,villageCode,villageName) Values(93,6,'KORTHIKOTA');
insert into village(mandalId,villageCode,villageName) Values(93,13,'KOTAPALLE');
insert into village(mandalId,villageCode,villageName) Values(93,7,'MADDINAYANIPALYAM');
insert into village(mandalId,villageCode,villageName) Values(93,17,'MALREDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(93,14,'MUNDLAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(93,9,'T.SADUM');
insert into village(mandalId,villageCode,villageName) Values(93,3,'TANAKAL');
insert into village(mandalId,villageCode,villageName) Values(93,11,'TAVALAM');
insert into village(mandalId,villageCode,villageName) Values(93,2,'ULAVALAVANDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(93,15,'VENKATRAYANIPALLE');


insert into village(mandalId,villageCode,villageName) Values(94,2,'ALLUGUNDU');
insert into village(mandalId,villageCode,villageName) Values(94,3,'JOGANNAPETA');
insert into village(mandalId,villageCode,villageName) Values(94,9,'KADIRIPULIKUNTA');
insert into village(mandalId,villageCode,villageName) Values(94,6,'MADDIMADUGU');
insert into village(mandalId,villageCode,villageName) Values(94,4,'NALLACHERUVU');
insert into village(mandalId,villageCode,villageName) Values(94,11,'ORAVOY');
insert into village(mandalId,villageCode,villageName) Values(94,1,'PANTHULACHERUVU');
insert into village(mandalId,villageCode,villageName) Values(94,5,'SHO.MULAKALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(94,10,'TALAMARLAVANDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(94,7,'TAVALAMARRI');
insert into village(mandalId,villageCode,villageName) Values(94,8,'UBICHERLA');


insert into village(mandalId,villageCode,villageName) Values(95,12,'CHAMACHENUBYLU');
insert into village(mandalId,villageCode,villageName) Values(95,9,'CHAMALAGONDI');
insert into village(mandalId,villageCode,villageName) Values(95,5,'GANDLAPENTA');
insert into village(mandalId,villageCode,villageName) Values(95,1,'GODDUVELAGALA');
insert into village(mandalId,villageCode,villageName) Values(95,6,'JEENULAKUNTA');
insert into village(mandalId,villageCode,villageName) Values(95,3,'KAMATHAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(95,8,'KURUMAMIDI');
insert into village(mandalId,villageCode,villageName) Values(95,4,'MADDIVARIGONDI');
insert into village(mandalId,villageCode,villageName) Values(95,11,'MADUGUVANIGONDI');
insert into village(mandalId,villageCode,villageName) Values(95,13,'MALAMEEDAPALLI');
insert into village(mandalId,villageCode,villageName) Values(95,10,'SOMAYAJULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(95,2,'THUMMALABYLU');
insert into village(mandalId,villageCode,villageName) Values(95,7,'VEPARALA');



insert into village(mandalId,villageCode,villageName) Values(96,6,'ALAMPUR');
insert into village(mandalId,villageCode,villageName) Values(96,16,'BATHALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(96,8,'CHALAMAKUNTLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(96,3,'CHIPPALAMADUGU');
insert into village(mandalId,villageCode,villageName) Values(96,11,'KADIRI (RURAL)');
insert into village(mandalId,villageCode,villageName) Values(96,15,'KADIRI BRAHMANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(96,5,'KADIRIKUNTLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(96,2,'KALASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(96,12,'KONDAMANAYANIPALEM');
insert into village(mandalId,villageCode,villageName) Values(96,17,'KOWLEPALLE');
insert into village(mandalId,villageCode,villageName) Values(96,9,'KUTAGULLA');
insert into village(mandalId,villageCode,villageName) Values(96,18,'MOTUKAPALLE');
insert into village(mandalId,villageCode,villageName) Values(96,13,'MUTHYALACHERUVU');
insert into village(mandalId,villageCode,villageName) Values(96,7,'PANDULAKUNTLA');
insert into village(mandalId,villageCode,villageName) Values(96,1,'PATNAM');
insert into village(mandalId,villageCode,villageName) Values(96,10,'SAIDAPURAM');
insert into village(mandalId,villageCode,villageName) Values(96,14,'YAGUVAPALLE');
insert into village(mandalId,villageCode,villageName) Values(96,4,'YERRADODDI');



insert into village(mandalId,villageCode,villageName) Values(97,6,'AMADAGUR');
insert into village(mandalId,villageCode,villageName) Values(97,11,'CHEEKIREVULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(97,7,'CHINAGANIPALLE');
insert into village(mandalId,villageCode,villageName) Values(97,14,'DADEMVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(97,10,'EDIGAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(97,9,'GADIDAMVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(97,2,'JOWKALAKOTHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(97,12,'KARIMIREDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(97,4,'KASSAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(97,15,'LOKOJIPALLE');
insert into village(mandalId,villageCode,villageName) Values(97,3,'MOHAMMADABAD');
insert into village(mandalId,villageCode,villageName) Values(97,13,'PULAKUNTLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(97,8,'RAMANATHAPURAM');
insert into village(mandalId,villageCode,villageName) Values(97,1,'S.KURUVAPALLE');
insert into village(mandalId,villageCode,villageName) Values(97,5,'THUMMALA');


insert into village(mandalId,villageCode,villageName) Values(98,4,'ALLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(98,14,'ARAKABHAVIPALLE');
insert into village(mandalId,villageCode,villageName) Values(98,3,'DABURUVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(98,7,'INAGALUR');
insert into village(mandalId,villageCode,villageName) Values(98,1,'KONDAKAMARLA');
insert into village(mandalId,villageCode,villageName) Values(98,6,'MUKKANDLAVARIKOTHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(98,2,'NARASAMBHOTLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(98,11,'OBULADEVARACHERUVU');
insert into village(mandalId,villageCode,villageName) Values(98,12,'SUNNAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(98,13,'TANGEDUKUNTA');
insert into village(mandalId,villageCode,villageName) Values(98,5,'THIPPEPALLE');
insert into village(mandalId,villageCode,villageName) Values(98,8,'THUMMALAKUNTLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(98,9,'VEERAOBANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(98,10,'VENKATAPURAM');


insert into village(mandalId,villageCode,villageName) Values(99,2,'CHARUPALLE');
insert into village(mandalId,villageCode,villageName) Values(99,6,'CHOWTIKUNTAPALLE');
insert into village(mandalId,villageCode,villageName) Values(99,9,'DONNIKOTA');
insert into village(mandalId,villageCode,villageName) Values(99,8,'GOPEPALLE');
insert into village(mandalId,villageCode,villageName) Values(99,7,'KURUMALA');
insert into village(mandalId,villageCode,villageName) Values(99,5,'NALLAMADA');
insert into village(mandalId,villageCode,villageName) Values(99,10,'PULAGAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(99,3,'REDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(99,4,'VANKARAKUNTA');
insert into village(mandalId,villageCode,villageName) Values(99,1,'VELLAMADDI');



insert into village(mandalId,villageCode,villageName) Values(100,4,'BAYANNAKUNTAPALLE');
insert into village(mandalId,villageCode,villageName) Values(100,9,'BOYALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(100,18,'BUDILI');
insert into village(mandalId,villageCode,villageName) Values(100,10,'BUGANIPALLE');
insert into village(mandalId,villageCode,villageName) Values(100,15,'DEVULACHERUVU');
insert into village(mandalId,villageCode,villageName) Values(100,1,'GANGAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(100,19,'GORANTLA');
insert into village(mandalId,villageCode,villageName) Values(100,5,'GOWNIVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(100,3,'JAKKASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(100,7,'JOWKULEDUDINNE');
insert into village(mandalId,villageCode,villageName) Values(100,6,'KAMMAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(100,13,'KATEPALLE');
insert into village(mandalId,villageCode,villageName) Values(100,11,'MALLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(100,8,'MANDALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(100,20,'MAREDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(100,12,'OBULAPURAM');
insert into village(mandalId,villageCode,villageName) Values(100,16,'PALASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(100,21,'PULERU');
insert into village(mandalId,villageCode,villageName) Values(100,14,'RAGIMAKULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(100,17,'VADIGEPALLE');
insert into village(mandalId,villageCode,villageName) Values(100,2,'VANAVOLU');



insert into village(mandalId,villageCode,villageName) Values(101,4,'AMAGONDAPALEM');
insert into village(mandalId,villageCode,villageName) Values(101,7,'BEEDUPALLE');
insert into village(mandalId,villageCode,villageName) Values(101,6,'BRAHMANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(101,9,'JAGARAJUPALLE');
insert into village(mandalId,villageCode,villageName) Values(101,8,'KAPPALA BANDA');
insert into village(mandalId,villageCode,villageName) Values(101,10,'KOTLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(101,11,'NIDIMAMIDI');
insert into village(mandalId,villageCode,villageName) Values(101,12,'PEDABALLE');
insert into village(mandalId,villageCode,villageName) Values(101,1,'PUTTAPARTHI');
insert into village(mandalId,villageCode,villageName) Values(101,3,'SATARLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(101,2,'VENGALAMMACHERUVU');
insert into village(mandalId,villageCode,villageName) Values(101,5,'YENUMALAPALLE (RURAL)');


insert into village(mandalId,villageCode,villageName) Values(102,4,'AGRAHARAM');
insert into village(mandalId,villageCode,villageName) Values(102,7,'BUKKAPATNAM');
insert into village(mandalId,villageCode,villageName) Values(102,5,'GUNIPALLE');
insert into village(mandalId,villageCode,villageName) Values(102,8,'KOTHAKOTA');
insert into village(mandalId,villageCode,villageName) Values(102,1,'KRISHNAPURAM');
insert into village(mandalId,villageCode,villageName) Values(102,2,'MARALA');
insert into village(mandalId,villageCode,villageName) Values(102,3,'PAMUDURTHI');
insert into village(mandalId,villageCode,villageName) Values(102,6,'SIDDARAMPURAM');


insert into village(mandalId,villageCode,villageName) Values(103,20,'BANDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(103,22,'BYRAPURAM');
insert into village(mandalId,villageCode,villageName) Values(103,12,'IRAGAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(103,17,'K.LOCHARLA');
insert into village(mandalId,villageCode,villageName) Values(103,23,'KADIREPALLE');
insert into village(mandalId,villageCode,villageName) Values(103,3,'KADIRIDEVARAPALLE');
insert into village(mandalId,villageCode,villageName) Values(103,6,'KANISETTIPALLE');
insert into village(mandalId,villageCode,villageName) Values(103,1,'KESAPURAM');
insert into village(mandalId,villageCode,villageName) Values(103,8,'KODAPAGANIPALLE');
insert into village(mandalId,villageCode,villageName) Values(103,14,'KOTHACHERUVU');
insert into village(mandalId,villageCode,villageName) Values(103,4,'KOTHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(103,13,'LINGAREDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(103,16,'MARAKUNTAPALLE');
insert into village(mandalId,villageCode,villageName) Values(103,2,'MYLASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(103,5,'NAREPALLE');
insert into village(mandalId,villageCode,villageName) Values(103,21,'PAKEERUPALLE');
insert into village(mandalId,villageCode,villageName) Values(103,18,'POTHULAKUNTA');
insert into village(mandalId,villageCode,villageName) Values(103,10,'TALAMARLA');
insert into village(mandalId,villageCode,villageName) Values(103,15,'THIPPA BATLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(103,7,'TIRUMALADEVARAPALLE');
insert into village(mandalId,villageCode,villageName) Values(103,11,'VANGAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(103,9,'VEMULETIPALLE');
insert into village(mandalId,villageCode,villageName) Values(103,19,'VIRUPAPURAM');



insert into village(mandalId,villageCode,villageName) Values(104,15,'ADADAKULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(104,12,'BOJJIREDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(104,4,'CHERLOPALLE');
insert into village(mandalId,villageCode,villageName) Values(104,1,'DUDDEBANDA');
insert into village(mandalId,villageCode,villageName) Values(104,6,'ERRAMANCHI');
insert into village(mandalId,villageCode,villageName) Values(104,7,'GONDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(104,14,'GONIPETA');
insert into village(mandalId,villageCode,villageName) Values(104,2,'GUTTURU');
insert into village(mandalId,villageCode,villageName) Values(104,11,'KONDAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(104,16,'MAVATURU');
insert into village(mandalId,villageCode,villageName) Values(104,3,'MUNIMADUGU');
insert into village(mandalId,villageCode,villageName) Values(104,17,'NAGALURU');
insert into village(mandalId,villageCode,villageName) Values(104,8,'OBULAPURAM');
insert into village(mandalId,villageCode,villageName) Values(104,9,'PENUKONDA (RURAL)');
insert into village(mandalId,villageCode,villageName) Values(104,10,'RAMPURAM');
insert into village(mandalId,villageCode,villageName) Values(104,13,'SETTIPALLE');
insert into village(mandalId,villageCode,villageName) Values(104,5,'VASUDEVAPURAM');


insert into village(mandalId,villageCode,villageName) Values(105,5,'BOKKASAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(105,9,'BUCHARLA');
insert into village(mandalId,villageCode,villageName) Values(105,20,'CHERUKUR');
insert into village(mandalId,villageCode,villageName) Values(105,18,'CHINNAMANTHUR');
insert into village(mandalId,villageCode,villageName) Values(105,14,'CHOLEMARRI');
insert into village(mandalId,villageCode,villageName) Values(105,17,'DODAGATTA');
insert into village(mandalId,villageCode,villageName) Values(105,3,'JAKKALACHERUVU');
insert into village(mandalId,villageCode,villageName) Values(105,21,'KALIPI');
insert into village(mandalId,villageCode,villageName) Values(105,4,'KOGIRA');
insert into village(mandalId,villageCode,villageName) Values(105,1,'MULAKALACHERUVU');
insert into village(mandalId,villageCode,villageName) Values(105,15,'NALLUR');
insert into village(mandalId,villageCode,villageName) Values(105,2,'NARANAGEPALLE');
insert into village(mandalId,villageCode,villageName) Values(105,19,'PEDDAMANTHUR');
insert into village(mandalId,villageCode,villageName) Values(105,12,'PEDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(105,8,'R. LOCHARLA');
insert into village(mandalId,villageCode,villageName) Values(105,16,'REDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(105,10,'RODDAKAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(105,11,'RODDAM');
insert into village(mandalId,villageCode,villageName) Values(105,13,'SANIPALLE');
insert into village(mandalId,villageCode,villageName) Values(105,7,'THURAKALAPATNAM');
insert into village(mandalId,villageCode,villageName) Values(105,6,'VENKATAPURAM');


insert into village(mandalId,villageCode,villageName) Values(106,14,'BRAHMANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(106,1,'BRAHMASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(106,9,'BUSSAIAHGARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(106,18,'CHALAKUR');
insert into village(mandalId,villageCode,villageName) Values(106,3,'CHALLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(106,4,'CHENNAPURAM');
insert into village(mandalId,villageCode,villageName) Values(106,15,'EDULABALLAPURAM');
insert into village(mandalId,villageCode,villageName) Values(106,11,'GUDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(106,16,'JULUKUNTA');
insert into village(mandalId,villageCode,villageName) Values(106,17,'KETHAGANICHERUVU');
insert into village(mandalId,villageCode,villageName) Values(106,6,'KOTHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(106,5,'MAGECHERUVU');
insert into village(mandalId,villageCode,villageName) Values(106,2,'MANDLI');
insert into village(mandalId,villageCode,villageName) Values(106,20,'NADIMPALLE');
insert into village(mandalId,villageCode,villageName) Values(106,8,'NAGINAYANICHERUVU');
insert into village(mandalId,villageCode,villageName) Values(106,13,'PANDIPARTHI');
insert into village(mandalId,villageCode,villageName) Values(106,7,'SOMANDEPALLE');
insert into village(mandalId,villageCode,villageName) Values(106,12,'TUNGODU');
insert into village(mandalId,villageCode,villageName) Values(106,10,'VELAGAMAKULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(106,19,'VELIDADAKALA');



insert into village(mandalId,villageCode,villageName) Values(107,2,'CHAGALERU');
insert into village(mandalId,villageCode,villageName) Values(107,18,'CHILAMATHUR');
insert into village(mandalId,villageCode,villageName) Values(107,7,'CHOWDIREDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(107,14,'DEMAKETHEPALLE');
insert into village(mandalId,villageCode,villageName) Values(107,9,'GANDLADINNE');
insert into village(mandalId,villageCode,villageName) Values(107,16,'HUSSAINPURAM');
insert into village(mandalId,villageCode,villageName) Values(107,4,'KAMBALADINNE');
insert into village(mandalId,villageCode,villageName) Values(107,3,'KANUGAMAKULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(107,21,'KODIKONDA');
insert into village(mandalId,villageCode,villageName) Values(107,12,'KODUR');
insert into village(mandalId,villageCode,villageName) Values(107,20,'MARRIMAKULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(107,10,'MORASALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(107,8,'MUDDIREDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(107,19,'NAREMUDDAPALLE');
insert into village(mandalId,villageCode,villageName) Values(107,1,'SETTIPALLE');
insert into village(mandalId,villageCode,villageName) Values(107,5,'SOMAGHATTA');
insert into village(mandalId,villageCode,villageName) Values(107,11,'SUBBARAOPET');
insert into village(mandalId,villageCode,villageName) Values(107,13,'TEKULODU');
insert into village(mandalId,villageCode,villageName) Values(107,17,'VEERAPURAM');
insert into village(mandalId,villageCode,villageName) Values(107,15,'YAGNISETTIPALLE');
insert into village(mandalId,villageCode,villageName) Values(107,6,'YERRASINGE PALLE');



insert into village(mandalId,villageCode,villageName) Values(108,4,'CHOLASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(108,1,'KALLUR');
insert into village(mandalId,villageCode,villageName) Values(108,9,'KANCHISAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(108,5,'KODIHALLI');
insert into village(mandalId,villageCode,villageName) Values(108,2,'KONDUR');
insert into village(mandalId,villageCode,villageName) Values(108,3,'LEPAKSHI');
insert into village(mandalId,villageCode,villageName) Values(108,7,'MANEPALLE');
insert into village(mandalId,villageCode,villageName) Values(108,10,'MYDUGOLAM');
insert into village(mandalId,villageCode,villageName) Values(108,6,'PULAMATHI');
insert into village(mandalId,villageCode,villageName) Values(108,8,'SIRIVARAM');



insert into village(mandalId,villageCode,villageName) Values(109,7,'BEVINA HALLI');
insert into village(mandalId,villageCode,villageName) Values(109,1,'CHALIVENDALA');
insert into village(mandalId,villageCode,villageName) Values(109,14,'CHOWLUR');
insert into village(mandalId,villageCode,villageName) Values(109,11,'DEVARAPALLE');
insert into village(mandalId,villageCode,villageName) Values(109,16,'GOLLAPURAM');
insert into village(mandalId,villageCode,villageName) Values(109,6,'HINDUPUR');
insert into village(mandalId,villageCode,villageName) Values(109,9,'KIRIKERA');
insert into village(mandalId,villageCode,villageName) Values(109,10,'KOTIPI');
insert into village(mandalId,villageCode,villageName) Values(109,4,'KOTNUR');
insert into village(mandalId,villageCode,villageName) Values(109,2,'MALUGURU');
insert into village(mandalId,villageCode,villageName) Values(109,3,'MANESAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(109,8,'NAKKALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(109,12,'SANTHEBIDANUR');
insert into village(mandalId,villageCode,villageName) Values(109,5,'SREEKANTHAPURAM (RURAL)');
insert into village(mandalId,villageCode,villageName) Values(109,15,'THUMUKUNTA');
insert into village(mandalId,villageCode,villageName) Values(109,13,'THUNGEPALLE');



insert into village(mandalId,villageCode,villageName) Values(110,16,'BANDARLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(110,5,'BEECHIGANIPALLE');
insert into village(mandalId,villageCode,villageName) Values(110,3,'GANAPATHIPALLE');
insert into village(mandalId,villageCode,villageName) Values(110,14,'KODIGENAHALLI');
insert into village(mandalId,villageCode,villageName) Values(110,15,'MODA');
insert into village(mandalId,villageCode,villageName) Values(110,1,'NARASAPURAM');
insert into village(mandalId,villageCode,villageName) Values(110,9,'PARIGI');
insert into village(mandalId,villageCode,villageName) Values(110,2,'PYDETI');
insert into village(mandalId,villageCode,villageName) Values(110,12,'SANGAMESWARAPALLE');
insert into village(mandalId,villageCode,villageName) Values(110,13,'SASANAKOTA');
insert into village(mandalId,villageCode,villageName) Values(110,4,'SEEGIPALLE');
insert into village(mandalId,villageCode,villageName) Values(110,7,'SHO. BEEREPALLE');
insert into village(mandalId,villageCode,villageName) Values(110,8,'SIREKOLAM');
insert into village(mandalId,villageCode,villageName) Values(110,11,'UTAKUR');
insert into village(mandalId,villageCode,villageName) Values(110,6,'VITTAPALLE');
insert into village(mandalId,villageCode,villageName) Values(110,10,'YERRAGUNTA');



insert into village(mandalId,villageCode,villageName) Values(111,1,'AMIDALAGONDI');
insert into village(mandalId,villageCode,villageName) Values(111,5,'ANANTAPURAM');
insert into village(mandalId,villageCode,villageName) Values(111,16,'BULLASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(111,19,'C.KODIGEPALLE');
insert into village(mandalId,villageCode,villageName) Values(111,3,'CHANDAKACHERELA');
insert into village(mandalId,villageCode,villageName) Values(111,6,'CHATRAM');
insert into village(mandalId,villageCode,villageName) Values(111,10,'GOVINDAPURAM');
insert into village(mandalId,villageCode,villageName) Values(111,4,'GOWDANAHALLI');
insert into village(mandalId,villageCode,villageName) Values(111,15,'HARESAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(111,11,'JADRAHALLI');
insert into village(mandalId,villageCode,villageName) Values(111,18,'KALLUMARRI');
insert into village(mandalId,villageCode,villageName) Values(111,12,'KARESANKANAHALLI');
insert into village(mandalId,villageCode,villageName) Values(111,2,'KOTHALAM');
insert into village(mandalId,villageCode,villageName) Values(111,8,'MADAKASIRA');
insert into village(mandalId,villageCode,villageName) Values(111,7,'MALLINAYAKANAHALLI');
insert into village(mandalId,villageCode,villageName) Values(111,17,'MANUR');
insert into village(mandalId,villageCode,villageName) Values(111,9,'MELAVOI');
insert into village(mandalId,villageCode,villageName) Values(111,20,'TIRUMALADEVARAHALLI');
insert into village(mandalId,villageCode,villageName) Values(111,14,'UPPARLAHALLI');
insert into village(mandalId,villageCode,villageName) Values(111,13,'YERRABOMMANAHALLI');



insert into village(mandalId,villageCode,villageName) Values(112,7,'CHIGATHURPI');
insert into village(mandalId,villageCode,villageName) Values(112,6,'GUDIBANDA');
insert into village(mandalId,villageCode,villageName) Values(112,9,'GUNE MORUBAGAL');
insert into village(mandalId,villageCode,villageName) Values(112,2,'KARIKERA');
insert into village(mandalId,villageCode,villageName) Values(112,5,'KEKATHI');
insert into village(mandalId,villageCode,villageName) Values(112,12,'KONKALLU');
insert into village(mandalId,villageCode,villageName) Values(112,1,'KUMBARANAGEHALLI');
insert into village(mandalId,villageCode,villageName) Values(112,11,'MANDALAHALLI');
insert into village(mandalId,villageCode,villageName) Values(112,8,'MORUBAGAL');
insert into village(mandalId,villageCode,villageName) Values(112,10,'MUTHUKUR');
insert into village(mandalId,villageCode,villageName) Values(112,4,'PILLENAHALLI');
insert into village(mandalId,villageCode,villageName) Values(112,3,'RALLAHALLI');
insert into village(mandalId,villageCode,villageName) Values(112,13,'S. RAYAPURAM');



insert into village(mandalId,villageCode,villageName) Values(113,3,'AMARAPURAM');
insert into village(mandalId,villageCode,villageName) Values(113,9,'BASAVANAHALLI');
insert into village(mandalId,villageCode,villageName) Values(113,4,'HALUKURU');
insert into village(mandalId,villageCode,villageName) Values(113,6,'HEMAVATHI');
insert into village(mandalId,villageCode,villageName) Values(113,5,'NIDRAGATTA');
insert into village(mandalId,villageCode,villageName) Values(113,7,'SIVARAM');
insert into village(mandalId,villageCode,villageName) Values(113,2,'TAMMEDAHALLI');
insert into village(mandalId,villageCode,villageName) Values(113,1,'VALASA');
insert into village(mandalId,villageCode,villageName) Values(113,8,'VEERABOMMANAHALLI');


insert into village(mandalId,villageCode,villageName) Values(114,8,'AGALI');
insert into village(mandalId,villageCode,villageName) Values(114,3,'AKKAGALADEVARAHALLI');
insert into village(mandalId,villageCode,villageName) Values(114,7,'HULIKERADEVARAHALLI');
insert into village(mandalId,villageCode,villageName) Values(114,2,'INAGALORE');
insert into village(mandalId,villageCode,villageName) Values(114,9,'KODIHALLI');
insert into village(mandalId,villageCode,villageName) Values(114,5,'MADHUDI');
insert into village(mandalId,villageCode,villageName) Values(114,4,'NARASAMBUDI');
insert into village(mandalId,villageCode,villageName) Values(114,6,'P.BYADIGERA');
insert into village(mandalId,villageCode,villageName) Values(114,1,'RAVUDI');


insert into village(mandalId,villageCode,villageName) Values(115,2,'BOMMAGONDANAHALLI');
insert into village(mandalId,villageCode,villageName) Values(115,7,'DODDERI');
insert into village(mandalId,villageCode,villageName) Values(115,6,'KAKI');
insert into village(mandalId,villageCode,villageName) Values(115,1,'M.RAYAPURAM');
insert into village(mandalId,villageCode,villageName) Values(115,5,'RATNAGIRI');
insert into village(mandalId,villageCode,villageName) Values(115,3,'ROLLA');
insert into village(mandalId,villageCode,villageName) Values(115,4,'TUBINAKUNTA');






insert into village(mandalId,villageCode,villageName) Values(116,5,'BANDREVU');
insert into village(mandalId,villageCode,villageName) Values(116,4,'DIGUVAPALLE');
insert into village(mandalId,villageCode,villageName) Values(116,6,'KALICHERLA');
insert into village(mandalId,villageCode,villageName) Values(116,3,'MUSALIKUNTA');
insert into village(mandalId,villageCode,villageName) Values(116,1,'PAPEPALLE');
insert into village(mandalId,villageCode,villageName) Values(116,2,'PEDDAMANDYAM');
insert into village(mandalId,villageCode,villageName) Values(116,7,'SIDDAVARAM');
insert into village(mandalId,villageCode,villageName) Values(116,8,'SIVAPURAM');
insert into village(mandalId,villageCode,villageName) Values(116,9,'VELIGALLU');



insert into village(mandalId,villageCode,villageName) Values(117,6,'DIGUVAPALEM');
insert into village(mandalId,villageCode,villageName) Values(117,9,'EDDULAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(117,4,'GANGIREDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(117,3,'GOPIDINNE');
insert into village(mandalId,villageCode,villageName) Values(117,7,'GUNDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(117,14,'KANNEMADUGU');
insert into village(mandalId,villageCode,villageName) Values(117,12,'KOSUVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(117,16,'KOTAKONDA');
insert into village(mandalId,villageCode,villageName) Values(117,2,'KOTALA');
insert into village(mandalId,villageCode,villageName) Values(117,5,'KOTLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(117,17,'KUKKARAJUPALLE');
insert into village(mandalId,villageCode,villageName) Values(117,13,'MARRIMAKULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(117,11,'PANCHALAMARRI');
insert into village(mandalId,villageCode,villageName) Values(117,15,'RENIMAKULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(117,8,'THAMBALLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(117,10,'YERRASANIPPALLE');
insert into village(mandalId,villageCode,villageName) Values(117,1,'ZUNJURUPENTA');



insert into village(mandalId,villageCode,villageName) Values(118,13,'BURAKAYALAKOTA');
insert into village(mandalId,villageCode,villageName) Values(118,1,'CHANNAPPAGARIPALLI');
insert into village(mandalId,villageCode,villageName) Values(118,10,'CHENNAIAHGARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(118,4,'COWDASAMURAM');
insert into village(mandalId,villageCode,villageName) Values(118,12,'DEVALCHERUVU');
insert into village(mandalId,villageCode,villageName) Values(118,17,'DEVARAPALLE');
insert into village(mandalId,villageCode,villageName) Values(118,2,'GUDUPALLE');
insert into village(mandalId,villageCode,villageName) Values(118,9,'KADIRINATHUNIKOTA');
insert into village(mandalId,villageCode,villageName) Values(118,3,'KALAVAPALLE');
insert into village(mandalId,villageCode,villageName) Values(118,16,'MADDINMAYANIPALLE');
insert into village(mandalId,villageCode,villageName) Values(118,8,'MARELLAGADDA');
insert into village(mandalId,villageCode,villageName) Values(118,6,'MOLAKALACHERUVU');
insert into village(mandalId,villageCode,villageName) Values(118,15,'NADIGADDATHIMMANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(118,7,'NAYANICHERUVUPALLE');
insert into village(mandalId,villageCode,villageName) Values(118,11,'PEDDAPALEM');
insert into village(mandalId,villageCode,villageName) Values(118,5,'SOMPALLE');
insert into village(mandalId,villageCode,villageName) Values(118,14,'VEPURIKOTA');




insert into village(mandalId,villageCode,villageName) Values(119,15,'AMALLABANDAKOTA');
insert into village(mandalId,villageCode,villageName) Values(119,13,'ANANTHAPURAM');
insert into village(mandalId,villageCode,villageName) Values(119,16,'BOORLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(119,19,'BUCHIPALLE');
insert into village(mandalId,villageCode,villageName) Values(119,14,'CHINNAPONGUPPALLE');
insert into village(mandalId,villageCode,villageName) Values(119,4,'JAGADAMVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(119,6,'KAMSALAVANDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(119,2,'KANDUKURU');
insert into village(mandalId,villageCode,villageName) Values(119,3,'KATNAGALLU');
insert into village(mandalId,villageCode,villageName) Values(119,9,'MADDAIAHGARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(119,8,'MADUMURU');
insert into village(mandalId,villageCode,villageName) Values(119,17,'MALLELA');
insert into village(mandalId,villageCode,villageName) Values(119,1,'PATTEMVANDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(119,10,'PEDDATHIPPASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(119,5,'PULIKALLU');
insert into village(mandalId,villageCode,villageName) Values(119,7,'RANGASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(119,18,'SAMPATHIKOTA');
insert into village(mandalId,villageCode,villageName) Values(119,12,'T.SADUM');
insert into village(mandalId,villageCode,villageName) Values(119,11,'THUMMARAKUNTA');



insert into village(mandalId,villageCode,villageName) Values(120,2,'B.KOTHAKOTA');
insert into village(mandalId,villageCode,villageName) Values(120,4,'BADIKAYALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(120,3,'BEERANGI');
insert into village(mandalId,villageCode,villageName) Values(120,6,'BOYYAPPAGARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(120,5,'GATTU');
insert into village(mandalId,villageCode,villageName) Values(120,8,'GOLLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(120,1,'GUMMASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(120,9,'KOTAVOORU');
insert into village(mandalId,villageCode,villageName) Values(120,7,'THUMMANAMGUTTA');



insert into village(mandalId,villageCode,villageName) Values(121,6,'ANGALLU');
insert into village(mandalId,villageCode,villageName) Values(121,7,'BRAHMANAVODDUPALLE');
insert into village(mandalId,villageCode,villageName) Values(121,4,'KURABALAKOTA');
insert into village(mandalId,villageCode,villageName) Values(121,3,'MATLIVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(121,2,'MUDIVEDU');
insert into village(mandalId,villageCode,villageName) Values(121,8,'MUTAKANAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(121,1,'PITCHALAVANDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(121,5,'THETTU');



insert into village(mandalId,villageCode,villageName) Values(122,11,'AMILEPALLE');
insert into village(mandalId,villageCode,villageName) Values(122,12,'ARIGALAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(122,3,'CHERLOPALLE');
insert into village(mandalId,villageCode,villageName) Values(122,6,'CHITTIBOYANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(122,7,'GURRAMKONDA');
insert into village(mandalId,villageCode,villageName) Values(122,9,'KHANDRIGA');
insert into village(mandalId,villageCode,villageName) Values(122,10,'MARRIMAKULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(122,17,'MARRIPADU');
insert into village(mandalId,villageCode,villageName) Values(122,4,'NADIMIKHANDRIGA');
insert into village(mandalId,villageCode,villageName) Values(122,15,'RAMAPURAM');
insert into village(mandalId,villageCode,villageName) Values(122,8,'SANGASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(122,5,'SARIMADUGU');
insert into village(mandalId,villageCode,villageName) Values(122,13,'SETLIVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(122,1,'T.PASALAVANDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(122,14,'THARIGONDA');
insert into village(mandalId,villageCode,villageName) Values(122,16,'THARIGONDARACHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(122,2,'YELLUTLA');



insert into village(mandalId,villageCode,villageName) Values(123,9,'DEVALAPALLE');
insert into village(mandalId,villageCode,villageName) Values(123,14,'GANGAPURAM');
insert into village(mandalId,villageCode,villageName) Values(123,11,'GUDIBANDA');
insert into village(mandalId,villageCode,villageName) Values(123,3,'KADIRAYACHERUVU');
insert into village(mandalId,villageCode,villageName) Values(123,15,'KALAKADA');
insert into village(mandalId,villageCode,villageName) Values(123,12,'KALAKADADODDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(123,13,'KALAKADAKOTHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(123,10,'KONA');
insert into village(mandalId,villageCode,villageName) Values(123,1,'MADINENIPALEM');
insert into village(mandalId,villageCode,villageName) Values(123,5,'MUDIAMVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(123,2,'NADMICHERLA');
insert into village(mandalId,villageCode,villageName) Values(123,6,'NAWABPET');
insert into village(mandalId,villageCode,villageName) Values(123,16,'POTHAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(123,8,'RATIGUNTAPALLE');
insert into village(mandalId,villageCode,villageName) Values(123,4,'YENUGONDAPALEM');
insert into village(mandalId,villageCode,villageName) Values(123,7,'YERRAKOTAPALLE');



insert into village(mandalId,villageCode,villageName) Values(124,2,'BOPPASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(124,3,'GALIVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(124,4,'GARNIMITTA');
insert into village(mandalId,villageCode,villageName) Values(124,17,'GORANTLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(124,21,'GYARAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(124,11,'HISSAPALLE');
insert into village(mandalId,villageCode,villageName) Values(124,7,'JILLELLAMANDA');
insert into village(mandalId,villageCode,villageName) Values(124,9,'KAMBHAMVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(124,16,'KASIREDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(124,15,'KETHAMREDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(124,5,'MADDIPATLAVANDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(124,12,'MAHALRAJUPALLE');
insert into village(mandalId,villageCode,villageName) Values(124,8,'MARELLA');
insert into village(mandalId,villageCode,villageName) Values(124,20,'MATAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(124,6,'NOOTHANAKALVA');
insert into village(mandalId,villageCode,villageName) Values(124,18,'SORAKAYALAPETA');
insert into village(mandalId,villageCode,villageName) Values(124,13,'THEETHAVAGUNTAPALLE');
insert into village(mandalId,villageCode,villageName) Values(124,10,'THIMMAPURAM');
insert into village(mandalId,villageCode,villageName) Values(124,1,'THUVVAPALLE');
insert into village(mandalId,villageCode,villageName) Values(124,14,'VAGALLA');
insert into village(mandalId,villageCode,villageName) Values(124,19,'YERLAMPALLE');



insert into village(mandalId,villageCode,villageName) Values(125,13,'YERPEDU');
insert into village(mandalId,villageCode,villageName) Values(125,4,'BODEVANDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(125,10,'CHINTHAKUNTA');
insert into village(mandalId,villageCode,villageName) Values(125,2,'ELLAMANDA');
insert into village(mandalId,villageCode,villageName) Values(125,9,'KAMALAYYAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(125,5,'NERABYLU');
insert into village(mandalId,villageCode,villageName) Values(125,6,'UDAYAMANIKYAM');
insert into village(mandalId,villageCode,villageName) Values(125,7,'UDAYAMANIKYAMAGRAHARAM');
insert into village(mandalId,villageCode,villageName) Values(125,1,'USTHIKAYALAPENTA');
insert into village(mandalId,villageCode,villageName) Values(125,3,'VENKATARAMAAGRAHARAM');
insert into village(mandalId,villageCode,villageName) Values(125,8,'YERRAVARIPALEM');


insert into village(mandalId,villageCode,villageName) Values(126,2,'AKKARAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(126,5,'CHENNAYYAGUNTA');
insert into village(mandalId,villageCode,villageName) Values(126,7,'KONKACHENNAIAHGUNTA');
insert into village(mandalId,villageCode,villageName) Values(126,4,'MANGALAM');
insert into village(mandalId,villageCode,villageName) Values(126,6,'SETTIPALLE');
insert into village(mandalId,villageCode,villageName) Values(126,3,'THIMMINAIDUPALEM');
insert into village(mandalId,villageCode,villageName) Values(126,1,'TIRUPATHI');



insert into village(mandalId,villageCode,villageName) Values(127,18,'ADUSUPALEM');
insert into village(mandalId,villageCode,villageName) Values(127,30,'AMMAVARIPATTEDA');
insert into village(mandalId,villageCode,villageName) Values(127,8,'ANAGUNTA');
insert into village(mandalId,villageCode,villageName) Values(127,13,'ANNASANIPALLE');
insert into village(mandalId,villageCode,villageName) Values(127,31,'ATHURU');
insert into village(mandalId,villageCode,villageName) Values(127,1,'BALUPALLE');
insert into village(mandalId,villageCode,villageName) Values(127,6,'DHARMAPURAM KHANDRIGA');
insert into village(mandalId,villageCode,villageName) Values(127,16,'ELAMANDYAM');
insert into village(mandalId,villageCode,villageName) Values(127,11,'ERRAGUNTA');
insert into village(mandalId,villageCode,villageName) Values(127,14,'ERRAMAREDDIPALEM');
insert into village(mandalId,villageCode,villageName) Values(127,24,'GAJULAMANDYAM');
insert into village(mandalId,villageCode,villageName) Values(127,21,'JEEPALEM');
insert into village(mandalId,villageCode,villageName) Values(127,3,'KARAKAMBADI');
insert into village(mandalId,villageCode,villageName) Values(127,17,'KOTHAPALEM');
insert into village(mandalId,villageCode,villageName) Values(127,26,'KOTRMANGALAM');
insert into village(mandalId,villageCode,villageName) Values(127,20,'KRISHNAIH KALVA');
insert into village(mandalId,villageCode,villageName) Values(127,4,'KRISHNAPURAM');
insert into village(mandalId,villageCode,villageName) Values(127,19,'KURUKALVA');
insert into village(mandalId,villageCode,villageName) Values(127,2,'MAMANDUR');
insert into village(mandalId,villageCode,villageName) Values(127,29,'MOLAGAMUDI');
insert into village(mandalId,villageCode,villageName) Values(127,22,'NALLAPALEM');
insert into village(mandalId,villageCode,villageName) Values(127,7,'R. MALLAVARAM');
insert into village(mandalId,villageCode,villageName) Values(127,10,'RENIGUNTA AGRAHARAM');
insert into village(mandalId,villageCode,villageName) Values(127,25,'SANJEEVARAYANIPATTEDA');
insert into village(mandalId,villageCode,villageName) Values(127,5,'SRINIVASAUDASIPURAM');
insert into village(mandalId,villageCode,villageName) Values(127,28,'SURAPPAKASAM');
insert into village(mandalId,villageCode,villageName) Values(127,27,'THANDLAMNGALAM');
insert into village(mandalId,villageCode,villageName) Values(127,23,'THATHAIAH KALVA');
insert into village(mandalId,villageCode,villageName) Values(127,15,'THUKIVAKAM');
insert into village(mandalId,villageCode,villageName) Values(127,9,'VEDULLACHERUVU');
insert into village(mandalId,villageCode,villageName) Values(127,12,'VENKATAPURAM');



insert into village(mandalId,villageCode,villageName) Values(128,24,'ARUGORLA KHANDRIGA');
insert into village(mandalId,villageCode,villageName) Values(128,28,'CHELLUR');
insert into village(mandalId,villageCode,villageName) Values(128,11,'CHINDEPALLE');
insert into village(mandalId,villageCode,villageName) Values(128,19,'CHINNAJIMEDU');
insert into village(mandalId,villageCode,villageName) Values(128,2,'CHINTALAPALEM');
insert into village(mandalId,villageCode,villageName) Values(128,7,'DURGIPERI');
insert into village(mandalId,villageCode,villageName) Values(128,34,'GUDIMALLAM');
insert into village(mandalId,villageCode,villageName) Values(128,17,'ISUKATHAGELI');
insert into village(mandalId,villageCode,villageName) Values(128,27,'KANDADU');
insert into village(mandalId,villageCode,villageName) Values(128,5,'KATRAKAYALAGUNTA');
insert into village(mandalId,villageCode,villageName) Values(128,20,'KOBAKA');
insert into village(mandalId,villageCode,villageName) Values(128,8,'KRISHNAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(128,25,'MADHAVAMALA');
insert into village(mandalId,villageCode,villageName) Values(128,26,'MAHANKALIDEVIPUTTUR');
insert into village(mandalId,villageCode,villageName) Values(128,21,'MANNASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(128,12,'MERLAPAKA');
insert into village(mandalId,villageCode,villageName) Values(128,23,'MODUGULAPALEM');
insert into village(mandalId,villageCode,villageName) Values(128,29,'MUNGALAPALEM');
insert into village(mandalId,villageCode,villageName) Values(128,22,'MUSALIPEDU');
insert into village(mandalId,villageCode,villageName) Values(128,9,'NACHANERI');
insert into village(mandalId,villageCode,villageName) Values(128,10,'NAGAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(128,4,'PAGALI');
insert into village(mandalId,villageCode,villageName) Values(128,1,'PALLAM');
insert into village(mandalId,villageCode,villageName) Values(128,3,'PANGURU');
insert into village(mandalId,villageCode,villageName) Values(128,18,'PATHAVEERAPURAM');
insert into village(mandalId,villageCode,villageName) Values(128,16,'PEDDANJIMEDU');
insert into village(mandalId,villageCode,villageName) Values(128,31,'PENNAGADAM');
insert into village(mandalId,villageCode,villageName) Values(128,32,'PENUMALLAM');
insert into village(mandalId,villageCode,villageName) Values(128,14,'SEETHARAMPET');
insert into village(mandalId,villageCode,villageName) Values(128,6,'SREENIVASAPURAM');
insert into village(mandalId,villageCode,villageName) Values(128,33,'VEDULLA CHERUVU');
insert into village(mandalId,villageCode,villageName) Values(128,15,'VENKATAPURAM');
insert into village(mandalId,villageCode,villageName) Values(128,30,'VIKRUTHAMALA');
insert into village(mandalId,villageCode,villageName) Values(128,13,'YERPEDU');



insert into village(mandalId,villageCode,villageName) Values(129,38,'AKKURTHY');
insert into village(mandalId,villageCode,villageName) Values(129,9,'AMMACHERUVU');
insert into village(mandalId,villageCode,villageName) Values(129,51,'AMMAPALEM');
insert into village(mandalId,villageCode,villageName) Values(129,35,'ANANTHAPADMANABHAPURAM');
insert into village(mandalId,villageCode,villageName) Values(129,49,'APPALAYAGUNTA');
insert into village(mandalId,villageCode,villageName) Values(129,47,'ARAVAKOTHURU');
insert into village(mandalId,villageCode,villageName) Values(129,19,'B.VENKATAPURAM');
insert into village(mandalId,villageCode,villageName) Values(129,12,'BHEEMAVARAM');
insert into village(mandalId,villageCode,villageName) Values(129,34,'BODAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(129,59,'BOKKASAM PALEM');
insert into village(mandalId,villageCode,villageName) Values(129,17,'BRAHMANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(129,58,'CHELLAPALEM');
insert into village(mandalId,villageCode,villageName) Values(129,50,'CHERLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(129,54,'CHERLOPALLE.VS.KANDRIGA');
insert into village(mandalId,villageCode,villageName) Values(129,41,'CHERUKULAPADU');
insert into village(mandalId,villageCode,villageName) Values(129,52,'CHUKKALANIDIGALLU');
insert into village(mandalId,villageCode,villageName) Values(129,61,'DIGUVAVEEDHI');
insert into village(mandalId,villageCode,villageName) Values(129,53,'EGUVAVEEDHI');
insert into village(mandalId,villageCode,villageName) Values(129,13,'EMPEDU');
insert into village(mandalId,villageCode,villageName) Values(129,33,'ERRAGUDIPADU');
insert into village(mandalId,villageCode,villageName) Values(129,18,'GOLLAPALLE @VENKAATAPUR');
insert into village(mandalId,villageCode,villageName) Values(129,4,'GOVINDARAOPALLE');
insert into village(mandalId,villageCode,villageName) Values(129,43,'GUNTAKINDAPALLE');
insert into village(mandalId,villageCode,villageName) Values(129,3,'INAGALUR');
insert into village(mandalId,villageCode,villageName) Values(129,6,'KALAVAGUNTA');
insert into village(mandalId,villageCode,villageName) Values(129,39,'KAMMAKOTHUR');
insert into village(mandalId,villageCode,villageName) Values(129,55,'KAPUGUNNERI');
insert into village(mandalId,villageCode,villageName) Values(129,24,'KONERU GUNTA');
insert into village(mandalId,villageCode,villageName) Values(129,1,'KOTHAPALLE CHINTHALA');
insert into village(mandalId,villageCode,villageName) Values(129,21,'KOTHURU @CHELLAMAMBAPUR');
insert into village(mandalId,villageCode,villageName) Values(129,23,'KUNTIPUDI');
insert into village(mandalId,villageCode,villageName) Values(129,32,'MADAMALA');
insert into village(mandalId,villageCode,villageName) Values(129,44,'MADDILEDU');
insert into village(mandalId,villageCode,villageName) Values(129,8,'MANGALAGUNTA');
insert into village(mandalId,villageCode,villageName) Values(129,30,'MANGALAPURI');
insert into village(mandalId,villageCode,villageName) Values(129,2,'MANNAVARAM');
insert into village(mandalId,villageCode,villageName) Values(129,56,'MARRIMAKULACHENU KHANDR');
insert into village(mandalId,villageCode,villageName) Values(129,16,'MELACHUR');
insert into village(mandalId,villageCode,villageName) Values(129,31,'MUCHIVOLU');
insert into village(mandalId,villageCode,villageName) Values(129,36,'MUDDDUMUDI');
insert into village(mandalId,villageCode,villageName) Values(129,20,'MURTHY PALEM');
insert into village(mandalId,villageCode,villageName) Values(129,42,'NARAYANAPURAM');
insert into village(mandalId,villageCode,villageName) Values(129,29,'OBULAYAPALLE');
insert into village(mandalId,villageCode,villageName) Values(129,46,'PANAGALLU');
insert into village(mandalId,villageCode,villageName) Values(129,15,'PAPANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(129,14,'PATHAGUNTA');
insert into village(mandalId,villageCode,villageName) Values(129,40,'PENUBAKA');
insert into village(mandalId,villageCode,villageName) Values(129,11,'POLI');
insert into village(mandalId,villageCode,villageName) Values(129,63,'PULLAREDDI KHANDRIGA');
insert into village(mandalId,villageCode,villageName) Values(129,57,'RACHAGUNNERI');
insert into village(mandalId,villageCode,villageName) Values(129,65,'RAMALINGA PURAM');
insert into village(mandalId,villageCode,villageName) Values(129,22,'RAMANUJA PALLE');
insert into village(mandalId,villageCode,villageName) Values(129,67,'RAMAPURAM');
insert into village(mandalId,villageCode,villageName) Values(129,28,'REDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(129,66,'SAHASRALINGESHWARA PURA');
insert into village(mandalId,villageCode,villageName) Values(129,48,'SRIKALAHASTI');
insert into village(mandalId,villageCode,villageName) Values(129,60,'SUBBANAIDU KHANDRIGA');
insert into village(mandalId,villageCode,villageName) Values(129,62,'THONDAMANADU');
insert into village(mandalId,villageCode,villageName) Values(129,37,'UDAMALAPADU');
insert into village(mandalId,villageCode,villageName) Values(129,45,'URANDURU');
insert into village(mandalId,villageCode,villageName) Values(129,25,'VAGAVEDU');
insert into village(mandalId,villageCode,villageName) Values(129,10,'VAMPALLE');
insert into village(mandalId,villageCode,villageName) Values(129,64,'VEDAM');
insert into village(mandalId,villageCode,villageName) Values(129,5,'VELAMPADU');
insert into village(mandalId,villageCode,villageName) Values(129,27,'VELAVEDU');
insert into village(mandalId,villageCode,villageName) Values(129,26,'VENGALAM PALLE @ ENDRAP');
insert into village(mandalId,villageCode,villageName) Values(129,7,'YARLAPUDI');


insert into village(mandalId,villageCode,villageName) Values(130,25,'BASAVAIAHPALEM');
insert into village(mandalId,villageCode,villageName) Values(130,41,'BONUPALLE');
insert into village(mandalId,villageCode,villageName) Values(130,5,'CHEMURU');
insert into village(mandalId,villageCode,villageName) Values(130,33,'CHERUKURAGAPPA NAIDU KH');
insert into village(mandalId,villageCode,villageName) Values(130,23,'CHINNA SINGAMALA');
insert into village(mandalId,villageCode,villageName) Values(130,38,'CHITTATHUR');
insert into village(mandalId,villageCode,villageName) Values(130,3,'CHIYYAVARAM');
insert into village(mandalId,villageCode,villageName) Values(130,14,'CHODAVARAM');
insert into village(mandalId,villageCode,villageName) Values(130,10,'DAINEDU');
insert into village(mandalId,villageCode,villageName) Values(130,37,'DONGALAMUDUR');
insert into village(mandalId,villageCode,villageName) Values(130,34,'EDULAGUNTA @ C.VARI KHA');
insert into village(mandalId,villageCode,villageName) Values(130,1,'GOTTIPUDI');
insert into village(mandalId,villageCode,villageName) Values(130,31,'GOWDAMALA');
insert into village(mandalId,villageCode,villageName) Values(130,29,'GUMMADIGUNTA');
insert into village(mandalId,villageCode,villageName) Values(130,16,'GUNDELIGUNTA');
insert into village(mandalId,villageCode,villageName) Values(130,21,'GURUKULAPALEM');
insert into village(mandalId,villageCode,villageName) Values(130,19,'ILAGANUS');
insert into village(mandalId,villageCode,villageName) Values(130,27,'KALLIPUDI');
insert into village(mandalId,villageCode,villageName) Values(130,40,'KANCHANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(130,7,'KASARAM');
insert into village(mandalId,villageCode,villageName) Values(130,20,'KONATHANERI');
insert into village(mandalId,villageCode,villageName) Values(130,6,'KONDACHENU KHANDRIGA');
insert into village(mandalId,villageCode,villageName) Values(130,12,'KONNALI');
insert into village(mandalId,villageCode,villageName) Values(130,8,'MAMIDIGUNTA');
insert into village(mandalId,villageCode,villageName) Values(130,24,'PEDDA KANNALI');
insert into village(mandalId,villageCode,villageName) Values(130,13,'PEDDAGUNTA AGRAHARAM');
insert into village(mandalId,villageCode,villageName) Values(130,11,'PEDDAKANAPARTHI');
insert into village(mandalId,villageCode,villageName) Values(130,18,'PENNALAPADU');
insert into village(mandalId,villageCode,villageName) Values(130,9,'PILLAMEDU');
insert into village(mandalId,villageCode,villageName) Values(130,22,'POYYA');
insert into village(mandalId,villageCode,villageName) Values(130,17,'PUDI');
insert into village(mandalId,villageCode,villageName) Values(130,2,'RAMBHATLAPALLI');
insert into village(mandalId,villageCode,villageName) Values(130,30,'ROUTHUSURAMALA');
insert into village(mandalId,villageCode,villageName) Values(130,26,'SAMBAIAHPALEM');
insert into village(mandalId,villageCode,villageName) Values(130,4,'SIDDIGUNTA');
insert into village(mandalId,villageCode,villageName) Values(130,36,'SIVANADHAPALEM');
insert into village(mandalId,villageCode,villageName) Values(130,39,'SRIKRISHNAPURAM');
insert into village(mandalId,villageCode,villageName) Values(130,28,'TATIPARTHY');
insert into village(mandalId,villageCode,villageName) Values(130,32,'THANGELLAPALEM');
insert into village(mandalId,villageCode,villageName) Values(130,35,'THOTTAMBEDU');
insert into village(mandalId,villageCode,villageName) Values(130,15,'VIRUPAKSHAPURAM');



insert into village(mandalId,villageCode,villageName) Values(131,6,'ALATHUR');
insert into village(mandalId,villageCode,villageName) Values(131,5,'BHAVANISANKARAPURAM');
insert into village(mandalId,villageCode,villageName) Values(131,1,'CHELLAMAMBAPURAM');
insert into village(mandalId,villageCode,villageName) Values(131,9,'CHINNAYYAGUNTA');
insert into village(mandalId,villageCode,villageName) Values(131,16,'GAJULAPELLURE');
insert into village(mandalId,villageCode,villageName) Values(131,22,'KALLIVETTU');
insert into village(mandalId,villageCode,villageName) Values(131,15,'KANAMANAMBEDU');
insert into village(mandalId,villageCode,villageName) Values(131,19,'KANCHANAPUTTUR');
insert into village(mandalId,villageCode,villageName) Values(131,13,'KATURU');
insert into village(mandalId,villageCode,villageName) Values(131,4,'KOTHAPALEM');
insert into village(mandalId,villageCode,villageName) Values(131,18,'KUKKAMBAKAM');
insert into village(mandalId,villageCode,villageName) Values(131,23,'KUMARA VENKATAPURAM');
insert into village(mandalId,villageCode,villageName) Values(131,17,'NEERPAKOTA');
insert into village(mandalId,villageCode,villageName) Values(131,21,'NELAVOY');
insert into village(mandalId,villageCode,villageName) Values(131,12,'PALLAMALA');
insert into village(mandalId,villageCode,villageName) Values(131,3,'PARLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(131,10,'PEDDAPALAVEDU');
insert into village(mandalId,villageCode,villageName) Values(131,7,'PUTTERI');
insert into village(mandalId,villageCode,villageName) Values(131,24,'TALARIVETTU');
insert into village(mandalId,villageCode,villageName) Values(131,14,'THANGELLAPURAM');
insert into village(mandalId,villageCode,villageName) Values(131,8,'THERRIPADU');
insert into village(mandalId,villageCode,villageName) Values(131,2,'THIMMABHUPALAPURAM');
insert into village(mandalId,villageCode,villageName) Values(131,11,'VIJAYAGOPALAPURAM');
insert into village(mandalId,villageCode,villageName) Values(131,20,'WEST WARATHUR');



insert into village(mandalId,villageCode,villageName) Values(132,2,'AMBUR');
insert into village(mandalId,villageCode,villageName) Values(132,19,'ARUDURU');
insert into village(mandalId,villageCode,villageName) Values(132,1,'AYYAVARIPALEM');
insert into village(mandalId,villageCode,villageName) Values(132,10,'CHAVA LINGAMA NAIDU PAL');
insert into village(mandalId,villageCode,villageName) Values(132,18,'CHEDULLAPAKAM');
insert into village(mandalId,villageCode,villageName) Values(132,28,'CHILAMATHUR');
insert into village(mandalId,villageCode,villageName) Values(132,25,'CHINNAPANDUR');
insert into village(mandalId,villageCode,villageName) Values(132,7,'GUDALAVARIPALEM');
insert into village(mandalId,villageCode,villageName) Values(132,6,'ISUKAPALEM');
insert into village(mandalId,villageCode,villageName) Values(132,14,'KADURU');
insert into village(mandalId,villageCode,villageName) Values(132,12,'KAMBAKAM');
insert into village(mandalId,villageCode,villageName) Values(132,16,'KAREPAKAM');
insert into village(mandalId,villageCode,villageName) Values(132,8,'KOLATHUR');
insert into village(mandalId,villageCode,villageName) Values(132,20,'KOVURPADU');
insert into village(mandalId,villageCode,villageName) Values(132,11,'KUDINJALAM');
insert into village(mandalId,villageCode,villageName) Values(132,4,'KUVVAKOLLI');
insert into village(mandalId,villageCode,villageName) Values(132,9,'MARADAWADA');
insert into village(mandalId,villageCode,villageName) Values(132,27,'MOPORAPALLE');
insert into village(mandalId,villageCode,villageName) Values(132,15,'NELLATUR');
insert into village(mandalId,villageCode,villageName) Values(132,23,'PADIRIKUPPAM');
insert into village(mandalId,villageCode,villageName) Values(132,22,'PANDURU');
insert into village(mandalId,villageCode,villageName) Values(132,26,'RACHERLA');
insert into village(mandalId,villageCode,villageName) Values(132,3,'SANTHAVELLORE');
insert into village(mandalId,villageCode,villageName) Values(132,5,'SATHAMBEDU');
insert into village(mandalId,villageCode,villageName) Values(132,30,'SIDDAMA AGRAHARAM');
insert into village(mandalId,villageCode,villageName) Values(132,21,'THONDAMBATTU');
insert into village(mandalId,villageCode,villageName) Values(132,29,'THONDURU');
insert into village(mandalId,villageCode,villageName) Values(132,13,'VARADAIAHPALEM');
insert into village(mandalId,villageCode,villageName) Values(132,17,'VITTAIAH PALEM');
insert into village(mandalId,villageCode,villageName) Values(132,24,'YANADIVETTU');


insert into village(mandalId,villageCode,villageName) Values(133,28,'AMBAKAM');
insert into village(mandalId,villageCode,villageName) Values(133,9,'APPAIAHPALEM');
insert into village(mandalId,villageCode,villageName) Values(133,11,'AROOR');
insert into village(mandalId,villageCode,villageName) Values(133,8,'CHEMGAMBAKAM');
insert into village(mandalId,villageCode,villageName) Values(133,6,'CHERIVI');
insert into village(mandalId,villageCode,villageName) Values(133,15,'CHINNA EETIVAKAM');
insert into village(mandalId,villageCode,villageName) Values(133,18,'DALAVAI AGRAHARAM');
insert into village(mandalId,villageCode,villageName) Values(133,31,'DASUKUPPAM');
insert into village(mandalId,villageCode,villageName) Values(133,7,'GOLLAVARIPALEM');
insert into village(mandalId,villageCode,villageName) Values(133,12,'IRUGULAM');
insert into village(mandalId,villageCode,villageName) Values(133,25,'KADIRVEDU');
insert into village(mandalId,villageCode,villageName) Values(133,22,'KANNAVARAM');
insert into village(mandalId,villageCode,villageName) Values(133,13,'KOLLADAM');
insert into village(mandalId,villageCode,villageName) Values(133,16,'KOTHAMARIKUPPAM');
insert into village(mandalId,villageCode,villageName) Values(133,21,'MADAMAMBEDU');
insert into village(mandalId,villageCode,villageName) Values(133,26,'MADANANJERI');
insert into village(mandalId,villageCode,villageName) Values(133,5,'MADANAPALEM');
insert into village(mandalId,villageCode,villageName) Values(133,10,'MALLAVARIPALEM');
insert into village(mandalId,villageCode,villageName) Values(133,17,'NARASARAJU AGRAHARAM');
insert into village(mandalId,villageCode,villageName) Values(133,14,'PEDDA EETIVAKAM');
insert into village(mandalId,villageCode,villageName) Values(133,29,'PEDUKUPPAM');
insert into village(mandalId,villageCode,villageName) Values(133,23,'PERADAM');
insert into village(mandalId,villageCode,villageName) Values(133,1,'PRAVELAVARMESWARAPURAM');
insert into village(mandalId,villageCode,villageName) Values(133,2,'RAJAGOPALAPURAM');
insert into village(mandalId,villageCode,villageName) Values(133,4,'RALLAKUPPAM');
insert into village(mandalId,villageCode,villageName) Values(133,19,'SATYAVEDU');
insert into village(mandalId,villageCode,villageName) Values(133,30,'SENNERI');
insert into village(mandalId,villageCode,villageName) Values(133,24,'SIRUNAMBUDURU');
insert into village(mandalId,villageCode,villageName) Values(133,27,'THONDUKULI');
insert into village(mandalId,villageCode,villageName) Values(133,3,'VANELLURU');
insert into village(mandalId,villageCode,villageName) Values(133,20,'VENKATARAJULA KHANDRIGA');


insert into village(mandalId,villageCode,villageName) Values(134,10,'BAITAKODIAMBEDU');
insert into village(mandalId,villageCode,villageName) Values(134,2,'BEERAKUPPAM');
insert into village(mandalId,villageCode,villageName) Values(134,3,'KADIVEDU');
insert into village(mandalId,villageCode,villageName) Values(134,7,'KALANJERI');
insert into village(mandalId,villageCode,villageName) Values(134,11,'KARANI');
insert into village(mandalId,villageCode,villageName) Values(134,6,'KRISHNAPURAM');
insert into village(mandalId,villageCode,villageName) Values(134,8,'NAGALAPURAM');
insert into village(mandalId,villageCode,villageName) Values(134,4,'SADASIVA SANKARAPURAM');
insert into village(mandalId,villageCode,villageName) Values(134,12,'SURUTUPALLE');
insert into village(mandalId,villageCode,villageName) Values(134,1,'TRIPURANTHAKA PURAMKOTA');
insert into village(mandalId,villageCode,villageName) Values(134,5,'VELLORE');
insert into village(mandalId,villageCode,villageName) Values(134,9,'VEMBAKAM');



insert into village(mandalId,villageCode,villageName) Values(135,7,'APPAMBATTU');
insert into village(mandalId,villageCode,villageName) Values(135,16,'CHILAMATHUR @ BANGALA');
insert into village(mandalId,villageCode,villageName) Values(135,14,'KARURU @ KRISHNAGIRI');
insert into village(mandalId,villageCode,villageName) Values(135,2,'KEELAPUDI');
insert into village(mandalId,villageCode,villageName) Values(135,1,'MUDIYURU');
insert into village(mandalId,villageCode,villageName) Values(135,9,'NEERVOY');
insert into village(mandalId,villageCode,villageName) Values(135,3,'PICHATURU');
insert into village(mandalId,villageCode,villageName) Values(135,15,'PULIGUNDRAM');
insert into village(mandalId,villageCode,villageName) Values(135,13,'PULIPADU @ GOVERDHANAGI');
insert into village(mandalId,villageCode,villageName) Values(135,6,'RAJANAGARAM');
insert into village(mandalId,villageCode,villageName) Values(135,5,'RAMAGIRI');
insert into village(mandalId,villageCode,villageName) Values(135,11,'RAMAPURAM');
insert into village(mandalId,villageCode,villageName) Values(135,4,'REPPALAPATTU');
insert into village(mandalId,villageCode,villageName) Values(135,17,'SHAMSHEER BAHADURPET');
insert into village(mandalId,villageCode,villageName) Values(135,12,'SIVAGIRI');
insert into village(mandalId,villageCode,villageName) Values(135,8,'VELURU');
insert into village(mandalId,villageCode,villageName) Values(135,10,'VENGALATHURU');




insert into village(mandalId,villageCode,villageName) Values(136,1,'ALAPAKAM');
insert into village(mandalId,villageCode,villageName) Values(136,2,'BUCHIVANATHAM');
insert into village(mandalId,villageCode,villageName) Values(136,3,'ELLA SAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(136,7,'GANGAMAMBA PURAM');
insert into village(mandalId,villageCode,villageName) Values(136,14,'ILLATHURU');
insert into village(mandalId,villageCode,villageName) Values(136,12,'JAGANNADHAPURAM');
insert into village(mandalId,villageCode,villageName) Values(136,4,'KALIAMBAKAM');
insert into village(mandalId,villageCode,villageName) Values(136,8,'KALIKAPURAM');
insert into village(mandalId,villageCode,villageName) Values(136,16,'KOSALA NAGARAM');
insert into village(mandalId,villageCode,villageName) Values(136,10,'KOTHURU VENKATAPURAM');
insert into village(mandalId,villageCode,villageName) Values(136,17,'MAHARAJA PURAM');
insert into village(mandalId,villageCode,villageName) Values(136,11,'MANGALAM');
insert into village(mandalId,villageCode,villageName) Values(136,6,'PANNURU');
insert into village(mandalId,villageCode,villageName) Values(136,15,'PATHA ARCOT');
insert into village(mandalId,villageCode,villageName) Values(136,5,'SAMI REDDI KHANDRIGA');
insert into village(mandalId,villageCode,villageName) Values(136,18,'SRIHARIPURAM');
insert into village(mandalId,villageCode,villageName) Values(136,13,'VIJAYA PURAM');
insert into village(mandalId,villageCode,villageName) Values(136,9,'VIJAYA RAGHAVAPURAM');




insert into village(mandalId,villageCode,villageName) Values(137,13,'AGARAM');
insert into village(mandalId,villageCode,villageName) Values(137,7,'ARURU');
insert into village(mandalId,villageCode,villageName) Values(137,14,'ATHURU');
insert into village(mandalId,villageCode,villageName) Values(137,6,'CHAVARAMBAKAM');
insert into village(mandalId,villageCode,villageName) Values(137,5,'ELAKATUR');
insert into village(mandalId,villageCode,villageName) Values(137,8,'IRUGUVAI');
insert into village(mandalId,villageCode,villageName) Values(137,11,'KACHARAVEDU');
insert into village(mandalId,villageCode,villageName) Values(137,1,'KAVANURU');
insert into village(mandalId,villageCode,villageName) Values(137,3,'KOPPEDU');
insert into village(mandalId,villageCode,villageName) Values(137,9,'KUNAMA RAJU PALEM');
insert into village(mandalId,villageCode,villageName) Values(137,10,'NETTERI');
insert into village(mandalId,villageCode,villageName) Values(137,12,'NINDRA');
insert into village(mandalId,villageCode,villageName) Values(137,15,'PADIRI');
insert into village(mandalId,villageCode,villageName) Values(137,4,'SAMAYAPURAM');
insert into village(mandalId,villageCode,villageName) Values(137,2,'SRI RAMAPURAM');


insert into village(mandalId,villageCode,villageName) Values(138,8,'ADARAM');
insert into village(mandalId,villageCode,villageName) Values(138,21,'ADAVARAM');
insert into village(mandalId,villageCode,villageName) Values(138,4,'ANGERI CHERUVU');
insert into village(mandalId,villageCode,villageName) Values(138,6,'ANJURU');
insert into village(mandalId,villageCode,villageName) Values(138,34,'ARAI');
insert into village(mandalId,villageCode,villageName) Values(138,16,'BAKKAPOTUGUNTA');
insert into village(mandalId,villageCode,villageName) Values(138,11,'BRAHMANA CHERUVU');
insert into village(mandalId,villageCode,villageName) Values(138,12,'BRAHMANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(138,7,'CHELLAMAMBA PURAM');
insert into village(mandalId,villageCode,villageName) Values(138,36,'DIGUVAPUTHURU');
insert into village(mandalId,villageCode,villageName) Values(138,37,'EGUVAPUTTURU');
insert into village(mandalId,villageCode,villageName) Values(138,1,'GNANAMAMBA PURAM @ P.KH');
insert into village(mandalId,villageCode,villageName) Values(138,5,'GUNTIPEDU');
insert into village(mandalId,villageCode,villageName) Values(138,22,'KALATHURU');
insert into village(mandalId,villageCode,villageName) Values(138,26,'KANCHANACHENGANNA KHAND');
insert into village(mandalId,villageCode,villageName) Values(138,17,'KANDLURU');
insert into village(mandalId,villageCode,villageName) Values(138,24,'KATRAPALLE');
insert into village(mandalId,villageCode,villageName) Values(138,2,'KOVANUR');
insert into village(mandalId,villageCode,villageName) Values(138,23,'KUMARA VENKATA BHUPALAP');
insert into village(mandalId,villageCode,villageName) Values(138,20,'MATTAMANAPATHATTU R.KHA');
insert into village(mandalId,villageCode,villageName) Values(138,39,'MIDDI KHANDRIGA');
insert into village(mandalId,villageCode,villageName) Values(138,28,'OLLURU');
insert into village(mandalId,villageCode,villageName) Values(138,29,'PATHAPALEM');
insert into village(mandalId,villageCode,villageName) Values(138,14,'PERINDESAM');
insert into village(mandalId,villageCode,villageName) Values(138,18,'PUDI@CHENNAKESAVAPURAM');
insert into village(mandalId,villageCode,villageName) Values(138,13,'RAGIGUNTA');
insert into village(mandalId,villageCode,villageName) Values(138,19,'RANGAIAHGUNTA');
insert into village(mandalId,villageCode,villageName) Values(138,27,'RAYAPEDU');
insert into village(mandalId,villageCode,villageName) Values(138,31,'SADASIVA PURAM');
insert into village(mandalId,villageCode,villageName) Values(138,38,'SHAKTHI GANESWARAPURAM');
insert into village(mandalId,villageCode,villageName) Values(138,30,'SIDDAMANAIDU KHANDRIGA');
insert into village(mandalId,villageCode,villageName) Values(138,33,'SRINIVASAPURAM');
insert into village(mandalId,villageCode,villageName) Values(138,25,'SUBRAMANYA PURAM');
insert into village(mandalId,villageCode,villageName) Values(138,10,'SURAMALA');
insert into village(mandalId,villageCode,villageName) Values(138,35,'SURYANARAYANA PURAM');
insert into village(mandalId,villageCode,villageName) Values(138,3,'THIMMASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(138,15,'THIMMINAIDUGUNTA');
insert into village(mandalId,villageCode,villageName) Values(138,32,'VAGATHURU');
insert into village(mandalId,villageCode,villageName) Values(138,9,'VENKATAPURAM @ G.KHANDR');


insert into village(mandalId,villageCode,villageName) Values(139,14,'ARANYAM KHANDRIGA');
insert into village(mandalId,villageCode,villageName) Values(139,9,'BHEEMU NICHERUVU');
insert into village(mandalId,villageCode,villageName) Values(139,13,'BOPPA RAJU PALEM');
insert into village(mandalId,villageCode,villageName) Values(139,11,'DIGUVAANAKAM PALEM');
insert into village(mandalId,villageCode,villageName) Values(139,3,'ERIKAMBATTU');
insert into village(mandalId,villageCode,villageName) Values(139,10,'IPPPAM THANGAL');
insert into village(mandalId,villageCode,villageName) Values(139,2,'KALYANAPURAM');
insert into village(mandalId,villageCode,villageName) Values(139,12,'KASIMMITTA');
insert into village(mandalId,villageCode,villageName) Values(139,7,'KEELAGARAM');
insert into village(mandalId,villageCode,villageName) Values(139,1,'KONDALA CHERUVU');
insert into village(mandalId,villageCode,villageName) Values(139,5,'NARAYANAVANAM');
insert into village(mandalId,villageCode,villageName) Values(139,16,'PALAMANGALAM');
insert into village(mandalId,villageCode,villageName) Values(139,8,'SAMUDAYAM');
insert into village(mandalId,villageCode,villageName) Values(139,4,'THIRUVATYAM');
insert into village(mandalId,villageCode,villageName) Values(139,15,'THUMBURU');
insert into village(mandalId,villageCode,villageName) Values(139,6,'VENKATA KRISHNA PALEM');


insert into village(mandalId,villageCode,villageName) Values(140,21,'ALIMELU MANGA PURAM');
insert into village(mandalId,villageCode,villageName) Values(140,17,'AYYANNAGARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(140,5,'K.G.KHANDRIGA');
insert into village(mandalId,villageCode,villageName) Values(140,4,'KADIRIMANGALAM');
insert into village(mandalId,villageCode,villageName) Values(140,7,'KALLURU');
insert into village(mandalId,villageCode,villageName) Values(140,3,'KAYAM');
insert into village(mandalId,villageCode,villageName) Values(140,1,'PACHIKALVA');
insert into village(mandalId,villageCode,villageName) Values(140,10,'PADIREDU');
insert into village(mandalId,villageCode,villageName) Values(140,11,'PADIREDU ARANYAM');
insert into village(mandalId,villageCode,villageName) Values(140,2,'PATHIPUTHURU');
insert into village(mandalId,villageCode,villageName) Values(140,14,'PUDI');
insert into village(mandalId,villageCode,villageName) Values(140,13,'RAMASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(140,19,'S.V. PURAM');
insert into village(mandalId,villageCode,villageName) Values(140,20,'SEETHAMMA AGRAHARAM');
insert into village(mandalId,villageCode,villageName) Values(140,18,'SEETHARAMA PURAM');
insert into village(mandalId,villageCode,villageName) Values(140,22,'SRI BOMMARAJU PURAM');
insert into village(mandalId,villageCode,villageName) Values(140,9,'SRINIVASAPURAM');
insert into village(mandalId,villageCode,villageName) Values(140,6,'T.C.AGRAJARAM');
insert into village(mandalId,villageCode,villageName) Values(140,8,'THATNERI');
insert into village(mandalId,villageCode,villageName) Values(140,16,'THIRUMANDYAM');
insert into village(mandalId,villageCode,villageName) Values(140,12,'VADAMALA');
insert into village(mandalId,villageCode,villageName) Values(140,15,'VEMAPURAM');


insert into village(mandalId,villageCode,villageName) Values(141,13,'AVILALA');
insert into village(mandalId,villageCode,villageName) Values(141,18,'BRAHMANA PATTU');
insert into village(mandalId,villageCode,villageName) Values(141,1,'CHERLOPALLE');
insert into village(mandalId,villageCode,villageName) Values(141,10,'CHIGURUWADA NORTH KHAND');
insert into village(mandalId,villageCode,villageName) Values(141,28,'CHIGURUWADA SOUTH KANDR');
insert into village(mandalId,villageCode,villageName) Values(141,15,'DAMINEDU');
insert into village(mandalId,villageCode,villageName) Values(141,29,'DURGA SAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(141,7,'GOLLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(141,3,'KALUR');
insert into village(mandalId,villageCode,villageName) Values(141,17,'KOTHUR');
insert into village(mandalId,villageCode,villageName) Values(141,24,'KUNTRAPAKAM');
insert into village(mandalId,villageCode,villageName) Values(141,26,'KUPU CHANDRA PETA');
insert into village(mandalId,villageCode,villageName) Values(141,11,'MALLAM GUNTA');
insert into village(mandalId,villageCode,villageName) Values(141,4,'MALLAVARAM');
insert into village(mandalId,villageCode,villageName) Values(141,22,'MUNDLAPUDI');
insert into village(mandalId,villageCode,villageName) Values(141,25,'NALLA MANI KALVA');
insert into village(mandalId,villageCode,villageName) Values(141,16,'PADI');
insert into village(mandalId,villageCode,villageName) Values(141,5,'PAIDI PALLE');
insert into village(mandalId,villageCode,villageName) Values(141,19,'PANAKAM');
insert into village(mandalId,villageCode,villageName) Values(141,6,'PATHA KALVA');
insert into village(mandalId,villageCode,villageName) Values(141,8,'PERUR');
insert into village(mandalId,villageCode,villageName) Values(141,2,'PUDIPATLA');
insert into village(mandalId,villageCode,villageName) Values(141,27,'RAMANUJA PALLE');
insert into village(mandalId,villageCode,villageName) Values(141,23,'THANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(141,9,'THUMMALA GUNTA');
insert into village(mandalId,villageCode,villageName) Values(141,14,'TIRU CHANUR');
insert into village(mandalId,villageCode,villageName) Values(141,12,'VEDANTHA PURAM');
insert into village(mandalId,villageCode,villageName) Values(141,20,'VEMUR');
insert into village(mandalId,villageCode,villageName) Values(141,21,'YOGI MALLAVARAM');


insert into village(mandalId,villageCode,villageName) Values(142,13,'ANUPALLE');
insert into village(mandalId,villageCode,villageName) Values(142,7,'BRAHMANAKALVA');
insert into village(mandalId,villageCode,villageName) Values(142,15,'CHITATHUR KALE PALLE');
insert into village(mandalId,villageCode,villageName) Values(142,1,'CHUTTAGUNTA RAMAPURAM');
insert into village(mandalId,villageCode,villageName) Values(142,10,'GANGIREDDI PALLE');
insert into village(mandalId,villageCode,villageName) Values(142,9,'KAMMA PALLE');
insert into village(mandalId,villageCode,villageName) Values(142,4,'KATTAKINDA VENKATAPURAM');
insert into village(mandalId,villageCode,villageName) Values(142,17,'KUPPAMBADURU');
insert into village(mandalId,villageCode,villageName) Values(142,3,'NADAVALURU');
insert into village(mandalId,villageCode,villageName) Values(142,2,'NENNURU');
insert into village(mandalId,villageCode,villageName) Values(142,14,'NETHA KUPPAM');
insert into village(mandalId,villageCode,villageName) Values(142,12,'P.VENKATESHWARA PURAM');
insert into village(mandalId,villageCode,villageName) Values(142,8,'RAVILLAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(142,16,'RAYAL CHERUVU');
insert into village(mandalId,villageCode,villageName) Values(142,11,'SANJEEVA RAYA PURAM');
insert into village(mandalId,villageCode,villageName) Values(142,5,'SEVOIVAKALVA');
insert into village(mandalId,villageCode,villageName) Values(142,6,'SORAKAYALAPALEM');



insert into village(mandalId,villageCode,villageName) Values(143,18,'AGARALA');
insert into village(mandalId,villageCode,villageName) Values(143,6,'ARE  PALLE @ A.RANGAMPET');
insert into village(mandalId,villageCode,villageName) Values(143,1,'BHEEMAVARAM');
insert into village(mandalId,villageCode,villageName) Values(143,17,'CHANDRAGIRI');
insert into village(mandalId,villageCode,villageName) Values(143,3,'CHINNA RAMA PURAM');
insert into village(mandalId,villageCode,villageName) Values(143,15,'CHINTAGUNTA');
insert into village(mandalId,villageCode,villageName) Values(143,16,'DORNAKAMBALA');
insert into village(mandalId,villageCode,villageName) Values(143,20,'ITHEPALLE');
insert into village(mandalId,villageCode,villageName) Values(143,22,'KALROADPALLE');
insert into village(mandalId,villageCode,villageName) Values(143,2,'KONDREDDY KHANDRIGA');
insert into village(mandalId,villageCode,villageName) Values(143,8,'KOTALA');
insert into village(mandalId,villageCode,villageName) Values(143,19,'MAMANDUR');
insert into village(mandalId,villageCode,villageName) Values(143,12,'MITTAPALEM');
insert into village(mandalId,villageCode,villageName) Values(143,21,'MUNGILIPATTUKOTHAPALLE');
insert into village(mandalId,villageCode,villageName) Values(143,7,'NAGAPATLA');
insert into village(mandalId,villageCode,villageName) Values(143,10,'NARASINGA PURAM');
insert into village(mandalId,villageCode,villageName) Values(143,23,'PANAPAKAM');
insert into village(mandalId,villageCode,villageName) Values(143,5,'PULLAIAHGARI PALLE');
insert into village(mandalId,villageCode,villageName) Values(143,9,'RAMIREDDI PALLE');
insert into village(mandalId,villageCode,villageName) Values(143,11,'REDDIVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(143,14,'SANAMBATLA');
insert into village(mandalId,villageCode,villageName) Values(143,4,'SESHA PURAM');
insert into village(mandalId,villageCode,villageName) Values(143,13,'THONDAWADA');


insert into village(mandalId,villageCode,villageName) Values(144,7,'BHAKARAPET');
insert into village(mandalId,villageCode,villageName) Values(144,4,'CHATTEVARIPALEM');
insert into village(mandalId,villageCode,villageName) Values(144,3,'CHINNAGOTTI GALLU');
insert into village(mandalId,villageCode,villageName) Values(144,5,'CHITTECHERLA');
insert into village(mandalId,villageCode,villageName) Values(144,6,'DEVARAKONDA');
insert into village(mandalId,villageCode,villageName) Values(144,8,'DIGUVURU');
insert into village(mandalId,villageCode,villageName) Values(144,1,'KOTABYLU');
insert into village(mandalId,villageCode,villageName) Values(144,2,'RANGANNAGARI GADDA');
insert into village(mandalId,villageCode,villageName) Values(144,9,'THIPPIREDDIGARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(144,10,'YEGUVURU');


insert into village(mandalId,villageCode,villageName) Values(145,5,'BANDAKINDAPALLE');
insert into village(mandalId,villageCode,villageName) Values(145,7,'BOMMAIAHGARI PALLE');
insert into village(mandalId,villageCode,villageName) Values(145,1,'GANUGACHINTHA');
insert into village(mandalId,villageCode,villageName) Values(145,3,'MOTUMALLELA');
insert into village(mandalId,villageCode,villageName) Values(145,6,'PEDDAGOTTIGALLU');
insert into village(mandalId,villageCode,villageName) Values(145,2,'PEDDAMALLELA');
insert into village(mandalId,villageCode,villageName) Values(145,4,'ROMPICHERLA');



insert into village(mandalId,villageCode,villageName) Values(146,14,'AGRAHARAM');
insert into village(mandalId,villageCode,villageName) Values(146,5,'AVUVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(146,9,'BODUMALLUVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(146,12,'DODDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(146,10,'GUDAREVUPALLE');
insert into village(mandalId,villageCode,villageName) Values(146,3,'JANDLA');
insert into village(mandalId,villageCode,villageName) Values(146,6,'KAVALIPALLE');
insert into village(mandalId,villageCode,villageName) Values(146,7,'MADDELACHERUVU');
insert into village(mandalId,villageCode,villageName) Values(146,1,'MELLACHERUVU');
insert into village(mandalId,villageCode,villageName) Values(146,4,'MUDUPULAVEMULA');
insert into village(mandalId,villageCode,villageName) Values(146,11,'PILERU');
insert into village(mandalId,villageCode,villageName) Values(146,13,'REGALLU');
insert into village(mandalId,villageCode,villageName) Values(146,2,'TALUPULA');
insert into village(mandalId,villageCode,villageName) Values(146,8,'VEPULABYLU');
insert into village(mandalId,villageCode,villageName) Values(146,15,'YERRAGUNTLAPALLE');



insert into village(mandalId,villageCode,villageName) Values(147,3,'CHEEKATIPALLE');
insert into village(mandalId,villageCode,villageName) Values(147,2,'GUNDLORU');
insert into village(mandalId,villageCode,villageName) Values(147,14,'GUTTAPALEM');
insert into village(mandalId,villageCode,villageName) Values(147,12,'KALIKIRI');
insert into village(mandalId,villageCode,villageName) Values(147,13,'KALIKIRIREDDIVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(147,11,'KORLAKUNTA');
insert into village(mandalId,villageCode,villageName) Values(147,1,'MAHAL');
insert into village(mandalId,villageCode,villageName) Values(147,9,'MARRIKUNTAPALLE');
insert into village(mandalId,villageCode,villageName) Values(147,5,'MEDIKURTHI');
insert into village(mandalId,villageCode,villageName) Values(147,7,'MUNELLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(147,6,'PALLAVOLU');
insert into village(mandalId,villageCode,villageName) Values(147,4,'PARAPATLA');
insert into village(mandalId,villageCode,villageName) Values(147,10,'PATHEGADA');
insert into village(mandalId,villageCode,villageName) Values(147,8,'TSANDRAVARIPALE');



insert into village(mandalId,villageCode,villageName) Values(148,16,'ARAMADAKA');
insert into village(mandalId,villageCode,villageName) Values(148,2,'AYYAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(148,5,'BUDIDAVEDU');
insert into village(mandalId,villageCode,villageName) Values(148,10,'CHINTAPARTHI');
insert into village(mandalId,villageCode,villageName) Values(148,12,'CHINTHALAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(148,9,'GANDABOYANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(148,11,'JAMMALLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(148,1,'JARRAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(148,15,'KURAPARTHI');
insert into village(mandalId,villageCode,villageName) Values(148,7,'MANCHURU');
insert into village(mandalId,villageCode,villageName) Values(148,17,'MUGALAMARRI');
insert into village(mandalId,villageCode,villageName) Values(148,4,'NAGARIMADUGU');
insert into village(mandalId,villageCode,villageName) Values(148,8,'THATIGUNTAPALLE');
insert into village(mandalId,villageCode,villageName) Values(148,6,'TSAKIREVUPALLE');
insert into village(mandalId,villageCode,villageName) Values(148,14,'VAYALPAD');
insert into village(mandalId,villageCode,villageName) Values(148,3,'VELAGAPALLE');
insert into village(mandalId,villageCode,villageName) Values(148,13,'VITTALAM');



insert into village(mandalId,villageCode,villageName) Values(149,9,'AGRAHARAM');
insert into village(mandalId,villageCode,villageName) Values(149,4,'BANDLAPAI');
insert into village(mandalId,villageCode,villageName) Values(149,2,'MACHIREDDIGARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(149,5,'MUSTOOR');
insert into village(mandalId,villageCode,villageName) Values(149,8,'NIMMANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(149,1,'RACHAVETIVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(149,3,'REDDIVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(149,7,'T.SOWKILLAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(149,6,'THAVALAM');
insert into village(mandalId,villageCode,villageName) Values(149,10,'VENGAMVARIPALLE');



insert into village(mandalId,villageCode,villageName) Values(150,17,'ANKISETTIPALLE');
insert into village(mandalId,villageCode,villageName) Values(150,6,'BANDAMEEDA KAMMAPALLE @');
insert into village(mandalId,villageCode,villageName) Values(150,12,'BASINIKONDA');
insert into village(mandalId,villageCode,villageName) Values(150,1,'CHINNATHIPPASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(150,9,'CHIPPILI');
insert into village(mandalId,villageCode,villageName) Values(150,11,'KAMMAPALLE');
insert into village(mandalId,villageCode,villageName) Values(150,2,'KASIRAOPETA');
insert into village(mandalId,villageCode,villageName) Values(150,7,'KOLLABYLU');
insert into village(mandalId,villageCode,villageName) Values(150,3,'KOTHAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(150,16,'MADANAPALLE (RURAL)');
insert into village(mandalId,villageCode,villageName) Values(150,19,'MALEPADU');
insert into village(mandalId,villageCode,villageName) Values(150,14,'MOLAKALADINNE');
insert into village(mandalId,villageCode,villageName) Values(150,13,'PAMAIAHGARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(150,10,'PAPPIREDDIPALLE');
insert into village(mandalId,villageCode,villageName) Values(150,20,'PENCHUPADU');
insert into village(mandalId,villageCode,villageName) Values(150,8,'PONNETIPALEM');
insert into village(mandalId,villageCode,villageName) Values(150,4,'POTHAPOLU');
insert into village(mandalId,villageCode,villageName) Values(150,21,'THENEEGALAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(150,15,'VALASAPALLE');
insert into village(mandalId,villageCode,villageName) Values(150,18,'VEMPALLE');
insert into village(mandalId,villageCode,villageName) Values(150,5,'VENKAPPAKOTA');


insert into village(mandalId,villageCode,villageName) Values(151,12,'ARIKELA');
insert into village(mandalId,villageCode,villageName) Values(151,1,'CHEMBAKUR');
insert into village(mandalId,villageCode,villageName) Values(151,9,'CHOKKANDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(151,4,'EDDAKURAPALLE');
insert into village(mandalId,villageCode,villageName) Values(151,6,'ELAVANELLORU');
insert into village(mandalId,villageCode,villageName) Values(151,3,'KAPPALLE');
insert into village(mandalId,villageCode,villageName) Values(151,15,'KUDURUCHEEMANAPALLE');
insert into village(mandalId,villageCode,villageName) Values(151,8,'KURIJALA');
insert into village(mandalId,villageCode,villageName) Values(151,13,'MALENATHAM');
insert into village(mandalId,villageCode,villageName) Values(151,11,'MINIKI');
insert into village(mandalId,villageCode,villageName) Values(151,14,'MOOGAVADI');
insert into village(mandalId,villageCode,villageName) Values(151,2,'NARIGANIPALLE');
insert into village(mandalId,villageCode,villageName) Values(151,5,'R.NADIMPALLE');
insert into village(mandalId,villageCode,villageName) Values(151,7,'RAMASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(151,10,'VOOLAPADU');


insert into village(mandalId,villageCode,villageName) Values(152,13,'ARADIGUNTA');
insert into village(mandalId,villageCode,villageName) Values(152,18,'BANDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(152,8,'BHEEMAGANIPALLI');
insert into village(mandalId,villageCode,villageName) Values(152,2,'BODEVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(152,6,'CHADALLA');
insert into village(mandalId,villageCode,villageName) Values(152,1,'CHANDRAMAKULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(152,10,'ETAVAKILI');
insert into village(mandalId,villageCode,villageName) Values(152,17,'ETHUR');
insert into village(mandalId,villageCode,villageName) Values(152,16,'KUMMARAMTHAM');
insert into village(mandalId,villageCode,villageName) Values(152,11,'MAGANDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(152,9,'MANGALAM');
insert into village(mandalId,villageCode,villageName) Values(152,14,'MELUMDODDI');
insert into village(mandalId,villageCode,villageName) Values(152,7,'MELUPATLA');
insert into village(mandalId,villageCode,villageName) Values(152,3,'MITTACHINTHAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(152,19,'NEKKONDI');
insert into village(mandalId,villageCode,villageName) Values(152,4,'PALEMPALLE');
insert into village(mandalId,villageCode,villageName) Values(152,15,'PUNGANURU (RURAL)');
insert into village(mandalId,villageCode,villageName) Values(152,5,'RAGANIPALLE');
insert into village(mandalId,villageCode,villageName) Values(152,12,'VANAMALADINNE');


insert into village(mandalId,villageCode,villageName) Values(153,11,'29 A.  CHINTAMAKULAPALLE ');
insert into village(mandalId,villageCode,villageName) Values(153,14,'A. KOTHAKOTA');
insert into village(mandalId,villageCode,villageName) Values(153,9,'CHARALA');
insert into village(mandalId,villageCode,villageName) Values(153,6,'CHOWDEPALLE');
insert into village(mandalId,villageCode,villageName) Values(153,1,'DIGUVAPALLE');
insert into village(mandalId,villageCode,villageName) Values(153,15,'DURGASAMUDRAM');
insert into village(mandalId,villageCode,villageName) Values(153,4,'GADDAMVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(153,2,'KAGITHI');
insert into village(mandalId,villageCode,villageName) Values(153,12,'KATIPERI');
insert into village(mandalId,villageCode,villageName) Values(153,3,'KONDAMARRI');
insert into village(mandalId,villageCode,villageName) Values(153,13,'LADDIGAM');
insert into village(mandalId,villageCode,villageName) Values(153,7,'PANDILLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(153,5,'PEDDAYALLAKUNTLA');
insert into village(mandalId,villageCode,villageName) Values(153,10,'PUDIPATLA');
insert into village(mandalId,villageCode,villageName) Values(153,8,'SETTIPETA');



insert into village(mandalId,villageCode,villageName) Values(154,12,'AVULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(154,7,'IRIKIPENTA');
insert into village(mandalId,villageCode,villageName) Values(154,4,'KAMIREDDIVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(154,3,'KANDURU');
insert into village(mandalId,villageCode,villageName) Values(154,1,'MITTAPALLE');
insert into village(mandalId,villageCode,villageName) Values(154,11,'NANJAMPETA @ CHADAMBYLU');
insert into village(mandalId,villageCode,villageName) Values(154,6,'NELLIMANDA');
insert into village(mandalId,villageCode,villageName) Values(154,13,'PEDDAUPPARAPALLE');
insert into village(mandalId,villageCode,villageName) Values(154,9,'S. NADIMPALLE');
insert into village(mandalId,villageCode,villageName) Values(154,8,'SOMALA');
insert into village(mandalId,villageCode,villageName) Values(154,2,'THAMMINAYANIPALLE');
insert into village(mandalId,villageCode,villageName) Values(154,10,'UPPARAPALLE');
insert into village(mandalId,villageCode,villageName) Values(154,5,'VALLIGATLA');


insert into village(mandalId,villageCode,villageName) Values(155,7,'AMMAGARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(155,12,'BOORAGAMANDA');
insert into village(mandalId,villageCode,villageName) Values(155,13,'CHERUKUVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(155,2,'CHINTAMAKULAPALLE');
insert into village(mandalId,villageCode,villageName) Values(155,8,'GONGIVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(155,4,'KHAMBHAMVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(155,10,'NADIGADDA');
insert into village(mandalId,villageCode,villageName) Values(155,3,'PALAMANDA');
insert into village(mandalId,villageCode,villageName) Values(155,6,'SODAM');
insert into village(mandalId,villageCode,villageName) Values(155,5,'THATIGUNTAPALEM');
insert into village(mandalId,villageCode,villageName) Values(155,9,'THIMMANAYANIPALLE');
insert into village(mandalId,villageCode,villageName) Values(155,1,'VOOTUPALLE');
insert into village(mandalId,villageCode,villageName) Values(155,11,'YARRATHIVARIPALLE');



insert into village(mandalId,villageCode,villageName) Values(156,2,'AYYAVANDLAPALLE');
insert into village(mandalId,villageCode,villageName) Values(156,11,'BANDARUVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(156,14,'BODIREDDIGARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(156,12,'DEVALAMPET');
insert into village(mandalId,villageCode,villageName) Values(156,1,'E.RAMIREDDYGARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(156,5,'GADDAMVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(156,15,'KALLURU');
insert into village(mandalId,villageCode,villageName) Values(156,9,'KAVETIGARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(156,6,'MANGALAMPETA');
insert into village(mandalId,villageCode,villageName) Values(156,4,'RAMIREDDIGARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(156,10,'RAYAVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(156,3,'REDDIVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(156,7,'VALLIVETIVARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(156,13,'VENKATADASARIPALLE');
insert into village(mandalId,villageCode,villageName) Values(156,8,'YELLANKIVARIPALLLE');



--SmsEvent insert queris
insert into smsEvents(id,eventName,startDate,version,endDate,academicYear,status) values(1,'Independence Day','2011-08-15 00:00:00',0,'2011-08-15 00:00:00','2011-12','H');
insert into smsEvents(id,eventName,startDate,version,endDate,academicYear,status) values(2,'Republic Day','2012-01-26 00:00:00',0,'2012-01-26 00:00:00','2011-12','H');
insert into smsEvents(id,eventName,startDate,version,endDate,academicYear,status) values(3,'Christmas Day ','2011-12-24 00:00:00',0,'2011-12-25 00:00:00','2011-12','H');
insert into smsEvents(id,eventName,startDate,version,endDate,academicYear,status) values(4,'Teacher Day','2011-09-05 00:00:00',0,'2011-09-05 00:00:00','2011-12','E');
insert into smsEvents(id,eventName,startDate,version,endDate,academicYear,status) values(5,'Independence Day','2011-08-15 00:00:00',0,'2011-08-15 00:00:00','2011-12','H');



--organizationTypes
insert into organizationTypes(id, organizationType) values(1, 'Govt');
insert into organizationTypes(id, organizationType) values(2, 'Mpl');
insert into organizationTypes(id, organizationType) values(3, 'ADW');
insert into organizationTypes(id, organizationType) values(4, 'Fully Aided');
insert into organizationTypes(id, organizationType) values(5, 'Partially Aided');
insert into organizationTypes(id, organizationType) values(6, 'Unaided');

-- Medium 
insert into medium(id,name) values(1,'TELUGU');
insert into medium(id,name) values(2,'TAMIL');
insert into medium(id,name) values(3,'KANADA');
insert into medium(id,name) values(4,'MALAYALAM');
insert into medium(id,name) values(5,'GUJARATHI');
insert into medium(id,name) values(6,'HINDI');
insert into medium(id,name) values(7,'ENGLISH');
insert into medium(id,name) values(8,'SANSKRIT');
insert into medium(id,name) values(9,'ARABIC');
insert into medium(id,name) values(1'FRENCH');
insert into medium(id,name) values(11,'OTHER');
insert into medium(id,name) values(12,'URDU');


--Mother Tongue
insert into motherTongue(id, name) values(1, 'Assamese');
insert into motherTongue(id, name) values(2, 'Bengali');
insert into motherTongue(id, name) values(3, 'Bodo');
insert into motherTongue(id, name) values(4, 'Dogri');
insert into motherTongue(id, name) values(5, 'English');
insert into motherTongue(id, name) values(6, 'French');
insert into motherTongue(id, name) values(7, 'Gujarati');
insert into motherTongue(id, name) values(8, 'Hindi');
insert into motherTongue(id, name) values(9, 'Kannada');
insert into motherTongue(id, name) values(1 'Kashmiri');
insert into motherTongue(id, name) values(11, 'Konkani');
insert into motherTongue(id, name) values(12, 'Maithili');
insert into motherTongue(id, name) values(13, 'Malayalam');
insert into motherTongue(id, name) values(14, 'Manipuri/Meitei/Meithei');
insert into motherTongue(id, name) values(15, 'Marathi');
insert into motherTongue(id, name) values(16, 'Nepali');
insert into motherTongue(id, name) values(17, 'Oriya');
insert into motherTongue(id, name) values(18, 'Punjabi');
insert into motherTongue(id, name) values(19, 'Sanskrit');
insert into motherTongue(id, name) values(21, 'Santhali');
insert into motherTongue(id, name) values(22, 'Sindhi');
insert into motherTongue(id, name) values(23, 'Tamil');
insert into motherTongue(id, name) values(24, 'Telugu');
insert into motherTongue(id, name) values(25, 'Urdu');
insert into motherTongue(id, name) values(26, 'Other');
insert into motherTongue(id, name) values(27, 'German');
insert into motherTongue(id, name) values(28, 'Rajasthani');
INSERT INTO motherTongue(id,  name) VALUES (29,  'Saurashtra');

-- List from bank names
delete from bankMaster;
INSERT INTO bankMaster(id,bankName) 
VALUES ('--Select Bank Name--'),
	   (1,'Allahabad Bank'),
	   (2,'Axis Bank'),
	   (3,'Andhra Bank'),
	   (4,'Bank of Baroda'),
	   (5,'Bank of India'),
	   (6,'Bank of Maharashtra'),
	   (7,'Bank of Bikaner & Jaipur'),
	   (8,'Canara Bank'),
	   (9,'Central Bank of India'),
	   (1'Corporation Bank'),
	   (11,'Dena Bank'),
	   (12,'HDFC Bank'),
	   (13,'IDBI Bank'),
	   (14,'Indian Bank'),
	   (15,'Indian Overseas Bank'),
	   (16,'ICICI Bank'),
	   (17,'IndusInd Bank'),
	   (18,'ING Vysya Bank'),
	   (19,'Kotak Mahindra Bank'),
	   (2'Oriental Bank of Commerce'),
	   (21,'Punjab National Bank'),
	   (22,'Punjab and Sind Bank'),
	   (23,'Syndicate Bank'),
	   (24,'State Bank of India'),
	   (25,'State Bank of Hyderabad'),
	   (26,'State Bank of Mysore'),
	   (27,'State Bank of Patiala'),
	   (28,'State Bank of Travancore'),
	   (29,'Uco Bank'),
	   (3'United Bank of India'),
	   (31,'Union Bank of India'),
	   (32,'Vijaya Bank'),
	   (33,'Yes Bank');

--MOTHERTOUNG in CommonType
insert into commonType(id, custId,skillTypeName,type) values(1, 3,'TELUGU','MEDIUM');
insert into commonType(id, custId,skillTypeName,type) values(2, 3,'TAMIL','MEDIUM');
insert into commonType(id, custId,skillTypeName,type) values(3,3,'KANADA','MEDIUM');
insert into commonType(id, custId,skillTypeName,type) values(4,3,'MALAYALAM','MEDIUM');
insert into commonType(id, custId,skillTypeName,type) values(5,3,'GUJARATHI','MEDIUM');
insert into commonType(id, custId,skillTypeName,type) values(6,3,'HINDI','MEDIUM');
insert into commonType(id, custId,skillTypeName,type) values(7,3,'ENGLISH','MEDIUM');
insert into commonType(id, custId,skillTypeName,type) values(8,3,'SANSKRIT','MEDIUM');
insert into commonType(id, custId,skillTypeName,type) values(9,3,'ARABIC','MEDIUM');
insert into commonType(id, custId,skillTypeName,type) values(13,'FRENCH','MEDIUM');
insert into commonType(id, custId,skillTypeName,type) values(11,3,'CHRISTIAN','RELIGION');
insert into commonType(id, custId,skillTypeName,type) values(12,3,'MUSLIM','RELIGION');
insert into commonType(id, custId,skillTypeName,type) values(12,3,'VIKKY','RELIGION');

--Formative and Summative Assement grades
/*insert into formativeAssessmentGrades(id, version,custId,academicYearId,grade,gradePoint,maxMarks,minMarks) values(1,0,2,7,'A1',10,40,37);
insert into formativeAssessmentGrades(id, version,custId,academicYearId,grade,gradePoint,maxMarks,minMarks) values(2,0,2,7,'A2',9,36,33);
insert into formativeAssessmentGrades(id, version,custId,academicYearId,grade,gradePoint,maxMarks,minMarks) values(3,0,2,7,'B1',8,32,29);
insert into formativeAssessmentGrades(id, version,custId,academicYearId,grade,gradePoint,maxMarks,minMarks) values(4,0,2,7,'B2',7,28,25);
insert into formativeAssessmentGrades(id, version,custId,academicYearId,grade,gradePoint,maxMarks,minMarks) values(5,0,2,7,'C1',6,24,21);
insert into formativeAssessmentGrades(id, version,custId,academicYearId,grade,gradePoint,maxMarks,minMarks) values(6,0,2,7,'C2',5,20,17);
insert into formativeAssessmentGrades(id, version,custId,academicYearId,grade,gradePoint,maxMarks,minMarks) values(7,0,2,7,'D',4,16,13);
insert into formativeAssessmentGrades(id, version,custId,academicYearId,grade,gradePoint,maxMarks,minMarks) values(8,0,2,7,'E1','-',12,9);
insert into formativeAssessmentGrades(id, version,custId,academicYearId,grade,gradePoint,maxMarks,minMarks) values(9,0,2,7,'E2','-',8,0);

insert into summativeAssessmentGrades(id, version,custId,academicYearId,grade,gradePoint,maxMarks,minMarks) values(1,0,2,7,'A1',10,60,55);
insert into summativeAssessmentGrades(id, version,custId,academicYearId,grade,gradePoint,maxMarks,minMarks) values(2,0,2,7,'A2',9,54,49);
insert into summativeAssessmentGrades(id, version,custId,academicYearId,grade,gradePoint,maxMarks,minMarks) values(3,0,2,7,'B1',8,48,43);
insert into summativeAssessmentGrades(id, version,custId,academicYearId,grade,gradePoint,maxMarks,minMarks) values(4,0,2,7,'B2',7,42,37);
insert into summativeAssessmentGrades(id, version,custId,academicYearId,grade,gradePoint,maxMarks,minMarks) values(5,0,2,7,'C1',6,36,31);
insert into summativeAssessmentGrades(id, version,custId,academicYearId,grade,gradePoint,maxMarks,minMarks) values(6,0,2,7,'C2',5,30,25);
insert into summativeAssessmentGrades(id, version,custId,academicYearId,grade,gradePoint,maxMarks,minMarks) values(7,0,2,7,'D',4,24,19);
insert into summativeAssessmentGrades(id, version,custId,academicYearId,grade,gradePoint,maxMarks,minMarks) values(8,0,2,7,'E1','-',18,13);
insert into summativeAssessmentGrades(id, version,custId,academicYearId,grade,gradePoint,maxMarks,minMarks) values(9,0,2,7,'E2','-',12,0);

*/

-- Sections
delete from section;
INSERT INTO section(id,section,custId) VALUES
(1,'A',1),
(2,'B',1),
(3,'C',1),
(4,'D',1),
(5,'E',1),
(6,'F',1),
(7,'G',1),
(8,'H',1);

-- For support login creation
insert into customer(id,custEmail,customerName, organization,smsServiceProviderId) values(1,'support@hyniva.com','Support','Hyniva Consulting Services Pvt Ltd',1);

INSERT INTO Person (id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,citizenship,dateOfBirth,firstName,gender,height,lastName,maritalStatus,middleName,mothersMaidenName,passwordHint,passportExpireDate,passportNumber,personalTitle,socialSecurityNumber,suffix,weight,bloodGroup,experience,licenseExpDate,licenseNumber,mobileNumber,parentEmail,phoneNumber,fatherName,motherName,dateOfJoining,roleName) VALUES 
 (1,'2010-10-08 19:31:46',NULL,0,'2010-10-08 19:31:46',0,NULL,'2010-10-08 00:00:00','Admin','M',0,'Admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'a+',0,NULL,NULL,NULL,'support@uroomtech.co','9962030607',NULL,NULL,NULL,NULL);

insert into Account(id,password,username,personId,custId) values(1,0,'eee417ade1aaabe13200a63d02c841130be82051','support@uroomtech.co',1,1);

insert into UserRoles(roleId,userId) values (13,1);
INSERT INTO loginAccessbilityRoles (id, createdById, lastUpdatedById,  customerId, roleId, status) VALUES (1,  0, 0, 1, 13, 'Y');


-- For School Admin creation 

insert into customer(id,version,custEmail,customerName, organization) values(2,1,'admin@urt.com','SRI Saraswathi Vidya Mandir','SRI SRI SRI MOOKAMBIKA EDUCATIONAL SOCIETY');

INSERT INTO Person (id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,citizenship,dateOfBirth,firstName,gender,height,lastName,maritalStatus,middleName,mothersMaidenName,passwordHint,passportExpireDate,passportNumber,personalTitle,socialSecurityNumber,suffix,weight,bloodGroup,experience,licenseExpDate,licenseNumber,mobileNumber,parentEmail,phoneNumber,fatherName,motherName,dateOfJoining,roleName) VALUES 
 (2,0,'2010-10-08 19:31:46',NULL,0,'2010-10-08 19:31:46',NULL,'10/15/1985','Admin','M',0,'Admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'a+',0,NULL,NULL,NULL,'admin@gmail.com','9962030607',NULL,NULL,NULL,NULL);

insert into Account(id,password,username,personId,custId) values(1,'eee417ade1aaabe13200a63d02c841130be82051','support@hyniva.com',2,2);

insert into UserRoles(roleId,userId) values (1,2);
insert into staff (id,createdDate,version,casualLeaves,sickLeaves,status,accountId,custId,totalLeaves,academicYearId) values(07,'2010-11-30 17:00:50','1',10,20,'A',2,2,30,1);

-- k-Video
delete from playList where id=19;
update playList set subjectName='Maths' where id=20;

delete from playList where id=71;
update playList set subjectName='Maths' where id=72;

delete from playList where id=11;
update playList set subjectName='Maths' where id=12;

delete from playList where id=37;
update playList set subjectName='Maths' where id=38;

delete from playList where id=9;
update playList set subjectName='Maths' where id=10;

delete from playList where id=7;
update playList set subjectName='Maths' where id=8;

delete from playList where id=43;
update playList set subjectName='Maths' where id=44;

delete from playList where id=41;
update playList set subjectName='Maths' where id=42;

delete from playList where id=65;
update playList set subjectName='Science' where id=66;

delete from playList where id=61;
update playList set subjectName='Science' where id=62;

delete from playList where id=75;
update playList set subjectName='Science' where id=76;

delete from playList where id=93;
update playList set subjectName='Science' where id=94;

delete from playList where id=25;
update playList set subjectName='Science' where id=26;

delete from playList where id=79;
update playList set subjectName='Science' where id=80;

delete from playList where id=87;
update playList set subjectName='Science' where id=88;

delete from playList where id=55;
update playList set subjectName='Humanities & Other' where id=56;

delete from playList where id=89;
update playList set subjectName='Humanities & Other' where id=90;

delete from playList where id=1;
update playlist set title='Developmental Math 1' where id=2;

delete from playList where id=85;
update playList set subjectname='Maths' where id=86;

update playList set subjectname='Maths',subtopic='Developmental Math 1' where id=2;

delete from playList where id=1;
update playList set title='Developmental Math 1' where id=2;
update playList set subjectName='Maths',subtopic='Developmental Math 1' where id=2;

delete from playList where id=3;
update playList set subjectName='Test Preparation' where id=4;

delete from playList where id=7;
update playList set subjectName='Maths' where id=8;

delete from playList where id=9;
update playList set subjectName='Maths' where id=10;

delete from playList where id=11;
update playList set subjectName='Maths' where id=12;

delete from playList where id=13;
update playList set subjectName='Maths' where id=14;

delete from playList where id=15;
update playList set subjectName='Maths' where id=16;

delete from playList where id=17;
update playList set subjectName='Maths' where id=18;

delete from playList where id=19;
update playList set subjectName='Maths' where id=20;

delete from playList where id=21;
update playList set subjectName='Maths' where id=22;

delete from playList where id=23;
update playList set subjectName='Test Preparation' where id=24;

delete from playList where id=25;
update playList set subjectName='Science' where id=26;

delete from playList where id=29;
update playList set subjectName='Test Preparation',subTopic='Problem Solving' where id=30;

delete from playList where id=33;
update playList set subjectName='Test Preparation',subTopic='Geometry' where id=34;

delete from playList where id=35;
update playList set subjectName='Maths' where id=36;

delete from playList where id=37;
update playList set subjectName='Maths' where id=38;

delete from playList where id=39;
update playList set subjectName='Test Preparation' where id=40;

delete from playList where id=41;
update playList set subjectName='Maths' where id=42;

delete from playList where id=43;
update playList set subjectName='Maths' where id=44;

delete from playList where id=49;
update playList set subjectName='Test Preparation',subTopic='Data Sufficiency' where id=50;

delete from playList where id=51;
update playList set subjectName='Test Preparation',subTopic='Algebra I' where id=52;

delete from playList where id=55;
update playList set subjectName='Humanities & Other' where id=56;

delete from playList where id=61;
update playList set subjectName='Science' where id=62;

delete from playList where id=65;
update playList set subjectName='Science' where id=66;

delete from playList where id=71;
update playList set subjectName='Maths' where id=72;

delete from playList where id=75;
update playList set subjectName='Science' where id=76;

delete from playList where id=79;
update playList set subjectName='Science' where id=80;

delete from playList where id=81;
update playList set subjectName='Test Preparation' where id=82;

delete from playList where id=83;
update playList set subjectName='Test Preparation' where id=84;

delete from playList where id=85;
update playList set subjectName='Maths' where id=86;

delete from playList where id=87;
update playList set subjectName='Science' where id=88;

delete from playList where id=89;
update playList set subjectName='Humanities & Other' where id=90;

delete from playList where id=93;
update playList set subjectName='Science' where id=94;


-- Student Activities For Exam ===========Not in server=========
/*
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(1,1,'IS INNOVATIVE IN IDEAS','B',2,1);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(2,1,'ART EDUCATION','A',2,1);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(3,1,'PHYSICAL & HEALTH EDUCATION','B',2,1);


insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(4,1,'THINKING SKILLS','C',1,1);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(5,1,'ART EDUCATION','A',1,1);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(6,1,'PHYSICAL & HEALTH EDUCATION','B',1,1);

insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(7,1,'ATTITUDE TOWARDS','B',1,1);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(8,1,'TEACHERS','B',1,1);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(9,1,'SCHOOL MATES','A',1,1);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(10,1,'SCHOOL PROGRAMMERS','B',1,1);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(11,1,'ENVIRONMENT','C',1,1);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(12,'VALUE SYSTEMS','VALUE SYSTEMS','B',1,1);

insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(13,2,'LITERARY and CREATIVE SKILLS','A',2,1);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(14,2,'SCIENTIFIC SKILLS','B',2,1);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(15,2,'AESTHETIC and PERFORMANING ART','A',2,1);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(16,2,'CLUBS (Eco Club,Health and Wellness and others)','C',2,1);



insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(17,2,'IS INNOVATIVE IN IDEAS','B',2,2);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(18,2,'A',2,2);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(19,2,'PHYSICAL & HEALTH EDUCATION','B',2,2);


insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(20,2,'THINKING SKILLS','C',1,2);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(21,2,'ART EDUCATION','A',1,2);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(22,3,'PHYSICAL & HEALTH EDUCATION','B',1,2);

insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(23,3,'ATTITUDE TOWARDS','B',1,2);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(24,3,'TEACHERS','B',1,2);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(25,3,'SCHOOL MATES','A',1,2);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(26,3,'SCHOOL PROGRAMMERS','B',1,2);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(27,3,'ENVIRONMENT','C',1,2);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(28,3,'VALUE SYSTEMS','B',1,2);

insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(29,4,'LITERARY and CREATIVE SKILLS','A',2,2);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(30,4,'SCIENTIFIC SKILLS','B',2,2);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(31,4,'AESTHETIC and PERFORMANING ART','A',2,2);
insert into studentAcademicPerformance (id,studentActivityTypeId,description,grade,examTypeId,studId) values(32,4,'CLUBS (Eco Club,Health and Wellness and others)','C',2,2);

*/

--For creating syllabus table it is mandatory 

create table syllabusTypeInfo (customerId bigint(20) not null, syllabusTypeId bigint(20) not null, primary key (customerId, syllabusTypeId));


-------------------------  income tax rates 07/08/2012

INSERT INTO incomeTaxRates(id,version,slabs,netAmount1,netAmount2,taxPercentage,financialYear,status,month,year,Gender) VALUES
(1,1,1,0,200000,0,'2012-2013','Y',8,2012,'M'),
(2,1,2,200001,500000,10,'2012-2013','Y',8,2012,'M'),
(3,1,3,500001,1000000,20,'2012-2013','Y',8,2012,'M'),
(4,1,4,1000000,0,30,'2012-2013','Y',8,2012,'M');

INSERT INTO incomeTaxRates(id,version,slabs,netAmount1,netAmount2,taxPercentage,financialYear,status,month,year,Gender) VALUES
(5,1,1,0,200000,0,'2012-2013','Y',8,2012,'F'),
(6,1,2,200001,500000,10,'2012-2013','Y',8,2012,'F'),
(7,1,3,500001,1000000,20,'2012-2013','Y',8,2012,'F'),
(8,1,4,1000000,0,30,'2012-2013','Y',8,2012,'F');

insert into payrollTypes (id,custId,payrollName,payrollDescription,defaultStatus,payrollType,version) values(1,0,'BS','Basic Salary','Y','BS',1);
insert into payrollTypes (id,custId,payrollName,payrollDescription,defaultStatus,payrollType,version) values(2,0,'HRA','House Rental Allowance','Y','AW',1);
insert into payrollTypes (id,custId,payrollName,payrollDescription,defaultStatus,payrollType,version) values(3,0,'TA','Travelling Allowance','Y','AW',1);

insert into schoolFeeSetting (id,settingName,settingType,status) values(1,'Non Term Fee','Y','S'),(2,'Terms Fee','N','S'),(3,'Transport Fee','N','T'),(4,'Hostel Fee','N','H');

INSERT INTO  organizationSubTypes (id, organizationTypeId, schoolCategory) VALUES (1, 1,'Primary');

INSERT INTO  organizationSubTypes (id, organizationTypeId, schoolCategory) VALUES (2, 1, 'Primary with Upper Primary');

INSERT INTO  organizationSubTypes (id, organizationTypeId, schoolCategory) VALUES (3, 1, 'Primary with Upper primary and secondary/higher secondary');

INSERT INTO  organizationSubTypes (id, organizationTypeId, schoolCategory) VALUES (4, 1,'Upper primary only');

INSERT INTO  organizationSubTypes (id, organizationTypeId, schoolCategory) VALUES (5, 1, 'Upper primary with secondary/higher secondary');


update customer set  organizationSubTypeId=0;


alter table onlineApplicationDetails  modify religionId BIGINT  NULL DEFAULT NULL;

alter table onlineApplicationDetails ADD KEY (religionId);

ALTER TABLE onlineApplicationDetails ADD KEY (castId);

ALTER TABLE onlineApplicationDetails ADD KEY (subCastId);

ALTER TABLE onlineApplicationDetails ADD KEY (classId);

update onlineApplicationDetails set castId=null where castId=0;

update onlineApplicationDetails set subCastId=null where subCastId=0;

update onlineApplicationDetails set religionId=null where religionId=0;

alter table studyAndBonafiedSettings change dueCirtificateFileName dueCertificateFileName varchar(255);

alter table transportMaintenance change repairDescription repairDescription varchar(1024);

ALTER TABLE onlineApplicationDetails MODIFY entranceMarks SMALLINT ;

alter table transportMaintenance modify vehicleId bigint default 0;


 ALTER TABLE vehicles drop column academicYearId;
 
 ALTER TABLE vehicles drop column driverId;
 
 ALTER TABLE vehicles drop column helperId;
 
 ALTER TABLE vehicles drop column status;
 
 ALTER TABLE vehicles drop column name;
  
 ALTER TABLE onlineApplicationDetails drop column className;
 ALTER TABLE onlineApplicationDetails drop column religion;
 
 
/*Fee related procedure*/
SET GLOBAL log_bin_trust_function_creators = 1;
set @sequanceId is 1;
DROP FUNCTION IF EXISTS payment_SequanceId; 
DELIMITER $$ 
  CREATE  FUNCTION payment_SequanceId() RETURNS int(20)
  begin
   
  return if(@sequanceId=null,if(@sequanceId, @sequanceId:=@sequanceId+1, @sequanceId:=1), if(@sequanceId, @sequanceId:=@sequanceId+1, @sequanceId:=1) );
 
end $$
DELIMITER ;


alter table subTypeGrades modify maxMarks double not NULL default 0.0;
alter table subTypeGrades modify minMarks double not NULL default 0.0;
alter table schoolGrades modify minMarks double not NULL default 0.0;
alter table schoolGrades modify maxMarks double not NULL default 0.0;
alter table overAllGrades modify minMarks double not NULL default 0.0;
alter table overAllGrades modify maxMarks double not NULL default 0.0;

alter table onlineApplicationDetails modify column annualIncome double not null default 0;
 alter table Person modify column annualIncome double not null default 0;

// 26 May 2014 - Seshu
alter table class drop column transportFeeStatus;

// 08 Jul 2014 - Cherivi
UPDATE student s  SET imageId = ( SELECT imageId FROM Account WHERE id = s.accountid)  
    WHERE s.accountId in (SELECT id FROM Account); 
    
UPDATE Account A LEFT JOIN Account B USING (id) SET A.imageId=B.studentImageId where B.studentImageId is not null;

update class c set noOfSections =(select count(*) from studyClass where classNameClassId=c.id); 

// 17 Jul 2014 - Cherivi
alter table leaves modify leavesCount double not NULL default 0.0;   

// 08 aug 2014 - CVS
alter table customer change feeReceiptNoWithCustName  feeReceiptNoWithCustName varchar(10);

/* Changes for roles update */
update Role set description='System Administrator' , name='ROLE_SYSTEMADMINISTRATOR' where id=20;

/* Changes for Fps role */
insert into customer(id,version,custEmail,customerName, organization) values(2,1,'admin@urt.com','SRI Saraswathi Vidya Mandir','SRI SRI SRI MOOKAMBIKA EDUCATIONAL SOCIETY');

INSERT INTO Person (id,createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,version,citizenship,firstName,gender,height,lastName,maritalStatus,middleName,mothersMaidenName,passwordHint,passportExpireDate,passportNumber,personalTitle,socialSecurityNumber,suffix,weight,bloodGroup,experience,licenseExpDate,licenseNumber,mobileNumber,parentEmail,phoneNumber,fatherName,motherName,dateOfJoining,roleName) VALUES 
 (5340,0,'2010-10-08 19:31:46',NULL,0,'2010-10-08 19:31:46',0,NULL,'fps','M',0,'fps',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'a+',0,NULL,NULL,NULL,'fps@gmail.com','9962030607',NULL,NULL,NULL,NULL);

insert into Account(id,version,password,username,personId,custId) values(5218,0,'eee417ade1aaabe13200a63d02c841130be82051','fps@urt.com',5340,19);

insert into UserRoles(roleId,userId) values (34,5218);
insert into loginAccessbilityRoles(id,customerId,roleId,status,version) values (5416,58,1,'Y',0);

INSERT INTO Role (id, createdById, createdDate, lastAccessDate, lastUpdatedById, lastUpdatedDate, version, description, name) VALUES ('35', '1', '2014-10-06 00:00:00.000000', '2014-10-06 00:00:00.000000', '1', '2014-10-06 00:00:00.000000', '0', 'Asst Staff', 'ROLE_ASST_STAFF');

INSERT INTO area ( id ,  createdById ,  createdDate ,  lastAccessDate ,  lastUpdatedById ,  lastUpdatedDate ,  version ,  districtId ,  mandalId ,  stateId ,  status ,  villageId ) VALUES ('1', '0', NULL, NULL, '0', NULL, NULL, '23', '2213', '1', 'Y', '0');
INSERT INTO area ( id ,  createdById ,  createdDate ,  lastAccessDate ,  lastUpdatedById ,  lastUpdatedDate ,  version ,  districtId ,  mandalId ,  stateId ,  status ,  villageId ) VALUES ('2', '0', NULL, NULL, '0', NULL, NULL, '23', '2214', '1', 'Y', '0');

UPDATE  role  SET  name  = 'ROLE_CEO', description  = 'Cluster Education Officer' WHERE  role . id  = 28;

INSERT INTO  role  ( id ,  createdById ,  createdDate ,  lastAccessDate ,  lastUpdatedById ,  lastUpdatedDate ,  version ,  description ,  name ) VALUES ('32', '0', '2014-10-09 00:00:00.000000', '2014-10-09 00:00:00.000000', '0', '2014-10-09 00:00:00.000000', '0', 'Block Education Officer', 'ROLE_BEO');

INSERT INTO  activityType  ( id ,  createdById ,  createdDate ,  lastAccessDate ,  lastUpdatedById ,  lastUpdatedDate ,  version ,  activityTypeName ,  description ) VALUES ('1', '0', '2014-10-28 00:00:00.000000', '2014-10-28 00:00:00.000000', '0', '2014-10-28 00:00:00.000000', '0', 'Sports', 'Sports');

insert into smsEvents (id,endDate,startDate,academicYear,eventName,version) values (6,"2014-08-15  00:00:00","2014-08-15  00:00:00","2014-15","Independence Day",0)
,(7,"2014-01-26 00:00:00","2014-01-26 00:00:00","2014-15","Republic Day",0)
,(8,"2014-12-25 00:00:00","2014-12-25 00:00:00","2014-15","Christmas Day",0)
,(9,"2014-09-05 00:00:00","2014-09-05 00:00:00","2014-15","Teachers Day",0)
,(10,"2014-11-14 00:00:00","2014-11-14 00:00:00","2014-15","Childrens Day",0);

ALTER TABLE mess DROP  buildingId ;

INSERT INTO messFoodType ( id ,  createdById ,  createdDate ,  lastAccessDate ,  lastUpdatedById ,  lastUpdatedDate ,  version ,  custId ,  foodTypeName ,  messId ,  status ) VALUES ('1', '0', '2014-12-01 00:00:00.000000', '2014-12-01 00:00:00.000000', '0', '2014-12-01 00:00:00.000000', '0', '18', 'Break Fast', '1', 'Y');
INSERT INTO messFoodType ( id ,  createdById ,  createdDate ,  lastAccessDate ,  lastUpdatedById ,  lastUpdatedDate ,  version ,  custId ,  foodTypeName ,  messId ,  status ) VALUES ('2', '0', '2014-12-01 00:00:00.000000', '2014-12-01 00:00:00.000000', '0', '2014-12-01 00:00:00.000000', '0', '18', 'Lunch', '1', 'Y');
INSERT INTO messFoodType ( id ,  createdById ,  createdDate ,  lastAccessDate ,  lastUpdatedById ,  lastUpdatedDate ,  version ,  custId ,  foodTypeName ,  messId ,  status ) VALUES ('3', '0', '2014-12-01 00:00:00.000000', '2014-12-01 00:00:00.000000', '0', '2014-12-01 00:00:00.000000', '0', '18', 'Snacks', '1', 'Y');
INSERT INTO messFoodType ( id ,  createdById ,  createdDate ,  lastAccessDate ,  lastUpdatedById ,  lastUpdatedDate ,  version ,  custId ,  foodTypeName ,  messId ,  status ) VALUES ('4', '0', '2014-12-01 00:00:00.000000', '2014-12-01 00:00:00.000000', '0', '2014-12-01 00:00:00.000000', '0', '18', 'Dinner', '1', 'Y');

UPDATE  manageitems SET messId = '0';

update Person set experience =0 where experience='';
update Person set experience =0 where experience is null;
alter table Person modify column experience double default NULL;

update customer set committedFeeStatus='N';

ALTER TABLE attachment MODIFY filePath varchar(511) NULL;

ALTER TABLE attachment MODIFY fileSize varchar(511) NULL;

ALTER TABLE attachment MODIFY fileTypePath varchar(511) NULL;
ALTER TABLE attachment MODIFY filePath varchar(511) NULL;

ALTER TABLE attachment MODIFY fileSize varchar(511) NULL;

ALTER TABLE userImage MODIFY path  varchar(511) NULL;

INSERT INTO Role (id, createdDate, lastAccessDate, lastUpdatedDate, version, description, name, createdById, lastUpdatedById) 
VALUES ('36', '2015-02-20 00:00:00.000000', '2015-02-20 00:00:00.000000', '2015-02-20 00:00:00.000000', '0', 'Secretary', 'ROLE_SECRETARY', '0', '0');

INSERT INTO Role (id, createdDate, lastAccessDate, lastUpdatedDate, version, description, name, createdById, lastUpdatedById) 
VALUES ('37', '2015-02-20 00:00:00.000000', '2015-02-20 00:00:00.000000', '2015-02-20 00:00:00.000000', '0', 'Secretary PA', 'ROLE_SECRETARY_PA', '0', '0');

INSERT INTO Role (id, createdDate, lastAccessDate, lastUpdatedDate, version, description, name, createdById, lastUpdatedById) 
VALUES ('38', '2015-02-20 00:00:00.000000', '2015-02-20 00:00:00.000000', '2015-02-20 00:00:00.000000', '0', 'Manager', 'ROLE_MANAGER', '0', '0');

INSERT INTO loginAccessbilityRoles (id, createdById, createdDate, lastAccessDate, lastUpdatedById, lastUpdatedDate, version, customerId, roleId, status) VALUES ('2398', '0', '2015-02-23 00:00:00.000000', '2015-02-23 00:00:00.000000', '0', '2015-02-23 00:00:00.000000', '1', '59', '38', 'Y');


ALTER TABLE customer CHANGE orgnizationId orgId INT(11) NULL DEFAULT '0';

ALTER TABLE organizationMembers DROP FOREIGN KEY FKA969C4669D4EECB9 ;

ALTER TABLE organizationMembers DROP COLUMN organizationId, DROP INDEX organizationId ;


ALTER TABLE particulartype CHANGE name particularTypeName VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL;

ALTER TABLE budgetRequest CHANGE custld custId BIGINT(20) NOT NULL;


INSERT INTO  particulartype  ( id ,  createdById ,  createdDate ,  lastAccessDate ,  lastUpdatedById ,  lastUpdatedDate ,  version ,  particularTypeName ,  orgId ,  status ) VALUES
(1, 62590, '2015-02-25 11:01:05', NULL, 0, '2015-02-25 11:01:05', 14, 'Eastablishment Cost', 1, 'Y'),
(2, 62590, '2015-02-25 11:01:52', NULL, 0, '2015-02-25 11:01:52', 643, 'Scholarship / Concessions', 1, 'Y'),
(3, 62590, '2015-02-26 17:06:31', NULL, 0, '2015-02-26 17:06:31', 15, 'Miscellaneous Expenses', 1, 'Y'),
(4, 62590, '2015-02-26 18:41:26', NULL, 0, '2015-02-26 18:41:26', 8, 'Closing Balances', 1, 'Y');



INSERT INTO  particulartype  ( id ,  createdById ,  createdDate ,  lastAccessDate ,  lastUpdatedById ,  lastUpdatedDate ,  version ,  particularTypeName ,  orgId ,  status ) VALUES
(5, 62590, '2015-02-25 11:01:05', NULL, 0, '2015-02-25 11:01:05', 14, 'Eastablishment Cost', 4, 'Y'),
(6, 62590, '2015-02-25 11:01:52', NULL, 0, '2015-02-25 11:01:52', 643, 'Scholarship / Concessions', 4, 'Y'),
(7, 62590, '2015-02-26 17:06:31', NULL, 0, '2015-02-26 17:06:31', 15, 'Miscellaneous Expenses', 4, 'Y'),
(8, 62590, '2015-02-26 18:41:26', NULL, 0, '2015-02-26 18:41:26', 8, 'Closing Balances', 4, 'Y');


INSERT INTO  particular  ( id ,  createdById ,  createdDate ,  lastAccessDate ,  lastUpdatedById ,  lastUpdatedDate ,  version ,  particularName ,  status ,  particularTypeId ) VALUES
(1, 62590, '2015-02-25 11:53:14', NULL, 0, '2015-02-25 11:53:14', 497, 'Salaries & Allowances', 'Y', 1),
(2, 62590, '2015-02-26 16:37:56', NULL, 0, '2015-02-26 16:37:56', 467, 'Contingencies', 'Y', 1),
(3, 62590, '2015-02-26 17:04:26', NULL, 0, '2015-02-26 17:04:26', 460, 'Postage', 'Y', 1),
(4, 62590, '2015-02-26 17:04:40', NULL, 0, '2015-02-26 17:04:40', 455, 'Water & Electricity', 'Y', 1),
(5, 62590, '2015-02-26 17:04:49', NULL, 0, '2015-02-26 17:04:49', 450, 'Telephone', 'Y', 1),
(6, 62590, '2015-02-26 17:05:02', NULL, 0, '2015-02-26 17:05:02', 445, 'Travel & Conveyance', 'Y', 1),
(7, 62590, '2015-02-26 17:05:16', NULL, 0, '2015-02-26 17:05:16', 440, 'Printing & Stationery', 'Y', 1),
(8, 62590, '2015-02-26 17:05:28', NULL, 0, '2015-02-26 17:05:28', 435, 'Bank Commissions', 'Y', 1),
(9, 62590, '2015-02-26 17:05:38', NULL, 0, '2015-02-26 17:05:38', 430, 'Audit Fees', 'Y', 1),
(10, 62590, '2015-02-26 17:05:49', NULL, 0, '2015-02-26 17:05:49', 425, 'Rates & Taxes', 'Y', 1),
(11, 62590, '2015-02-26 17:06:45', NULL, 0, '2015-02-26 17:06:45', 454, 'Sports', 'Y', 3),
(12, 62590, '2015-02-26 17:06:55', NULL, 0, '2015-02-26 17:06:55', 449, 'Reading Room', 'Y', 3),
(13, 62590, '2015-02-26 17:07:03', NULL, 0, '2015-02-26 17:07:03', 444, 'Laboratory', 'Y', 3),
(14, 62590, '2015-02-26 17:07:16', NULL, 0, '2015-02-26 17:07:16', 439, 'Audio Visual', 'Y', 3),
(15, 62590, '2015-02-26 17:08:22', NULL, 0, '2015-02-26 17:08:22', 433, 'Students Welfare Fund / Teachers Benefit Fund', 'Y', 3),
(16, 62590, '2015-02-26 17:08:39', NULL, 0, '2015-02-26 17:08:39', 428, 'Exam Expenditures', 'Y', 3),
(17, 62590, '2015-02-26 17:09:03', NULL, 0, '2015-02-26 17:09:03', 423, 'Public Functions / Cultural Activities', 'Y', 3),
(18, 62590, '2015-02-26 17:09:32', NULL, 0, '2015-02-26 17:09:32', 418, 'Extra Additional Teachers', 'Y', 3),
(19, 62590, '2015-02-26 17:09:50', NULL, 0, '2015-02-26 17:09:50', 413, 'Computer Staff Salary', 'Y', 3),
(20, 62590, '2015-02-26 18:41:41', NULL, 0, '2015-02-26 18:41:41', 399, 'Cash on hand A/c', 'Y', 4),
(21, 62590, '2015-02-26 18:41:53', NULL, 0, '2015-02-26 18:41:53', 394, 'Cash in operation A/c', 'Y', 4);

alter table attachment modify column fileType varchar(125);
ALTER TABLE  staffDailyAttendance  
  ADD CONSTRAINT  staffdailyattendance_account_accountId 
  FOREIGN KEY ( accountId  )
  REFERENCES  account  ( id  )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
 ADD INDEX  staffdailyattendance_account_accountId  ( accountId  ASC) ;
 
INSERT INTO supportTeam ( id ,  email ,  firstName ,  lastName , mobileNumber ) VALUES 
(1, 'support@eazyschool.com', 'EazySchool', 'Support',''),
(2, 'sunil@uroomtech.com', 'Sunil', 'Kumar', '7676196477'),
(3, 'ramesh@uroomtech.com', 'Ramesh','', '9066658625'),
(4, 'sravan96@gmail.com', 'Sravan', 'Kumar', '9741081562'),
(5, 'ravi@uroomtech.com', 'Ravi', 'Kumar', '9535850546'); 



insert into generalSubjects (id,name,description,subjectNumber,language,subjectType) values(1,'Hindi','','','Y','C'),
(2,'English','','','Y','C'),(3,'Mathematics','','','N','C'),(4,'Environmental Studies','','','N','C'),(5,'Science','','','N','C'),
(6,'Social Studies','','','N','C'),(7,'Chemistry','','','N','C'),(8,'Biology','','','N','C'),(9,'History','','','N','C'),(10,'Political Science','','','N','C'),
(11,'Geography','','','N','C'),(12,'Accountancy','','','N','C'),(13,'Business Studies','','','N','C'),(14,'Economics','','','N','C'),(15,'Physics','','','N','C'),
(16,'Science','','','N','S'),(17,'Social','','','N','S'),(18,'Mathematics','','','N','S'),(19,'English','','','N','S'),(20,'Chemistry','','','N','S'),(21,'Botany','','','N','S'),
(22,'Zoology','','','N','S'),(23,'Physics','','','N','S'),(24,'Civics','','','N','S'),(25,'History','','','N','S'),(26,'Geography','','','N','S'),(27,'Economics','','','N','S'),
(28,'Buss. Math','','','N','S'),(29,'Statistics','','','N','S'),(30,'Accountancy','','','N','S'),(31,'Commerce','','','N','S'),(32,'Kannada','','','Y','S'),(33,'Computer','','','N','S'),
(34,'Spoken English Course','','','N','S'),(35,'Arts&Graphity&Drawing','','','N','S'),(36,'GK','','','N','S'),(37,'Moral Science','','','N','S'),(38,'Music','','','N','S'),
(39,'PT','','','N','S'),(40,'Paint','','','N','S'),(41,'Club Activity','','','N','S'),(42,'Biology','','','N','S'),(43,'Tamil','','','Y','S'),(44,'Sanskrit','','','Y','S'),(45,'SUPW','','','N','S'),
(46,'Accounts','','','N','S'),(47,'Business Study','','','N','S'),(48,'Craft','','','N','S'),(49,'Marathi ','','','Y','S'),(50,'EVS','','','N','S'),(51,'Hindi','','','Y','S'),
(52,'Urdu','','','Y','S'),(53,'Telugu','','','Y','S'),(54,'English','','','N','I'),(55,'Telugu','','','Y','I'),(56,'French','','','Y','I'),(57,'Spanish','','','Y','I'),(58,'German','','','Y','I'),
(59,'History','','','N','I'),(60,'Geography','','','N','I'),(61,'Mathematics','','','N','I'),(62,'Physics','','','N','I'),(63,'Chemistry','','','N','I'),(64,'Biology','','','N','I'),
(65,'Economics','','','N','I'),(66,'Commercial Studies','','','N','I'),(67,'Technical Drawing','','','N','I'),(68,'Computer Science','','','N','I'),(69,'Environmental Science','','','Y','I'),
(70,'Agricultural Science','','','N','I'),(71,'Economic ','','','N','I'),(72,'Art','','','N','I'),(73,'Yoga','','','N','I'),(74,'SUPW and Community Service','','','N','I');

UPDATE generalSubjects SET version = 0 WHERE version IS NULL;  

update Role set description='System Administrator' , name='ROLE_SYSTEMADMINISTRATOR' where id=20;
update Role set description='Management Trainee' , name='ROLE_MANAGEMENTTRAINEE' where id=22;
update Role set description='Vice Principal' , name='ROLE_VICEPRINCIPAL' where id=31;
update Role set description='Admin Officer' , name='ROLE_ADMINOFFICER' where id=32;
update Role set description='Public Relation Officer' , name='ROLE_PUBLICRELATIONOFFICER' where id=33;
update customer SET smsServiceProviderId=1;



ALTER TABLE staffMonthlyAttendance drop column custId;
ALTER TABLE staffMonthlyAttendance drop column staffId;
ALTER TABLE staffMonthlyAttendance drop column academicDetailsId;

ALTER TABLE studentmonthlyattendance drop column classSectionId;
ALTER TABLE studentmonthlyattendance drop column subjectId;
ALTER TABLE studentmonthlyattendance drop column totalWorkingPeriods;
ALTER TABLE studentmonthlyattendance drop column subjectName;

ALTER TABLE Appointment  modify description varchar(1024);
ALTER TABLE Appointment  modify apporveDescription varchar(1024);
 
ALTER TABLE messages CHANGE COLUMN id id BIGINT(20) NOT NULL AUTO_INCREMENT;
ALTER TABLE messageDetailsTracking CHANGE COLUMN id id BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE dayBook modify description varchar(500);
ALTER TABLE dayBook modify column amount double default 0;

UPDATE  customer  SET  smsCost  = '0.14 ';



--ALTER TABLE  weekDays  ADD  sortingOrder  INT(11) NOT NULL DEFAULT '0' AFTER  dayName ;

UPDATE  weekDays  SET  sortingOrder  = '1' WHERE  weekDays . id  = 2;
UPDATE  weekDays  SET  sortingOrder  = '2' WHERE  weekDays . id  = 3;
UPDATE  weekDays  SET  sortingOrder  = '3' WHERE  weekDays . id  = 4;
UPDATE  weekDays  SET  sortingOrder  = '4' WHERE  weekDays . id  = 5;
UPDATE  weekDays  SET  sortingOrder  = '5' WHERE  weekDays . id  = 6;
UPDATE  weekDays  SET  sortingOrder  = '6' WHERE  weekDays . id  = 7;
UPDATE  weekDays  SET  sortingOrder  = '7' WHERE  weekDays . id  = 1;

ALTER TABLE  staffDailyAttendance  DROP FOREIGN KEY  staffDailyAttendance_account_accountId ;
ALTER TABLE  staffDailyAttendance  DROP INDEX  staffDailyAttendance_account_accountId  ;


--accountCategorys
INSERT INTO accountCategorys(id,version,name) VALUES
(1,0,'Income'),
(2,0,'Expenditure'),
(3,0,'Assets'),
(4,0,'Libraries'),
(5,0,'Cash/Bank');


INSERT INTO syllabusType ( id ,  createdById ,  lastUpdatedById ,  version ,  status ,  syllabusTypeDescription ,  syllabusTypeName ) VALUES (1, 0, 0, 0, 'Y', 'Central Board School Education', 'CBSE');

INSERT INTO syllabusType ( id ,  createdById ,  lastUpdatedById ,  version ,  status ,  syllabusTypeDescription ,  syllabusTypeName ) VALUES (2, 0, 0, 0, 'Y', 'State Board School Education', 'SBSE');

INSERT INTO syllabusType ( id ,  createdById ,  lastUpdatedById ,  version ,  status ,  syllabusTypeDescription ,  syllabusTypeName ) VALUES (3, 0, 0, 0, 'Y', 'Indian Certificate of Secondary Education', 'ICSE');
INSERT INTO syllabusType ( id ,  createdById ,  lastUpdatedById ,  version ,  status ,  syllabusTypeDescription ,  syllabusTypeName ) VALUES (4, 0, 0, 0, 'Y', '', 'Nigerian');




INSERT INTO financialYear(id,createdById,createdDate,version,endDate,startDate,status,yearName,orgId) VALUES
(1,1,'2016-02-10 12:24:25',0,'2016-03-31 00:00:00','2016-04-01 00:00:00','Y','2015-16',0);

ALTER TABLE student DROP COLUMN suspendDays , DROP COLUMN blackedOrSuspendToDate , DROP COLUMN blackedOrSuspendFromDate;

alter table studySubject drop column subjectType;


drop PROCEDURE topStudentsMonthlyAttendance;
drop PROCEDURE topTeacherPerformanceDetails;
drop  PROCEDURE topStudentMarksDetails;
drop PROCEDURE topStudentsDailyAttendance;


drop table topStudentDailyAttendancePercentageCal;
drop table studentDailyAttendancePercentageCal;

drop table studentMarksPercentageCalc;
drop table topStudentMarksPercentageCal;

drop table staffPefromancePercentageCalc;
drop table topStaffPeromancePercentageCal;

drop table studentMonthlyAttendancePercentageCal;
drop table topStudentMonthlyAttendancePercentageCal;
 


select * from topStudentDailyAttendancePercentageCal;
select * from studentDailyAttendancePercentageCal;

select * from studentMarksPercentageCalc;
select * from topStudentMarksPercentageCal;


select * from staffPefromancePercentageCalc;
select * from topStaffPeromancePercentageCal;


select * from studentMonthlyAttendancePercentageCal;
select * from topStudentMonthlyAttendancePercentageCal;


Create table studentMarksPercentageCalc (id INT NOT NULL AUTO_INCREMENT,createdDate varchar(20),studId INT NOT NULL,totalPersentage Double DEFAULT 0,examTypeId INT DEFAULT 0,totalStudentMarks Double DEFAULT 0,custId BIGINT(20),academicYearId BIGINT(20),studName VARCHAR(50) DEFAULT NULL,admisNo VARCHAR(50) DEFAULT NULL,classAndSection VARCHAR(50) DEFAULT NULL, primary key (id)); 
Create table topStudentMarksPercentageCal(id INT NOT NULL AUTO_INCREMENT,createdDate varchar(20),studId INT NOT NULL,topPercentage Double DEFAULT 0,custId BIGINT(20),academicYearId BIGINT(20),studNames VARCHAR(50) DEFAULT NULL,admisNos VARCHAR(50) DEFAULT NULL,classAndSections VARCHAR(50) DEFAULT NULL,primary key (id)); 
   

Create table staffPefromancePercentageCalc (id INT NOT NULL AUTO_INCREMENT,createdDate varchar(20),staffId INT NOT NULL,examTypeId INT DEFAULT 0,totalStaffPerformance Double DEFAULT 0, custId BIGINT(20), academicYearId BIGINT(20),staffName VARCHAR(50) DEFAULT NULL, primary key (id));
Create table topStaffPeromancePercentageCal (id INT NOT NULL AUTO_INCREMENT,createdDate varchar(20),staffId INT NOT NULL,topStaffPercentage Double DEFAULT 0, custId BIGINT(20), academicYearId BIGINT(20),staffNames VARCHAR(50) DEFAULT NULL,primary key (id)); 
   
   
Create table studentMonthlyAttendancePercentageCal (id INT NOT NULL AUTO_INCREMENT,createdDate varchar(20),studId INT NOT NULL,totalPercentage Double DEFAULT 0.0,studName  VARCHAR(50) DEFAULT NULL,custId BIGINT(20),academicYearId BIGINT(20),admNo VARCHAR(50),classAndSection VARCHAR(50), primary key (id)); 
Create table topStudentMonthlyAttendancePercentageCal (id INT NOT NULL AUTO_INCREMENT,createdDate varchar(20),studId INT NOT NULL,topStudentMonthlyAttendancePercentage Double DEFAULT 0,studsName VARCHAR(50) DEFAULT NULL,custId BIGINT(20),academicYearId BIGINT(20),admNos VARCHAR(50),classAndSections VARCHAR(50),primary key (id));
   
   
CREATE  TABLE studentDailyAttendancePercentageCal(id INT NOT NULL AUTO_INCREMENT,createdDate varchar(20),studId bigint(20),studyClassId bigint(20),academicYearId bigint(20),custId bigint(20),stuAbsentCount Double DEFAULT 0.0,stuPresentCount Double DEFAULT 0.0,totalWorkingDays INT,monthId INT,totalPercentage Double DEFAULT 0.0,studName VARCHAR(50) DEFAULT NULL,admisNo VARCHAR(50) DEFAULT NULL,classAndSection VARCHAR(50) DEFAULT NULL,primary key (id));
CREATE  TABLE topStudentDailyAttendancePercentageCal(id INT NOT NULL AUTO_INCREMENT,createdDate varchar(20),studId bigint(20),studyClassIds bigint(20),academicYearIds bigint(20),custIds bigint(20),stuAbsentCounts Double DEFAULT 0.0,stuPresentCounts Double DEFAULT 0.0,totalWorkingDayss INT DEFAULT 0 ,monthIds INT,topTotalPercentage Double DEFAULT 0.0,studNames VARCHAR(50) DEFAULT NULL,admisNos VARCHAR(50) DEFAULT NULL,classAndSections VARCHAR(50) DEFAULT NULL,primary key (id));


/*Updating all parents username with mobilenumber who are having mobile number*/
update Account a left join Person p on a.personId=p.id left join UserRoles ur on ur.userId= a.id 
set a.username=RIGHT(p.mobileNumber, 10) 
where  p.mobileNumber is not null and ur.roleId=7;



INSERT INTO  Country  ( id ,  countryCode ,  countryName ,  phonecode , noOfStates ) VALUES
(1, 'AF', 'Afghanistan', 93,0),
(2, 'AL', 'Albania', 355,0),
(3, 'DZ', 'Algeria',  213,0),
(4, 'AS', 'American Samoa',  1684,0),
(5, 'AD', 'Andorra', 376,0),
(6, 'AO', 'Angola', 244,0),
(7, 'AI', 'Anguilla',  1264,0),
(8, 'AQ', 'Antarctica',  0,0),
(9, 'AG', 'Antigua and Barbuda', 1268,0),
(10, 'AR', 'Argentina', 54,0),
(11, 'AM', 'Armenia',374,0),
(12, 'AW', 'Aruba', 297,0),
(13, 'AU', 'Australia',61,0),
(14, 'AT', 'Austria',  43,0),
(15, 'AZ', 'Azerbaijan', 994,0),
(16, 'BS', 'Bahamas', 1242,0),
(17, 'BH', 'Bahrain', 973,0),
(18, 'BD', 'Bangladesh', 880,0),
(19, 'BB', 'Barbados', 1246,0),
(20, 'BY', 'Belarus', 375,0),
(21, 'BE', 'Belgium', 32,0),
(22, 'BZ', 'Belize',  501,0),
(23, 'BJ', 'Benin', 229,0),
(24, 'BM', 'Bermuda', 1441,0),
(25, 'BT', 'Bhutan',  975,0),
(26, 'BO', 'Bolivia',  591,0),
(27, 'BA', 'Bosnia and Herzegovina', 387,0),
(28, 'BW', 'Botswana',  267,0),
(29, 'BV', 'Bouvet Island', 0,0),
(30, 'BR', 'Brazil', 55,0),
(31, 'IO', 'British Indian Ocean Territory',246,0),
(32, 'BN', 'Brunei Darussalam', 673,0),
(33, 'BG', 'Bulgaria', 359,0),
(34, 'BF', 'Burkina Faso', 226,0),
(35, 'BI', 'Burundi', 257,0),
(36, 'KH', 'Cambodia', 855,0),
(37, 'CM', 'Cameroon',  237,0),
(38, 'CA', 'Canada',  1,0),
(39, 'CV', 'Cape Verde', 238,0),
(40, 'KY', 'Cayman Islands', 1345,0),
(41, 'CF', 'Central African Republic', 236,0),
(42, 'TD', 'Chad', 235,0),
(43, 'CL', 'Chile',  56,0),
(44, 'CN', 'China', 86,0),
(45, 'CX', 'Christmas Island', 61,0),
(46, 'CC', 'Cocos (Keeling) Islands', 672,0),
(47, 'CO', 'Colombia', 57,0),
(48, 'KM', 'Comoros', 269,0),
(49, 'CG', 'Congo', 242,0),
(50, 'CD', 'Congo, the Democratic Republic of the', 242,0),
(51, 'CK', 'Cook Islands', 682,0),
(52, 'CR', 'Costa Rica', 506,0),
(53, 'CI', 'Cote D''Ivoire', 225,0),
(54, 'HR', 'Croatia', 385,0),
(55, 'CU', 'Cuba', 53,0),
(56, 'CY', 'Cyprus', 357,0),
(57, 'CZ', 'Czech Republic', 420,0),
(58, 'DK', 'Denmark',  45,0),
(59, 'DJ', 'Djibouti',  253,0),
(60, 'DM', 'Dominica', 1767,0),
(61, 'DO', 'Dominican Republic', 1809,0),
(62, 'EC', 'Ecuador', 593,0),
(63, 'EG', 'Egypt',  20,0),
(64, 'SV', 'El Salvador', 503,0),
(65, 'GQ', 'Equatorial Guinea',  240,0),
(66, 'ER', 'Eritrea', 291,0),
(67, 'EE', 'Estonia', 372,0),
(68, 'ET', 'Ethiopia',251,0),
(69, 'FK', 'Falkland Islands (Malvinas)',  500,0),
(70, 'FO', 'Faroe Islands', 298,0),
(71, 'FJ', 'Fiji', 679,0),
(72, 'FI', 'Finland',  358,0),
(73, 'FR', 'France',  33,0),
(74, 'GF', 'French Guiana', 594,0),
(75, 'PF', 'French Polynesia',  689,0),
(76, 'TF', 'French Southern Territories', 0,0),
(77, 'GA', 'Gabon', 241,0),
(78, 'GM', 'Gambia', 220,0),
(79, 'GE', 'Georgia',  995,0),
(80, 'DE', 'Germany',  49,0),
(81, 'GH', 'Ghana', 233,0),
(82, 'GI', 'Gibraltar', 350,0),
(83, 'GR', 'Greece',  30,0),
(84, 'GL', 'Greenland', 299,0),
(85, 'GD', 'Grenada', 1473,0),
(86, 'GP', 'Guadeloupe',  590,0),
(87, 'GU', 'Guam',  1671,0),
(88, 'GT', 'Guatemala',  502,0),
(89, 'GN', 'Guinea',  224,0),
(90, 'GW', 'Guinea-Bissau',  245,0),
(91, 'GY', 'Guyana', 592,0),
(92, 'HT', 'Haiti', 509,0),
(93, 'HM', 'Heard Island and Mcdonald Islands', 0,0),
(94, 'VA', 'Holy See (Vatican City State)', 39,0),
(95, 'HN', 'Honduras', 504,0),
(96, 'HK', 'Hong Kong',  852,0),
(97, 'HU', 'Hungary',  36,0),
(98, 'IS', 'Iceland', 354,0),
(99, 'IN', 'India', 91,0),
(100, 'ID', 'Indonesia', 62,0),
(101, 'IR', 'Iran, Islamic Republic of',  98,0),
(102, 'IQ', 'Iraq',  964,0),
(103, 'IE', 'Ireland',  353,0),
(104, 'IL', 'Israel',  972,0),
(105, 'IT', 'Italy',  39,0),
(106, 'JM', 'Jamaica',  1876,0),
(107, 'JP', 'Japan', 81,0),
(108, 'JO', 'Jordan', 962,0),
(109, 'KZ', 'Kazakhstan',  7,0),
(110, 'KE', 'Kenya',  254,0),
(111, 'KI', 'Kiribati',  686,0),
(112, 'KP', 'Korea, Democratic People''s Republic of',  850,0),
(113, 'KR', 'Korea, Republic of',  82,0),
(114, 'KW', 'Kuwait',965,0),
(115, 'KG', 'Kyrgyzstan', 996,0),
(116, 'LA', 'Lao People''s Democratic Republic',  856,0),
(117, 'LV', 'Latvia', 371,0),
(118, 'LB', 'Lebanon', 961,0),
(119, 'LS', 'Lesotho',  266,0),
(120, 'LR', 'Liberia', 231,0),
(121, 'LY', 'Libyan Arab Jamahiriya',  218,0),
(122, 'LI', 'Liechtenstein',  423,0),
(123, 'LT', 'Lithuania',  370,0),
(124, 'LU', 'Luxembourg',  352,0),
(125, 'MO', 'Macao', 853,0),
(126, 'MK', 'Macedonia, the Former Yugoslav Republic of', 389,0),
(127, 'MG', 'Madagascar',  261,0),
(128, 'MW', 'Malawi',  265,0),
(129, 'MY', 'Malaysia',  60,0),
(130, 'MV', 'Maldives',  960,0),
(131, 'ML', 'Mali',  223,0),
(132, 'MT', 'Malta',  356,0),
(133, 'MH', 'Marshall Islands',  692,0),
(134, 'MQ', 'Martinique',  596,0),
(135, 'MR', 'Mauritania',222,0),
(136, 'MU', 'Mauritius',  230,0),
(137, 'YT', 'Mayotte',  269,0),
(138, 'MX', 'Mexico',  52,0),
(139, 'FM', 'Micronesia, Federated States of', 691,0),
(140, 'MD', 'Moldova, Republic of', 373,0),
(141, 'MC', 'Monaco', 377,0),
(142, 'MN', 'Mongolia',  976 ,0),
(143, 'MS', 'Montserrat', 166,0),
(144, 'MA', 'Morocco',  212,0),
(145, 'MZ', 'Mozambique',  258,0),
(146, 'MM', 'Myanmar',  95,0),
(147, 'NA', 'Namibia', 264,0),
(148, 'NR', 'Nauru',  674,0),
(149, 'NP', 'Nepal',  977,0),
(150, 'NL', 'Netherlands',  31,0),
(151, 'AN', 'Netherlands Antilles',  599,0),
(152, 'NC', 'New Caledonia',  687,0),
(153, 'NZ', 'New Zealand', 64,0),
(154, 'NI', 'Nicaragua',  505,0),
(155, 'NE', 'Niger',  227,0),
(156, 'NG', 'Nigeria', 234,0),
(157, 'NU', 'Niue', 683,0),
(158, 'NF', 'Norfolk Island',  672,0),
(159, 'MP', 'Northern Mariana Islands', 1670,0),
(160, 'NO', 'Norway',  47,0),
(161, 'OM', 'Oman',  968,0),
(162, 'PK', 'Pakistan', 92,0),
(163, 'PW', 'Palau',  680,0),
(164, 'PS', 'Palestinian Territory, Occupied',970,0),
(165, 'PA', 'Panama',  507,0),
(166, 'PG', 'Papua New Guinea',  675,0),
(167, 'PY', 'Paraguay', 595,0),
(168, 'PE', 'Peru', 51,0),
(169, 'PH', 'Philippines', 63,0),
(170, 'PN', 'Pitcairn', 0,0),
(171, 'PL', 'Poland', 48,0),
(172, 'PT', 'Portugal',  351,0),
(173, 'PR', 'Puerto Rico',1787,0),
(174, 'QA', 'Qatar',  974,0),
(175, 'RE', 'Reunion',  262,0),
(176, 'RO', 'Romania',  40,0),
(177, 'RU', 'Russian Federation',  70,0),
(178, 'RW', 'Rwanda', 250,0),
(179, 'SH', 'Saint Helena', 290,0),
(180, 'KN', 'Saint Kitts and Nevis', 1869,0),
(181, 'LC', 'Saint Lucia',  1758,0),
(182, 'PM', 'Saint Pierre and Miquelon', 508,0),
(183, 'VC', 'Saint Vincent and the Grenadines', 1784,0),
(184, 'WS', 'Samoa',  684,0),
(185, 'SM', 'San Marino',  378,0),
(186, 'ST', 'Sao Tome and Principe', 239,0),
(187, 'SA', 'Saudi Arabia',966,0),
(188, 'SN', 'Senegal',  221,0),
(189, 'CS', 'Serbia and Montenegro', 381,0),
(190, 'SC', 'Seychelles', 248,0),
(191, 'SL', 'Sierra Leone',  232,0),
(192, 'SG', 'Singapore',  65,0),
(193, 'SK', 'Slovakia', 421,0),
(194, 'SI', 'Slovenia', 386,0),
(195, 'SB', 'Solomon Islands',  677,0),
(196, 'SO', 'Somalia',  252,0),
(197, 'ZA', 'South Africa',  27,0),
(198, 'GS', 'South Georgia and the South Sandwich Islands', 0,0),
(199, 'ES', 'Spain', 34,0),
(200, 'LK', 'Sri Lanka', 94,0),
(201, 'SD', 'Sudan',  249,0),
(202, 'SR', 'Suriname',  597,0),
(203, 'SJ', 'Svalbard and Jan Mayen',  47,0),
(204, 'SZ', 'Swaziland',  268,0),
(205, 'SE', 'Sweden',  46,0),
(206, 'CH', 'Switzerland',  41,0),
(207, 'SY', 'Syrian Arab Republic',  963,0),
(208, 'TW', 'Taiwan, Province of China',  886,0),
(209, 'TJ', 'Tajikistan',  992,0),
(210, 'TZ', 'Tanzania, United Republic of',  255,0),
(211, 'TH', 'Thailand',  66,0),
(212, 'TL', 'Timor-Leste',  670,0),
(213, 'TG', 'Togo',  228,0),
(214, 'TK', 'Tokelau', 690,0),
(215, 'TO', 'Tonga',  676,0),
(216, 'TT', 'Trinidad and Tobago',  1868,0),
(217, 'TN', 'Tunisia',  216,0),
(218, 'TR', 'Turkey',  90,0),
(219, 'TM', 'Turkmenistan', 7370,0),
(220, 'TC', 'Turks and Caicos Islands',  1649,0),
(221, 'TV', 'Tuvalu',  688,0),
(222, 'UG', 'Uganda',  256,0),
(223, 'UA', 'Ukraine',380,0),
(224, 'AE', 'United Arab Emirates',  971,0),
(225, 'GB', 'United Kingdom', 44,0),
(226, 'US', 'United States',  1,0),
(227, 'UM', 'United States Minor Outlying Islands', 1,0),
(228, 'UY', 'Uruguay',  598,0),
(229, 'UZ', 'Uzbekistan',  998,0),
(230, 'VU', 'Vanuatu',  678,0),
(231, 'VE', 'Venezuela',  58,0),
(232, 'VN', 'Viet Nam', 84,0),
(233, 'VG', 'Virgin Islands, British',  1284,0),
(234, 'VI', 'Virgin Islands, U.s.',  1340,0),
(235, 'WF', 'Wallis and Futuna',  681,0),
(236, 'EH', 'Western Sahara',  212,0),
(237, 'YE', 'Yemen',  967,0),
(238, 'ZM', 'Zambia',  260,0),
(239, 'ZW', 'Zimbabwe', 263,0);
UPDATE Country set status="Y";
UPDATE Country set createdById=0;
UPDATE Country set lastUpdatedById=0;
UPDATE Country set version=0 where version is null or version !='';

update Person SET mobileNumber= RIGHT(mobileNumber,10);
update Person SET studentMobile= RIGHT(studentMobile,10);
update onlineApplicationDetails SET mobilenumber= RIGHT(mobilenumber,10);
update hostel SET mobilenumber= RIGHT(mobilenumber,10);
update hostel SET contactNumber= RIGHT(contactNumber,10);
update customer SET contactNumber= RIGHT(contactNumber,10);
update customer SET mobileNumber= RIGHT(mobileNumber,10);

update route SET status='Y';
update routeBoardingpoints SET status='Y';

UPDATE studentDailyAttendance SET afternoonSession='' where present='';
UPDATE studentDailyAttendance SET afternoonSession='N' where present='N';


UPDATE staffDailyAttendance SET afternoonSession='' where present='';
UPDATE staffDailyAttendance SET afternoonSession='N' where present='N';

UPDATE  event  SET  eventFor  = 'all';-- this is for only one time, because we need to get previous events.
update circular set circularStatus="I" where circularStatus is null;

UPDATE meetingSchedules ms INNER JOIN staff s ON s.id =ms.staffId SET ms.custId=s.custId;
UPDATE meetingSlots ms INNER JOIN meetingSchedules msc ON msc.id =ms.meetingScheduleId SET ms.custId=msc.custId; 

update Account set secondaryUsername=id;


update address set countryId = 99 where countryId=0 and id in(select addressId from customer);-- this query for updaing the india country id in customer address

ALTER TABLE Account DROP INDEX UK_hirqei45ntg3u198xicxc1n77 ;
ALTER TABLE Account ADD UNIQUE INDEX secondaryUsername_UNIQUE (secondaryUsername ASC) ;

INSERT INTO  autoReportsTypes  (id,reportName,reportDescription) VALUES (1,'Monthly Performance Report','Deliver this report by starting of every month to all the staff. In this report consist of top 5 students in attedance, top 5 teachers performance with respective to exam and top 5 student performance');
INSERT INTO  autoReportsTypes  (id,reportName,reportDescription) VALUES (2,'Monthly Fee Report ','Email send to Admin,Principal and Financier on sarting of every month with fee details');
INSERT INTO  autoReportsTypes  (id,reportName,reportDescription) VALUES (3,'Birthday Wishes','SMS & Email will send Staff & Student birthdays');
INSERT INTO  autoReportsTypes  (id,reportName,reportDescription) VALUES (4,'Fee Reminder','SMS & Email will send based term fonfigureation');
INSERT INTO  autoReportsTypes  (id,reportName,reportDescription) VALUES (5,'Exam Schedules Reminder','SMS & Email will send based on Exam Shedule configuration');


UPDATE Address SET stateId=1 WHERE state='AP';
UPDATE Address SET stateId=2 WHERE state='AR';
UPDATE Address SET stateId=3 WHERE state='AS';
UPDATE Address SET stateId=4 WHERE state='BR';
UPDATE Address SET stateId=5 WHERE state='CG';
UPDATE Address SET stateId=6 WHERE state='GA';
UPDATE Address SET stateId=7 WHERE state='GJ';
UPDATE Address SET stateId=8 WHERE state='HR';
UPDATE Address SET stateId=9 WHERE state='HP';
UPDATE Address SET stateId=10 WHERE state='JK';
UPDATE Address SET stateId=11 WHERE state='JH';
UPDATE Address SET stateId=12 WHERE state='KA';
UPDATE Address SET stateId=13 WHERE state='KL';
UPDATE Address SET stateId=14 WHERE state='MP';
UPDATE Address SET stateId=15 WHERE state='MH';
UPDATE Address SET stateId=16 WHERE state='MN';
UPDATE Address SET stateId=17 WHERE state='ML';
UPDATE Address SET stateId=18 WHERE state='MZ';
UPDATE Address SET stateId=19 WHERE state='NL';
UPDATE Address SET stateId=20 WHERE state='OR';
UPDATE Address SET stateId=21 WHERE state='PB';
UPDATE Address SET stateId=22 WHERE state='RJ';
UPDATE Address SET stateId=23 WHERE state='SK';
UPDATE Address SET stateId=24 WHERE state='TN';
UPDATE Address SET stateId=25 WHERE state='TR';
UPDATE Address SET stateId=26 WHERE state='UK';
UPDATE Address SET stateId=27 WHERE state='UP';
UPDATE Address SET stateId=28 WHERE state='WB';
UPDATE Address SET stateId=29 WHERE state='AN';
UPDATE Address SET stateId=30 WHERE state='CH';
UPDATE Address SET stateId=31 WHERE state='DN';
UPDATE Address SET stateId=32 WHERE state='DD';
UPDATE Address SET stateId=33 WHERE state='DL';
UPDATE Address SET stateId=34 WHERE state='LD';
UPDATE Address SET stateId=35 WHERE state='PY';
UPDATE Address SET stateId=36 WHERE state='TG';
UPDATE Address SET stateId=37 WHERE state='NA';

--finAccountStatement
INSERT INTO finAccountStatement(id,createdById,lastUpdatedById,statementName,statmentCode) VALUES (1,0,0,'Asserts', 'AS');
INSERT INTO finAccountStatement(id,createdById,lastUpdatedById,statementName,statmentCode) VALUES (2,  0, 0, 'Expenduture', 'EX');
INSERT INTO finAccountStatement(id,createdById,lastUpdatedById,statementName,statmentCode) VALUES (3, 0, 0, 'Income', 'IN');
INSERT INTO finAccountStatement(id,createdById,lastUpdatedById,statementName,statmentCode ) VALUES (4,  0, 0, 'Libalities', 'LI');
--finAccountType
INSERT INTO finAccountType(id,createdById,lastUpdatedById,accountCode,accountType)VALUES (1,  0, 0, 'A', 'Account');
INSERT INTO finAccountType(id,createdById,lastUpdatedById,accountCode,accountType) VALUES (2,  0, 0, 'V', 'Vendor');
--finAccountCategory
INSERT INTO finAccountCategory(id,createdById,lastUpdatedById,cartegoryName,custId,statementId,accountTypeId ) VALUES (1,  0, 0, 'Cash And  Bank', 1, 1, 1);
INSERT INTO finAccountCategory(id,createdById,lastUpdatedById,cartegoryName,custId,statementId,accountTypeId ) VALUES (2,  0, 0, 'Income / Loss', 1, 4, 1);
--finAccountDetails
INSERT INTO  finAccountDetails(id,createdById,lastUpdatedById,accountName,custId,accountTypeId,finAccountCategoryId ) VALUES (1,  0, 0, 'CASH IN HAND', 1, 1, 1);
INSERT INTO  finAccountDetails(id,createdById,lastUpdatedById,accountName,custId,accountTypeId,finAccountCategoryId ) VALUES (2,  0, 0, 'Income / Loss Account', 1, 1, 1);

ALTER TABLE studentDailyAttendance 
ADD INDEX studentId_studentDailyAttendance (studentId ASC),
ADD INDEX academicYearId_studentDailyAttendance (academicYearId ASC),
ADD INDEX custId_studentDailyAttendance (custId ASC);
ALTER TABLE schoolHolidays 
ADD INDEX holidayDate_schoolHolidays (holidayDate ASC);


INSERT INTO `organizationTypes` (`id`, `createdDate`, `lastAccessDate`, `lastUpdatedDate`,  `organizationType`, `createdById`, `lastUpdatedById`) VALUES ('7', '2016-12-14 00:00:00', '2016-12-14 00:00:00', '2016-12-14 00:00:00', 'Private', '0', '0');

alter table customer modify column schoolCode varchar (20);

alter table academicYear drop column addStudentsSameAdmissionNumber;


-- Need To remove columns in Server

-- Table - Staff
-- isTimetableUploaded

-- Table - Study Class
-- isClassTimetableUploaded;

UPDATE `autoReportsTypes` SET `reportDescription`='Email  will be sent to Admin,Principal and Financier on starting of every month with fee details' WHERE `id`='2';
UPDATE `autoReportsTypes` SET `reportDescription`='SMS & Email will be sent to Staff & Student birthdays' WHERE `id`='3';
UPDATE `autoReportsTypes` SET `reportDescription`='SMS & Email will be sent based on the term configuration' WHERE `id`='4';
UPDATE `autoReportsTypes` SET `reportDescription`='SMS & Email will be sent based on Exam Schedule configuration' WHERE `id`='5';



-- 173 Cust ID is STERLING ENGLISH SCHOOL
INSERT INTO `smsServiceProviders` (`id`, `createdById`, `createdDate`, `lastAccessDate`, `lastUpdatedById`, `lastUpdatedDate`,  `activeUrl`, `serviceProvider`, `url`) 
VALUES ('4', '1', '2017-06-16 16:11:18', '2017-06-16 16:11:18', '1', '2017-06-16 16:11:18',  'Y', 'mobicomm', 'http://103.233.79.246//submitsms.jsp');

UPDATE `customer` SET `smsServiceProviderId`='4' WHERE `id`='173';
UPDATE `customer` SET `sender`='ESTLNG' WHERE `id`='173';

UPDATE `smsServiceProviders` SET `name`='ValueFirst', `password`='upper123', `userName`='upperroom' WHERE `id`='1';
UPDATE `smsServiceProviders` SET `name`='MsgClub',`providerKey`='53b5b4f961b86fea1c3aeebbe5dfb6e' WHERE `id`='2';
UPDATE `smsServiceProviders` SET `name`='InternationalValueFirst', `password`='hynivaxml12', `userName`='hynivaxml' WHERE `id`='3';
UPDATE `smsServiceProviders` SET `name`='mobicomm', `password`='123123', `userName`='SterGES', `providerKey`='f85b1f15baXX' WHERE `id`='4';
UPDATE `smsServiceProviders` SET `isCustomerSpecific`='Y' WHERE `id`='4';


-- Adding country currency records
INSERT INTO `CountryCurrency` (`id`, `createdById`, `createdDate`, `lastAccessDate`, `lastUpdatedById`, `lastUpdatedDate`, `countryId`, `currencyName`, `currencySymbol`, `currencyWord`) VALUES (1, 1, '2017-06-30 00:00:00', '2017-06-30 00:00:00', 1, '2017-06-30 00:00:00', 1, 'INDIAN RUPEE SIGN', '&#8377;', 'Rupees');

INSERT INTO `CountryCurrency` (`id`, `createdById`, `createdDate`, `lastAccessDate`, `lastUpdatedById`, `lastUpdatedDate`, `countryId`, `currencyName`, `currencySymbol`, `currencyWord`) VALUES (2, 1, '2017-06-30 00:00:00', '2017-06-30 00:00:00', 1, '2017-06-30 00:00:00', 1, 'NAIRA SIGN', '&#8358;', 'Naira(N)');


UPDATE `Country` SET `countryCurrencyId`=1 WHERE `id`='99';

UPDATE `Country` SET `countryCurrencyId`=2 WHERE `id`='156';

alter table ParentsEmploymentDetails drop column fatherAnnualIncome;

alter table customer drop column customerStatus;


alter table schoolTerms drop column mobileContentDesc;
alter table schoolTerms drop column mailContentDesc;

INSERT INTO Role (id, createdById, createdDate, lastUpdatedById, lastUpdatedDate,  description,name)
VALUES (53, 1044, '2017-08-28 00:00:00', 1044, '2017-08-28 00:00:00',  'Admin Coordinator', 'ROLE_ADMIN_COORDINATOR');

INSERT INTO loginAccessbilityRoles(createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,customerId,roleId,status,androidStatus) 
VALUES(1044,'2017-08-28 00:00:00','2017-08-28 00:00:00',1044,'2017-08-28 00:00:00', 1 ,53,'Y','Y');

update studentFeePaidDetails set committedFeeStatus='N' where committedFeeStatus is null;

INSERT INTO Role (id, createdById, createdDate, lastUpdatedById, lastUpdatedDate,  description,name)
VALUES (54, 1044, '2017-09-06 00:00:00', 1044, '2017-09-06 00:00:00', 'Store Keeper', 'ROLE_STOREKEEPER');

INSERT INTO loginAccessbilityRoles(createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,customerId,roleId,status,androidStatus) 
VALUES(1044,'2017-09-06 00:00:00','2017-09-06 00:00:00',1044,'2017-09-06 00:00:00', 1 ,54,'Y','Y');

ALTER TABLE student CHANGE COLUMN rollNumber rollNumber VARCHAR(15) NOT NULL ;

ALTER TABLE  schoolHolidays 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE schoolHolidays 
CHANGE COLUMN holidayDate holidayDate VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL ;

INSERT INTO Ref_HouseType (id, createdById, createdDate, lastUpdatedById, lastUpdatedDate,  type,description)
VALUES (1, 1044, '2017-12-25 00:00:00', 1044, '2017-12-25 00:00:00',  'Blue', 'House with blue colour');

INSERT INTO Ref_HouseType (id, createdById, createdDate, lastUpdatedById, lastUpdatedDate,  type,description)
VALUES (2, 1044, '2017-12-25 00:00:00', 1044, '2017-12-25 00:00:00',  'Green', 'House with green colour');

INSERT INTO Ref_HouseType (id, createdById, createdDate, lastUpdatedById, lastUpdatedDate,  type,description)
VALUES (3, 1044, '2017-12-25 00:00:00', 1044, '2017-12-25 00:00:00',  'Red', 'House with red colour');

INSERT INTO Ref_HouseType (id, createdById, createdDate, lastUpdatedById, lastUpdatedDate,  type,description)
VALUES (4, 1044, '2017-12-25 00:00:00', 1044, '2017-12-25 00:00:00',  'Yellow', 'House with yellow colour');

-- ALTER TABLE storeData DROP COLUMN  storeKeeperName ;

INSERT INTO Role (id, createdById, createdDate, lastUpdatedById, lastUpdatedDate,  description,name)
VALUES (55, 1044, '2018-01-10 00:00:00', 1044, '2018-01-10 00:00:00', 'Receptionist', 'ROLE_RECEPTIONIST');

INSERT INTO loginAccessbilityRoles(createdById,createdDate,lastAccessDate,lastUpdatedById,lastUpdatedDate,customerId,roleId,status,androidStatus) 
VALUES(1044,'2018-01-10 00:00:00','2018-01-10 00:00:00',1044,'2018-01-10 00:00:00', 1 ,55,'Y','Y');


INSERT INTO Role (id, createdById, createdDate, lastUpdatedById, lastUpdatedDate,  description,name)
VALUES (56, 1044, '2018-02-06 00:00:00', 1044, '2018-02-06 00:00:00', 'Staff Nurse', 'ROLE_STAFF_NURSE');

--Parent Income Ranges
delete from parentIncomeRange;

insert into parentIncomeRange (id,maxRange,minRange,rangeValues) values(1,'49999','0','Range 1 (0-49999)');
insert into parentIncomeRange (id,maxRange,minRange,rangeValues) values(2,'99999','50000','Range 2 (50000-99999)');
insert into parentIncomeRange (id,maxRange,minRange,rangeValues) values(3,'249000','100000','Range 3 (100000-249000)');
insert into parentIncomeRange (id,maxRange,minRange,rangeValues) values(4,'499999','250000','Range 4 (250000-499999)');
insert into parentIncomeRange (id,maxRange,minRange,rangeValues) values(5,'699999','500000','Range 5 (500000-699999)');
insert into parentIncomeRange (id,maxRange,minRange,rangeValues) values(6,'749000','700000','Range 6 (700000-749000)');
insert into parentIncomeRange (id,maxRange,minRange,rangeValues) values(6,'1000000','800000','Range 7 (800000-1000000 and Above)');