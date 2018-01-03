import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-newform',
  templateUrl: './newform.component.html',
  styleUrls: ['./newform.component.css']
})
export class NewformComponent implements OnInit {

  public sendRequest() {
      let request = {"eid":1,"date":document.getElementById("main").elements[0].value,
      "time":document.getElementById("main").elements[1].value,
      "description":document.getElementById("main").elements[2].value,
      "reason":document.getElementById("main").elements[3].value,
      "location":document.getElementById("main").elements[4].value,
      "cost":document.getElementById("main").elements[5].value,
      "timemissed":document.getElementById("main").elements[6].value,
      "formatid":document.getElementById("main").elements[7].value,
      "eventid":document.getElementById("main").elements[8].value,
      
      }
      let sendJSON = JSON.stringify(request);
      let xml = new XMLHttpRequest();
      xml.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
       }
    };
      xml.open('POST',`http://localhost:8080/Reimbursement/NewFormServlet`,true);
      xml.send(sendJSON);
       xml.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log('recieved');
            console.log(this.responseText);
        }
    };
  }

  ngOnInit() {
  }

}
