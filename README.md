QUIZ BACK APPLICATION  (SPRING 5 COURSE AT ATSISTEMAS)

The main goal of this application is provide to users in a course a way to make course quiz providing a administration service such as
add user, course and questionanrie for course. It also save the responses for each quuestionnaire for each course and user 
.
The model take into account next entities: 

*  User for provide alumns  personal details(name, email,..) to be registrated on Course
*  Course with the name of course and start and en date of it
*  Questionnarie for a course to provide questions and answer to be filled by users.
*  Question that will be part of questionnarie and that have linked one or more answers. Only one is assumed to be the correct
*  Answer mapped to Question
*  Result of a questionarie that will contain for each course, questionarie and user uses response with moptes
*  Also Questionarie and Question could be contains Tga and search questionarie and question y tag

   Main Goal and responsabilities requested:

 	+ COMPLETE AT LEAST 3 CRUDS (QUESTION, USER, QUESTIONNAIRE)
 
 	+ COURSE CREATION (USER + QUESTIONNARIE + COURSE). DO ALSO MOCKITO TEST FOR COURSE LAYERS
 
 	+ GET THE RESULS FILTER BY COURSE
 
 	+ GET THE QUESTIONNARIE REQUESTING QUESTION -ANSWER ONE BY ONE.USER MUST RESPONSE, SAVE THE ANSWER AND THEN GET NEXT QUESTION.
 
 	+ GET THE QUESTIONNARIE SHOWING RAMDOM QUESTION -ANSWER ONE BY ONE. USER MUST RESPONSE, SAVE THE ANSWER AND THEN GET NEXT QUESTION.
 
 	+ GET ALL QUESTION-ANSWER FOR QUESTIONNARIE(WITHOUT PAGINATION ??)	
 
 ************************************************
 ASUMPTIONS NOTE OF MY QUIZ BACK APP

+ IF QUESTIONNAIRE DOES NOT EXIST IT IS ALLOWED TO CREATE  IT WHEN COURSE IS TRYING TO ADD A NEW QUESTIONNAIRE TO COURSE
+ IF AN ANSWER DOES NOT EXIST IT IS ALLOWED TO CREATED ON FLY ANSWER WHEN QUESTION IS ADDING A NEW ANSWER.
+ FOR A QUESTION, SUITABLES ANSWERS ONLY ONE IS THE CORRECT ONE. NOT ALLOWED MULTIPLES ANSWERS
+ WHEN AN USER IT ADDED TO A COURSE IS NOT ALLOWED TO CREATE USERS ON THE FLY IF USER PREVIOUSLY USER DOES NOT EXIST 
+ WHEN A QUESTIONNARIE IS DELETED LINKED QUESTIONS AND ANSWERS WILL NOT BE DELETED TO BE REUSABLE FOR OTHER QUESTIONNARIES
************************************************

-------------------------------------------------------------------------------------------------------------------------
## Table of Details
-------------------------------------------------------------------------------------------------------------------------
* [<i>COMPLETE AT LEAST 3 CRUDS (QUESTION, USER, QUESTIONNAIRE)</i>]
* [<i>COURSE CREATION (USER + QUESTIONNARIE + COURSE). DO ALSO MOCKITO TEST FOR COURSE LAYERS</i>]
* [<i>GET THE RESULS FILTER BY COURSE</i>]
* [<i>GET THE QUESTIONNARIE REQUESTING QUESTION -ANSWER ONE BY ONE.USER MUST RESPONSE, SAVE THE ANSWER AND THEN GET NEXT QUESTION</i>]
* [<i>GET THE QUESTIONNARIE SHOWING RAMDOM QUESTION -ANSWER ONE BY ONE. USER MUST RESPONSE, SAVE THE ANSWER AND THEN GET NEXT QUESTION.</i>]
* [<i>GET ALL QUESTION-ANSWER FOR QUESTIONNARIE(WITHOUT PAGINATION ??)</i>]

**************************************************************************************************************************
* [<i>COMPLETE AT LEAST 3 CRUDS (QUESTION, USER, QUESTIONNAIRE)</i>]
**************************************************************************************************************************

******************************************************  USER  ********************************************
<p>
	<ul>

<li>a) UserDAO access to Repository map to User Entity mainly. </li>
	-Create(C)	
	-Read(R)
	-Update(C)
	-Delete(C)
 
<li>	b)Question Service:</li>

<li>    c) DTO and Mapper </li>
	
		-UserPostDTO purporse is for RequestBody  update/post controller contains USERDTO + password
		-UserDTO purporse is for HTTP Get methods on controller to show only user data required without password.
		
<li>d) Mapper:</li>
		-UserMapper is used to convert from DTO to Model and from Model to DTO.
	
<li>e)User Controller:	Below is the mapping can be used on User Rest Controller <li>
	
	Service Method  HTTP method CRUD 	URI 		Description
	create			POST 		Create	/user 		Create a new user given a UserDTO object
	findAll			GET 		Read	/user		Return a list of users paginate.
													Default pagination page=0 y size 10
	
	getUserById		GET			Read	/user/(idU)	Return user details for a specific user id							
	update			PUT			Update	/user/(idU)	Update user,mail, name, surname and /or password
	delete			DELETE		Delete	/user/(idU)	Remove user completely
</ul>	
</p>
**************************************************************************************************************************
**********  QUESTION  *******************************************************	

	a) QuestionDAO access to Repository map to Question Entity mainly. 
		-Create(C)	
		-Read(R)
		-Update(C)
		-Delete(C)
	
	b)Question Service:
	c) DTO and Mapper
	
		-QuestionUpdateDTO purporse is for RequestBody  update/post controller 
		-QuestionDTO purporse is for HTTP Get methos on controller to show all question and answer linked.
		
	d) Mapper:
		-QuestioNMapper is used to convert from DTO to Model and from Model to DTO.
	
	e)Question Controller:	Below is the mapping can be used on Question Rest Controller 
	
	Service Method  	HTTP method CRUD 		URI 							Description
	create				POST 		Create		/question 						Create a new question
						POST		Create		/question/(idQ)/answer			Create a answer link to question
	findAll				GET 		Read		/question						Return a list of questions with answers
	findById			GET			Read		/question/(idQ)					Return answers for a specific question							
	getCorrectAnswer	GET			Read		/question/(IdQ)/showCorrect		Return the corect answer for given quetsion id
	update				PUT			Update
									/Replace	/question/(idQ)					Update question
	updateAnswerQuestionPUT			Update
									/Replace	/question/(idQ)/answer/(idA)	Update answer of the existing question
	deleteAnswer		DELETE		Delete		/question/(idQ)					Remove question
	deleteAnswerQuestionDELETE		Delete		/question/(idQ)/answer/(idA)	Remove answer of the existing question
	
	
	And Validation method:
	isAnswerMapToQuestion(Integer idQuestion, Integer idAnswer) - check if specific Answer belongs to the specific Question

Example

METHOD GET
http://localhost:8080/question-_>geT ALL QUESTION WITH ITS ANSWERS

METHOD GET , GET QUESTIONS AND ANSWERS QUESTION 1
http://localhost:8080/question/1

METHOD GET , SHOW CORRECT ANSWER
http://localhost:8080/question/1/showCorrect

METHOD POST, CREATE QUESTION AND ITS DESCRIPTION
http://localhost:8080/question
{ "description": "Question1"}

METHOD PUT TO  ADD ANSWERS LIST TO QUESTIONS

http://localhost:8080/question/1/answer
[{"description": "answer1.1", "isCorrect":"false"},
{"description": "answer1.2", "isCorrect":"false"},
{"description": "answer1.3", "isCorrect":"true"} ]

**************************************************************************************************************************
*****************  ANSWER  *************************************
a) AnswerDAO access to Repository map to Answer Entity mainly. 
	-Create(C)	
	-Read(R)
	-Update(C)
	-Delete(C)
 
	b)Answer Service:
	Implemeted extended AbstractCrossService taht received a Answer Model object: create Answer, update Answer, delet Answer, findById given answerd id,
	findAll that return ala List of Answer Model
	
	Another methods implemented on Answer service but not in AbstractCrossService:
		addAnswerQuestion->Given a Answer model and an Id Question save into answer Repository this new link mapped idQuestion,idAnswer
		updatedAnswerQuestion->Given a Answer model and an Id Question udpate for idAnswer on answer Repository the idQuestion linked using addAnswerQuestion
		deleteAnswerQuestion-->Delete and answer mapped to an existing question id
		isAnswerMapToQuestion->check if an idQuestion is linked to idAnswer
		findOneByDescriptionOrderByIdDesc-_> return all answer order by description and sor tin desceding order
		
	c) DTO and Mapper
	
		-AnswerUpdateDTO purpose is for RequestBody  update/post controller contains Description + isAnswerCorrect
		-AnswerDTO purporse is for HTTP Get methods and PUT Answer creation controller to show only asnwer data without ID.
		
	d) Mapper:
		-AnswerMapper is used to convert from DTO to Model and from Model to DTO.AnswerDTO is the one use for DTO
	
	e)Answer Controller:	Below is the mapping can be used on Answer Rest Controller 
	
Service   HTTP   CRUD 		URI 				Description
create	  POST 	 Create	   /answer				Create a new answer given a AnswerDTO object

findAll	  GET 	 Read		/answer				Return a list of answers paginate. Default pagination page=0 y size 10
findById  GET	 Read		/answer/(idAnswer)	Return answer details for a specific answer id							
	
update	  PUT	 Update/Rep /answer/(idAnswer)	Update description for answer
										
delete	  DELETE Delete		/answer/(idAnswer)	Remove answer completely
	

**************************************************************************************************************************
* [<i>COURSE CREATION (USER + QUESTIONNARIE + COURSE). DO ALSO MOCKITO TEST FOR COURSE LAYERS</i>]
**************************************************************************************************************************
File src\main\resources\data\CourseDataModel.txt contains data test for course to be added and tets controller

Course Controller:	Below is the mapping can be used on Course Controller 
	
Service Method  	HTTP method 	CRUD 	URI 			         Description
create				POST 			Create	/course			         Create a new course given a CourseDTO object

findAll				GET 			Read	/course			          Return a list of courses paginate.Default pagination page=0 y size 10
findById			GET				Read	/course/(idC)	          Return course details for a specific course id
getUsersCourseById  GET             Read    /course/(idC)/user        Return lits of users in Course IDC with Course Details also
	
update				PUT				Update	/course/(idC)             Update details fields for course(description, start date..)
addUserCouse		PUT				Update  /course/(idC)/user/(idU)  Add user idU to Course idC		
delete				DELETE			Delete	/course/(idC)	          Remove course and linked questionnarie
	
	Usage Examples tested with filew txt:
	PUT -->http://localhost:8080/course/1/user/1-->This links user 1 with course 1
	PUT -->http://localhost:8080/course/1/user/2-->This links user 2 with course 1
	PUT -->http://localhost:8080/course/1/user/3-->This links user 3 with course 1
	PUT -->http://localhost:8080/course/2/user/1-->This links user 1 with course 1
	PUT -->http://localhost:8080/course/2/user/2-->This links user 2 with course 2
	GET -->http://localhost:8080/course/1/user -->This show Course 1 Description + List of Users belong to course 1


	MOCKITO TESTS:
**************************************************************************************************************************
* QUESTIONNAIRE CREATION (QUESTIONNARIE ).
**************************************************************************************************************************
File src\main\resources\data\CourseDataModel.txt contains data test for questionnaire also to be added and tets controller

Course Controller:	Below is the mapping can be used on Course Controller 
	
Service Method  	HTTP method 	CRUD 	URI 			         Description
create				POST 			Create	/questionnaire			 Create a new course given a CourseDTO object

findAll				GET 			Read	/questionnaire			 Return a list of courses paginate.Default pagination page=0 y 																				 size 10
findById			GET				Read	/questionnaire/(idQ)	 Return course details for a specific course id
update				PUT				Update	/questionnaire/(idQ)     Update details fields for course(description, start date..)
linkQuestion		PUT				Update  /questionnaire/(idQ)/question/(idQt)  Add user idU to Course idC		
delete				DELETE			Delete	/questionnaire/(idQ)	          Remove questionnaire and linked questions to questionnaire
	
getResultQuestionnarie  GET             Read    /questionnaire/(idQ)/result Return lits of Questionnarie result
getResultQuestionsQuestionnarie redirect to /question/{id }  get}}

Usage Examples tested with filew txt:
	PUT -->http://localhost:8080/questionnaire/1-->This creates questionnarie with given body descripton on course 1
	Body: {"description":"questionnaire1 SPRING"}
	GET -->http://localhost:8080/questionnaire-->Ths retrieve all questioarie withut question/asnwer details
	PUT ->http://localhost:8080/questionnaire/1/question/1 -->This Link question 1 with questionnarie 1

*******************************************************
* [<i>GET THE RESULS FILTER BY COURSE</i>]
*******************************************************
Main Controller fo result-->/result

MAIN FUNCTIONALLITIES ARE ON CONTROLLERS /QUIZ AND /RESULT
CONTROLLER QUIZ CREATE , UPDATE A USRE RESPONSE FOR A QUESTIONNAIRE AND QUESTION AND SAVE INTO DATABASE
ALSO GENERATE RESULT INITIAL.

THEN /RESULT CONTAINS METJDOS TO EXTRACT RESULT OF QUESTIONANAIRE FOR QUESTIONNAIRE, USER	

+ GET THE QUESTIONNARIE REQUESTING QUESTION -ANSWER ONE BY ONE.USER MUST RESPONSE, SAVE THE ANSWER AND THEN GET NEXT QUESTION.

/quiz controller
Service Method  	HTTP method 	CRUD 	URI 			         Description


Usage /quiz example

POST --> Create user answer: http://localhost:8080/quiz
Body: { "id": { "questionnaireId" : "1" , "userId": "1", "questionId": "1", "answerId":"1"}, "date": "2018-06-03"}

---------------------------------------------------------------------------
[<i>GET THE QUESTIONNARIE REQUESTING QUESTION -ANSWER ONE BY ONE.USER MUST RESPONSE, SAVE THE ANSWER AND THEN GET NEXT QUESTION</i>]
 Controller 
GET -->/questionarie/(id)/onebyone

[<i>GET THE QUESTIONNARIE SHOWING RAMDOM QUESTION -ANSWER ONE BY ONE. USER MUST RESPONSE, SAVE THE ANSWER AND THEN GET NEXT QUESTION.</i>]
 
 <<<<<<<TO DO>>>>>>>>
 Controller 
GET -->/questionarie/(id)/random

[<i>GET ALL QUESTION-ANSWER FOR QUESTIONNARIE(WITHOUT PAGINATION ??)</i>]
 
 <<<<<<<TO DO>>>>>>>>
 Controller 
GET -->/questionarie/(id)