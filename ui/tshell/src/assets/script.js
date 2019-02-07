function addItem(){
 
    var ul = document.getElementById("dynamic-list");
    var candidate = document.getElementById("candidate");
    
    var li = document.createElement("li");
    
    li.setAttribute('id',candidate.value);
    li.setAttribute('class',"btn litopic");
    li.setAttribute('style',"background: #eaeaea;color: #000;border-radius: 2px;cursor: default;position: relative;display: inline-block; border: 1px solid #e5e5e6;  margin: 4px 4px 0 0;  padding: 7px;");
    var button = document.createElement("button");
    button.setAttribute('class',"btn btn-sm btn-danger fa fa-minus");
    button.setAttribute('value',candidate.value);
    button.setAttribute('onclick',"removeItem();");
    li.appendChild(document.createTextNode(candidate.value));
    ul.appendChild(li);
    var lii = document.getElementById(candidate.value);
    lii.appendChild(button);
}

function removeItem(){
    var ul = document.getElementById("dynamic-list");
   
    var item = document.getElementById(candidate.value);
    ul.removeChild(item);
}