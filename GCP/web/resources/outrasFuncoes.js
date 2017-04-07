/*funcão para mascarar número decimal
 * para utilização apenas coloque no inputtext
 * onkeyup="MascaraNumeroDecimal(this);" onkeydown="MascaraNumeroDecimal(this);"
 **/
function MascaraNumeroDecimalQuatroCasas(o){
    v_obj=o;
    v_fun=function replaceNumeroDecimal(v){
        v=v.replace(/\D/g,"");
        v=v.replace(/(\d{4})$/,",$1");
        return v
    }
    setTimeout(function execmascara(){
        v_obj.value=v_fun(v_obj.value)
    },1);
}

function MascaraNumeroDecimalUmaCasa(o){
    v_obj=o;
    v_fun=function replaceMoeda(v){
        v=v.replace(/\D/g,"");
        v=v.replace(/(\d{1})$/,",$1");
        v=v.replace(/(\d+)(\d{3},\d{1})$/g,"$1.$2");
        var qtdLoop = (v.length-3)/3;
        var count = 0;
        while (qtdLoop > count){
            count++;
            v=v.replace(/(\d+)(\d{3}.*)/,"$1.$2");
        }
        v=v.replace(/^(0)(\d)/g,"$2");
        return v
    }
    setTimeout(function execmascara(){
        v_obj.value=v_fun(v_obj.value)
    },1);
}


function JumpField(fields) {   
    if ((fields.value.length == fields.maxLength) && fields.value[fields.maxLength-1]!="_" ) {
        for (var i = 0; i < fields.form.length; i++) {
            if (fields.form[i] == fields && fields.form[(i + 1)] && fields.form[(i + 1)].type != "hidden") {
                fields.form[(i + 1)].focus();
                fields.form[(i + 1)].select();
                break;
            }            
        }
    }
}

function somenteNumeros0a3(Campo){
    //SOMENTE NÚMEROS
    var digits="0123";
    var campo_temp = "";
    for (var i=0;i<Campo.value.length;i++){
        campo_temp=Campo.value.substring(i,i+1)    
        if (digits.indexOf(campo_temp)==-1){
            Campo.value = Campo.value.substring(0,i);
            break;
        }
    }	
}

function somenteNumeros(Campo){
    //SOMENTE NÚMEROS
    var digits="0123456789";
    var campo_temp = "";
    for (var i=0;i<Campo.value.length;i++){
        campo_temp=Campo.value.substring(i,i+1)    
        if (digits.indexOf(campo_temp)==-1){
            Campo.value = Campo.value.substring(0,i);
            break;
        }
    }	
}