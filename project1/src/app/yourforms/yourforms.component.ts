import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-yourforms',
  templateUrl: './yourforms.component.html',
  styleUrls: ['./yourforms.component.css']
})
export class YourformsComponent implements OnInit {
  
  ngOnInit() {
    let i = 1:number;
    let xml = new XMLHttpRequest();
        xml.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
              processTable(this);
         }
      };
      xml.open('GET',`http://localhost:8080/Reimbursement/YourServlet?eid=${i}`,true);
      xml.send();
   }

   function processTable(xml) {
      let i = 1:number;
      let xmlDoc = this.responseXML;
      let table=`<tr><th>ID</th><th>Status</th><th>Description</th><th>Cost</th><th>Reimbursement Amount</th>
      <th>Location</th><th>Date</th><th>Time</th><th>Reason</th><th>Format</th><th>Event</th></tr>`;
      let x = xmlDoc.getElementsByTagName("CD");
      for (i = 0; i <x.length; i++) { 
        table += "<tr><td>" +
        x[i].getElementsByTagName("ID")[0].childNodes[0].nodeValue +
        "</td><td>" +
        x[i].getElementsByTagName("STATUS")[0].childNodes[0].nodeValue +
        "</td></tr>";
        x[i].getElementsByTagName("DESCRIPTION")[0].childNodes[0].nodeValue +
        "</td></tr>";
        x[i].getElementsByTagName("COST")[0].childNodes[0].nodeValue +
        "</td></tr>";
        x[i].getElementsByTagName("RAMOUNT")[0].childNodes[0].nodeValue +
        "</td></tr>";
        x[i].getElementsByTagName("LOCATION")[0].childNodes[0].nodeValue +
        "</td></tr>";
        x[i].getElementsByTagName("DATE")[0].childNodes[0].nodeValue +
        "</td></tr>";
        x[i].getElementsByTagName("TIME")[0].childNodes[0].nodeValue +
        "</td></tr>";
        x[i].getElementsByTagName("REASON")[0].childNodes[0].nodeValue +
        "</td></tr>";
        x[i].getElementsByTagName("FORMATID")[0].childNodes[0].nodeValue +
        "</td></tr>";
        x[i].getElementsByTagName("EVENTID")[0].childNodes[0].nodeValue +
        "</td></tr>";
         }
      document.getElementById("demo").innerHTML = table;
      }
      }

