package listeners;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import munsell.HueCode;
import munsell.MunsellColor;

public class PaletteListener implements MouseListener{

	private static List<JPanel> activePanels = new ArrayList<JPanel>();
	
	
	/**
	 * Get the list of active panels
	 * 
	 * @return	the list of active panels
	 */
	public static List<JPanel >getActivePanels()
	{
		return activePanels;
	}
	
	/**
	 * Clear the active panels
	 * 
	 */
	public static void clearActivePanels()
	{
		activePanels = new ArrayList<JPanel>();
	}
	


	/**
	 * Get the values and chroma of a color from a selected JPanel
	 * 
	 * @return	an array that holds the value and chroma respectively
	 */
	public static MunsellColor getColor(JPanel panel)
	{

		// color properties
		float hueNum = 0;
		float value = 0;
		float chroma = 0;
		HueCode hueCode = null;
		
		
		// counters
		int i = 0;
		int y = 0;

		// iterate through the components to get the labels
        for (Component comp : panel.getComponents())
        {
        	// skip the first one
        	if (y == 0)
        	{
        		y++;
        		continue;
        	}

        	// get the inner panel with color properties
        	JPanel innerPanel = (JPanel)comp;
        	
        	for (Component innerComp: innerPanel.getComponents())
        	{
				// make sure we're looking at a JLabel
				if (innerComp instanceof JLabel)
				{
					
										// hue
					if (i == 0) 
					{
						String input = (((JLabel) innerComp).getText().substring(3));
						// pattern to look for letters
						Pattern pattern = Pattern.compile("[A-Z]|[a-z]");
						
						// look for the letter
						Matcher matcher = pattern.matcher(input);
						
						// check for match
						if (matcher.find())
						{
							// get the index for where the letter(s) starts
							int index = matcher.start();
							
							hueNum = (Float.valueOf(input.substring(0, index)));
							
							// get the string version of the hueCode
							String hueCodeStr = input.substring(index);
							
							// "convert" the string hue code to the enum version
							switch(hueCodeStr)
							{
								case "R":
									hueCode = HueCode.R;
									break;
								case "RP":
									hueCode = HueCode.RP;
									break;
								case "P":
									hueCode = (HueCode.P);
									break;
								case "PB":
									hueCode = (HueCode.PB);
									break;
								case "B":
									hueCode = (HueCode.B);
									break;
								case "BG":
									hueCode = (HueCode.BG);
									break;
								case "G":
									hueCode = (HueCode.G);
									break;
								case "GY":
									hueCode = (HueCode.GY);
									break;
								case "Y":
									hueCode = (HueCode.Y);
									break;
								case "YR":
									hueCode = (HueCode.YR);
									break;
								default :
									break;
							}
						}
					
					}
					// value 
					else if (i == 1)
					{
						value = Float.parseFloat(((JLabel) innerComp).getText().substring(3));
					}
					// chroma
					else if(i == 2) 
					{
						chroma = Float.parseFloat(((JLabel) innerComp).getText().substring(3));
					}

					// increment
					i++;
				}
        	}
        }
        
        // hueCode can't be 0
        if (hueNum == 0)
        {
        	return null;
        }
        
        // create new color
        MunsellColor color = new MunsellColor(hueNum, hueCode, value, chroma);

        // return the color
        return color;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent evt) 
	{
		
		// get the panel
		JPanel panel = (JPanel)evt.getSource();
	
		// set the background to a darker shade of white 
		panel.setBackground(new Color(234, 234, 234));
		
		// create a border on the left when the mouse enters
		// panel.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
		
	}

	@Override
	public void mouseExited(MouseEvent evt) {
		// get the panel
		JPanel panel = (JPanel)evt.getSource();
	
		// set the background to white
		panel.setBackground(Color.WHITE);
		
		// get rid of the border when the mouse exits
		//panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	}
	
	/**
	 * Add borders to the active panels 
	 */
	public static void updateActivePanels()
	{
		for(JPanel panel : activePanels)
		{
			panel.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.BLACK));
		}
	}

	@Override
	public void mousePressed(MouseEvent evt) 
	{
		// get the panel
		JPanel panel = (JPanel)evt.getSource();
		
		if (activePanels.contains(panel))
		{
			// check to see which panel it is in the list
			if (activePanels.get(0) == panel)
				activePanels.remove(0);
			else
				activePanels.remove(1);
			
			// remove the border
			panel.setBorder(BorderFactory.createEmptyBorder());
			return;
		}
			
		
		// check the size
		if (activePanels.size() == 2)
		{
			// get the old active panel
			JPanel oldPanel = (JPanel)activePanels.get(0);
			
			// remove border 
			oldPanel.setBorder(BorderFactory.createEmptyBorder());
			
			// remove the first item
			activePanels.remove(0);
		}
		
		// add panels to be mixed to the list
		activePanels.add(panel);
		
		// add borders to active panels
		updateActivePanels();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}

}
