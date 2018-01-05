import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-manforms',
  templateUrl: './manforms.component.html',
  styleUrls: ['./manforms.component.css']
})
export class ManformsComponent implements OnInit {

  constructor() { }

  ngOnInit() {
      let i = 1;
      let xml = new XMLHttpRequest();
          xml.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                processTable(this);
           }
        };
        xml.open('GET',`http://localhost:8080/Reimbursement/ManServlet?eid=${i}`,true);
        xml.send();
      
     }
     
    public sendApprove() {
      let apps = document.getElementById("toApprove").value
      let deny = document.getElementById("toDeny").value
      let xml = new XMLHttpRequest();
        xml.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
             // this.router.navigate(['/NewForm']);
          }
        };
        xml.open('GET',`http://localhost:8080/Reimbursement/AcceptDenyServlet?eid=${sessionStorage.employeeId}&apps=${apps}&deny=${deny}`,true);
        xml.send();
     }

     public applyRequest(rid:Number,request:String) {
         console.log(rid);
         console.log(request);
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
        "</td><tr>"
         }
      document.getElementById("table2").innerHTML = table;
      }
}
