package itr.algo;

import java.lang.reflect.Field;

import itr.ComprobanteGR;
import itr.sat.cfdi.v33.Comprobante;
//import itr.sat.cfdvxx.ComprobanteImpl;
import itr.sat.cfdi.v33.ObjectFactory;

public abstract class ComprobanteGRCreator {

	private ObjectFactory objFactory;
	private ComprobanteGR compGR;

	public ComprobanteGRCreator(ObjectFactory objFactory) {
		super();
		this.objFactory = objFactory;
		this.compGR = new ComprobanteGR();
	}

	protected ObjectFactory getObjFactory() {
		return objFactory;
	}

	protected ComprobanteGR getCompGR() {
		return compGR;
	}

	protected Comprobante getComprobante() {
		return compGR.getComprobante();
	}

	public final ComprobanteGR copyElements()
	{
		copyData();
		copyEmisor();
		copyReceptor();
		copyConceptos();
		//debug copyImpuestos();
		copyComplemento();
		hook();
		
		return compGR;
	}
	
	protected abstract void copyData();
	protected abstract void copyEmisor();
	protected abstract void copyReceptor();
	protected abstract void copyConceptos();
	protected abstract void copyImpuestos();
	protected abstract void copyComplemento();
	
	void hook()
	{
		
	}
	
	protected static int copy(Object a, Object b)
	{
		int result = 0;
		
		Class<?> classA = a.getClass();
		Class<?> classB = b.getClass();
	    
	    Field[] fields = 
	    		classB.getDeclaredFields();
	    		;
	    for(Field tmp: fields)
	    {
	    	String fieldName = tmp.getName();
	    	try {
				Field field = classA.getDeclaredField(fieldName);
				
				//boolean arePrimitive = tmp.getClass().isPrimitive() &&	field.getClass().isPrimitive();
				boolean sameType = tmp.getType().equals(field.getType());
				if(/*arePrimitive &&*/ 
						sameType)
				{
					field.setAccessible(true);
					Object value = field.get(a);
					tmp.setAccessible(true);
					tmp.set(b, value);
				}
			} catch (NoSuchFieldException e) {
				result = 1;
				e.printStackTrace();
			} catch (SecurityException e) {
				result = 2;
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				result = 3;
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				result = 4;
				e.printStackTrace();
			}
	    }

	    return result;
	}
}
