﻿import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-yourforms',
  templateUrl: './yourforms.component.html',
  styleUrls: ['./yourforms.component.css']
})
export class YourformsComponent implements OnInit {
  
  ngOnInit() {
 
    let i = 1;
    let xml = new XMLHttpRequest();
        xml.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
              processTable(this);
         }
      };
      xml.open('GET',`http://localhost:8080/Reimbursement/YourServlet?eid=${sessionStorage.employeeId}`,true);
      xml.send();
      
   }

   function processTable(xml) {
      let i = 1;
      let json = JSON.parse(xml.responseText);
      let table=`<tr><th>ID</th><th>Status</th><th>Description</th><th>Cost</th><th>Reimbursement Amount</th>
      <th>Location</th><th>Date</th><th>Time</th><th>Reason</th><th>Format</th><th>Event</th></tr>`;
      for (i = 0; i <json.length; i++) { 
        table += "<tr><td>" +
        json[i].id +
        "</td><td>" +
        json[i].status +
        "</td><td>" +
        json[i].description +
        "</td><td>" +
        json[i].cost +
        "</td><td>" +
        json[i].ramount +
        "</td><td>" +
        json[i].location +
        "</td><td>" +
        json[i].date +
        "</td><td>" +
        json[i].time +
        "</td><td>" +
        json[i].reason +
        "</td><td>" +
        json[i].formatid +
        "</td><td>" +
        json[i].eventid +
        "</td></tr>";
         }
      document.getElementById("table").innerHTML = table;
      }
}

