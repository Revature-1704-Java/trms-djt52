import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  
  constructor(/*private router: Router*/) {
  }

  public attemptLogin() {
  
      let email = document.getElementById("loginform").elements[0].value;
      let pass = document.getElementById("loginform").elements[1].value;
      let xml = new XMLHttpRequest();
      xml.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if(this.responseText == 'Incorrect Password' || this.responseText == 'Invalid Email') {
              document.getElementById('message').innerHTML = this.responseText;
              } else {
            sessionStorage.employeeId = this.responseText;
           // this.router.navigate(['/NewForm']);
            }
       }
    };
      xml.open('GET',`http://localhost:8080/Reimbursement/LoginServlet?email=${email}&pass=${pass}`,true);
      xml.send();
    
  }
  

  ngOnInit() {
  }

}
