package listeners;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import main.MainUI;
import munsell.HueCode;
import munsell.MunsellColor;
import palette.Palette;


public class HighlightPanel implements MouseListener
{
	
	private JPanel selectedPanel;
	
	class MenuActionListener implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent evt) {
			
			// get the item that was selected
			JMenuItem menuItem = (JMenuItem)evt.getSource();
			
			// check which button it was
			if (menuItem.getText() == "Add to Palette")
			{
				
				// get the value and chroma from the color panel
				float[] values = getColorValues(selectedPanel);
				
				// get the user's palette
				Palette palette = MainUI.getPalette();
				
				// extract value and chroma
				float value = values[0];
				float chroma = values[1];
				
				// get hue info
				float hueNum = MainUI.getUserHueNum();
				HueCode hueCode = MainUI.getUserHueCode();
				
				// create a new color
				MunsellColor newColor = new MunsellColor(hueNum, hueCode, value, chroma);
				
				// add the new color to the color palette
				palette.addColor(newColor);
				
				// redraw
				MainUI.redraw(0);
				
			}
		}
		
	}

	/**
	 * Get the values and chroma of a color from a selected JPanel
	 * 
	 * @return	an array that holds the value and chroma respectively
	 */
	public float[] getColorValues(JPanel panel)
	{
		// array to be returned
		float[] values = new float[2];
		
		// counter
		int i = 0;

		// iterate through the components to get the labels
        for (Component comp : panel.getComponents())
        {
            // make sure we're looking at a JLabel
            if (comp instanceof JLabel)
            {
                // check if we're setting value or chroma
                if (i == 0)
                    values[0] = Float.parseFloat(((JLabel) comp).getText().substring(4));
                else 
                    values[1] = Float.parseFloat(((JLabel) comp).getText().substring(4));

                // increment
                i++;
            }
        }
        
        return values;
	}
	
    @Override
    public void mouseClicked(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent evt)
    {
        
        // get the JPanel that was hovered over
        JPanel panel = (JPanel) evt.getSource();
        
        // get the panel background color for the border
        Color borderColor = panel.getBackground();
        
        // make it different
        borderColor = borderColor.darker();
        
        
        // set the border on mouse hover
        panel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, borderColor));
    }

    @Override
    public void mouseExited(MouseEvent evt)
    {
        // get the JPanel that was hovered over
        JPanel panel = (JPanel) evt.getSource();
        
        // hide the border on mouse hover
        panel.setBorder(BorderFactory.createMatteBorder(3,3,3,3, new Color(229, 229, 229)));
    }

    @Override
    public void mousePressed(MouseEvent evt)
    {
    	
        // get the selected JPanel
        JPanel panel = (JPanel) evt.getSource();
        
        // set the selected panel
        selectedPanel = panel;
    	
    	// check if the click was a right click
    	if (evt.getButton() == 3) 
    	{
    		// create new popup
    		JPopupMenu popup = new JPopupMenu();

    		// create a menu item for adding colors to palette
    		JMenuItem addPalette = new JMenuItem("Add to Palette");
    		addPalette.addActionListener(new MenuActionListener());
    		
    		// create a menu item for showing the color palette
    		JMenuItem showPalette = new JMenuItem("Show Palette");
    		showPalette.addActionListener(new MenuActionListener());
    		
    		// add the menu items
    		popup.add(addPalette);
    		popup.add(showPalette);
    		
    		// show the popup at the mouse position
    		popup.show(evt.getComponent(), evt.getX(), evt.getY());
    		
    		// don't continue
    		return;
    	}

    	
        
        // munsell values 
        float value = 0;
        float chroma = 0;
        
        // get the value and chroma from the text on the panel
        float[] values = getColorValues(panel);
        
        // set value and chroma
        value = values[0];
        chroma = values[1];
        
        // get hue info
        float hueNum = MainUI.getUserHueNum();
        HueCode hueCode = MainUI.getUserHueCode();

        // set the new selected color
        MainUI.setSelectedColor(new MunsellColor(hueNum, hueCode, value, chroma));
        
        // redraw the tabs
        MainUI.redraw(1);
    }

    @Override
    public void mouseReleased(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }

}



