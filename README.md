# Full Stack Applicaton

* Refer -> https://www.youtube.com/watch?v=i79WkzkPwG4&list=PL_Foa0RZ0qUzoQGb818550mVQvW1q9L3y&index=12
* https://github.com/veerrajukakarla434/2024-FrontEnd-React-Ems/blob/main/2024-React18-FullStack-Documentation/Part1.md
* YouTub Code : https://github.com/veerrajukakarla434/2024-EmployeeManagementService
 
* After React JS installation and folder structure
* Creating dummy data and displaying in Employee list.
```javascript

function EmployeeList() {

    const employeeData=[
        {
           "id":1,
           "firstName":"Veerraju",
           "lastName":"Kakarla",
            "skils":"Java"
        },
        {
            "id":1,
           "firstName":"Satwika",
           "lastName":"kakarla",
            "skils":"Java, JS , TS"
        }

    ];

  return (
    <div>
       <h2>List Of Employees</h2>
       <table>
        <tr>
            <th>Id:</th>
            <th>First Name:</th>
            <th>Lat Name:</th>
            <th>Skills</th>
        </tr>
          <tbody>
            {
                employeeData.map(emp=>
                    <tr key={emp.id}>
                        <td>{emp.id}</td>
                        <td>{emp.firstName}</td>
                        <td>{emp.lastName}</td>
                        <td>{emp.skils}</td>
                    </tr>
                ) 
            }
          </tbody>

       </table>
    </div>
  )
}

export default EmployeeList


Then call in App.tsx


import './App.css'
import EmployeeList from './components/EmployeeList'
function App() {
  return (
    <>
      <h1 >
        <EmployeeList/>
      </h1>
    </>
  )
}

export default App


-----------------------------------------------------------------------------
 Step2

  List of employees, Add Employee, and Rounter configuration

  import { useEffect, useState } from "react";
import { listOfEmployees } from "../services/EmployeeService";
import { useNavigate } from "react-router-dom";

function EmployeeList() {

   const navigate = useNavigate();

   const [employees, setEmployees] = useState<any[]>([]);
   
   
   useEffect(()=>{

     listOfEmployees().then((response)=>{
        setEmployees(response.data);
     }).catch(error=>{
      console.error(error);
     });
   }, []);

   function addEmployee(){
    navigate('/add-employee');
   }


  return (
     <div className='container'><br></br><br></br>
       <h2 className="text-center">List Of Employees</h2>
        <div className="d-grid gap-2 d-md-flex justify-content-md-end"> 
          <button className="btn btn-primary" onClick={addEmployee}>Add Employee</button>
        </div><br></br>
       <table className='table table-striped table-bordered'>
        <thead>
        <tr>
            <th>Id:</th>
            <th>First Name:</th>
            <th>Lat Name:</th>
            
        </tr>
        </thead>
          <tbody>
            {
                employees.map(emp=>
                    <tr key={emp.id}>
                        <td>{emp.id}</td>
                        <td>{emp.firstName}</td>
                        <td>{emp.lastName}</td>
                       
                    </tr>
                ) 
            }
          </tbody>

       </table>
    </div>
  )
}

export default EmployeeList

---------------------------

Router


import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import EmployeeList from './components/EmployeeList'
import Footer from './components/Footer'
import Header from './components/Header'
import Employee from './components/Employee'
function App() {
  return (
    <>
        <Header></Header>
        <BrowserRouter>
          <Routes>
                <Route path='/' element={<EmployeeList/>}/>
                <Route path='/employees' element={<EmployeeList/>}/>
                <Route path='/add-employee' element={<Employee/>}/>
                
          </Routes>         
        </BrowserRouter>
        <Footer/>
    </>
  )
}

export default App

------
 Add Employee
  import { useState } from "react"


const Employee = () => {

    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')

    function pageTitle() {
        return <h2 className="text-center">Add Employee</h2>
    }

     function saveEmployee(e: { preventDefault: () => void }) {
        e.preventDefault();
        const employee = {firstName, lastName}
        console.log(employee);
    }
    
    return (
        <div className="container"> <br /><br />
            <div className="row">
                <div className="card col-md-6 offset-md-3 offset-md-3">
                    {
                        pageTitle()
                    }
                    <div className="card-body">
                        <form>
                            <div className="form-group bm-2">
                                <label className="form-label"> First Name:</label>
                                <input type="text"
                                    placeholder="Enter Employee First Name"
                                    name="firstName"
                                    value={firstName}
                                    className="form-control"
                                    onChange={(e) => setFirstName(e.target.value)}
                                />
                            </div>

                    <div className="form-group bm-2">
                                <label className="form-label"> Last Name:</label>
                                <input type="text"
                                    placeholder="Enter Employee Last Name"
                                    name="lastName"
                                    value={lastName}
                                    className="form-control"
                                    onChange={(e) => setLastName(e.target.value)}
                                />
                            </div>

                            <button className="btn btn-success" onClick={saveEmployee}> Submit</button>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    )
}

export default Employee

```
![image](https://github.com/user-attachments/assets/ec1e7d7e-4f3f-4bdb-ad2e-c0a6cbe51281)


* With Validations

```javascript
import { useState } from "react"
import { Link, useNavigate } from "react-router-dom"
import { createEmployee } from "../services/EmployeeService"


const Employee = () => {

    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const navigator= useNavigate();

   const [errors, setErrors] = useState({
    firstName:"",
    lastName:""
   })



    function pageTitle() {
        return <h2 className="text-center">Add Employee</h2>
    }

   




     
        function saveEmployee(e: { preventDefault: () => void }) {
            e.preventDefault();
         if(validateForm()){
           const employee = {firstName, lastName}
            createEmployee(employee).then((response)=>{
                console.log(response.data);
                navigator("/employees")
            }).catch((error)=>{
                console.error(error);
            })
         }
        
    }
  
     
    
   


   function validateForm(){
        
      let valid= true;
      const errorsCopy = {...errors};
      
       if(firstName.trim()){
          errorsCopy.firstName="";
         }else{
          errorsCopy.firstName="FirstName is required";
          valid=false;
         }

          if(lastName.trim()){
          errorsCopy.lastName="";
         }else{
          errorsCopy.lastName="LastName is required";
          valid=false;
         }

      setErrors(errorsCopy);
      return valid;
   }




    return (
        <div className="container"> <br /><br />
            <div className="row">
                <div className="card col-md-6 offset-md-3 offset-md-3">
                    {
                        pageTitle()
                    }
                    <div className="card-body">
                        <form>
                            <div className="form-group bm-2">
                                <label className="form-label"> First Name:</label>
                                <input type="text"
                                    placeholder="Enter Employee First Name"
                                    name="firstName"
                                    value={firstName}
                                    className={`form-control ${errors.firstName ? 'is-invalid' : ""}`}
                                    onChange={(e) => setFirstName(e.target.value)}
                                /> {errors.firstName && (
                                    <div className="invalid-feedback">{errors.firstName}</div>
                                    )}
                            </div>

                          <div className="form-group bm-2">
                                <label className="form-label"> Last Name:</label>
                                <input type="text"
                                    placeholder="Enter Employee Last Name"
                                    name="lastName"
                                    value={lastName}
                                    className={`form-control ${errors.lastName ? 'is-invalid' : ""}`}
                                    onChange={(e) => setLastName(e.target.value)}
                                />{errors.lastName && (
                                    <div className="invalid-feedback">{errors.lastName}</div>
                                    )}
                            </div><br/>
                            <button className="btn btn-success" onClick={saveEmployee}> Submit</button>
                            <Link className="btn btn-danger mx-2 " to="/" >Cenel</Link>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    )
}

export default Employee

```
![image](https://github.com/user-attachments/assets/6ac8a14c-4c2e-497e-82bf-58e39f8bc063)


