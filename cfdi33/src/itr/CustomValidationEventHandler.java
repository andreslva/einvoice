package itr;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import org.xml.sax.SAXParseException;

public class CustomValidationEventHandler implements ValidationEventHandler {

	private boolean handled;

	public boolean isHandled() {
		return handled;
	}

	public boolean handleEvent(ValidationEvent event)
	{
		handled = true;
		
		Throwable e = event.getLinkedException();
		boolean isSAXParseException = e instanceof SAXParseException;
		if( !isSAXParseException )
		{
			if(event.getLinkedException() == null)
			{
				handled = true;//wrong xsd version
			} else
			{
				System.out.println("\nEVENT");
				System.out.println("SEVERITY:  " + event.getSeverity());
				System.out.println("MESSAGE:  " + event.getMessage());
				System.out.println("LINKED EXCEPTION:  " + event.getLinkedException());
				System.out.println("LOCATOR");
				System.out.println("    LINE NUMBER:  " + event.getLocator().getLineNumber());
				System.out.println("    COLUMN NUMBER:  " + event.getLocator().getColumnNumber());
				System.out.println("    OFFSET:  " + event.getLocator().getOffset());
				System.out.println("    OBJECT:  " + event.getLocator().getObject());
				System.out.println("    NODE:  " + event.getLocator().getNode());
				System.out.println("    URL:  " + event.getLocator().getURL());
			}
			
			handled = false;
		}

		return handled;
	}

}
