import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public attemptLogin() {
  
      let email = document.getElementById("loginform").elements[0].value;
      let pass = document.getElementById("loginform").elements[1].value;
      console.log(email);
      console.log(pass);
      let xml = new XMLHttpRequest();
      xml.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
       }
    };
      xml.open('GET',`http://localhost:8080/Reimbursement/LoginServlet?email=${email}&pass=${pass}`,true);
      xml.send();
    
  }
  

  ngOnInit() {
  }

}
