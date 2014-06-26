function confirmar(tipo) 
	{ 
	if(!confirm("Est\u00e1 seguro que desea eliminar "+ tipo +"?")) { 
		return false; 
	} 
	else { 
		return true; 
	} 
} 
