# Full Stack Applicaton

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


```
