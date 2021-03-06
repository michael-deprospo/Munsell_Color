package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;

import conversion.Converter;
import listeners.HighlightPanel;
import listeners.PaletteListener;
import munsell.HueCode;
import munsell.MunsellColor;
import palette.Palette;
import setCreator.ColorSetCreator;
import setCreator.MunDimension;

/**
 * Builds the UI.
 * 
 * @author Michael DeProspo
 *
 */
public class MainUI
{

	// dimension values
	private static final int WIDTH = 950;
	private static final int HEIGHT = 700;
	private static int COLOR_ROWS = 11;
	private static int COLOR_COLS = 10;

	// user set variables
	private static float userHueNum;
	private static HueCode userHueCode;

	// get a color converter
	private static Converter converter = Converter.getConverter();

	// declare a new palette
	private static Palette palette;

	// the main frame that holds everything
	private static JFrame mainFrame;

	// the main tab container
	private static JTabbedPane tabs;

	private static MunsellColor selectedColor;
	private static MunsellColor mixedColor;
	private static BufferedImage image;
	private static boolean clicked = false;
	private static boolean found = false;

	/**
	 * Get the selected color.
	 * 
	 * @return the selected color
	 */
	public static MunsellColor getSelectedColor()
	{
		return selectedColor;
	}

	/**
	 * Set the selected color.
	 * 
	 * @param newSelectedColor the new selected color
	 */
	public static void setSelectedColor(MunsellColor newSelectedColor)
	{
		selectedColor = newSelectedColor;
	}

	/**
	 * Get the users hue num.
	 * 
	 * @return user hue number
	 */
	public static float getUserHueNum()
	{
		return userHueNum;
	}

	/**
	 * Get the users hue code.
	 * 
	 * @return user hue code
	 */
	public static HueCode getUserHueCode()
	{
		return userHueCode;
	}

	/**
	 * Set the users hue num.
	 * 
	 * @param num the num to set
	 */
	public static void setUserHueNum(float num)
	{

		userHueNum = num;

	}

	/**
	 * Set the users hue code.
	 * 
	 * @param code the hue code to set
	 */
	public static void setUserHueCode(HueCode code)
	{
		userHueCode = code;
	}

	/**
	 * Get the users color palette.
	 * 
	 * @return the users color palette
	 */
	public static Palette getPalette()
	{
		return palette;
	}

	/**
	 * Redraw the UI.
	 * 
	 * @param tabIndex which tab to show after redraw()
	 */
	public static void redraw(int tabIndex)
	{

		// remove everything from the main frame
		mainFrame.getContentPane().removeAll();

		// create a new panel to replace the current one
		JTabbedPane newPanel = createBase();

		// add the new panel with the updated colors
		mainFrame.add(newPanel, BorderLayout.CENTER);

		// add a new palette
		mainFrame.add(paletteTab(), BorderLayout.EAST);

		// for requesting the palette tab before the harmonious tab has been created
		if (tabIndex >= tabs.getTabCount())
		{
			tabs.setSelectedIndex(tabs.getTabCount() - 1);
			return;
		}

		// redraw everything
		mainFrame.revalidate();

		// move tabs
		tabs.setSelectedIndex(tabIndex);

	}

	/**
	 * Create the UI.
	 * 
	 * @param args arguments
	 */
	public static void main(String[] args)
	{

		// set the starting color
		setUserHueNum(2.5f);
		setUserHueCode(HueCode.R);

		// create a new color palette
		palette = new Palette();

		// create a new JFrame
		mainFrame = new JFrame();

		// set frame size
		mainFrame.setSize(WIDTH, HEIGHT);

		// set the frame layout
		mainFrame.setLayout(new BorderLayout());

		// make the frame visible
		mainFrame.setVisible(true);

		// draw the colors
		JTabbedPane pan = createBase();

		// mainFrame.setContentPane(pan);
		mainFrame.add(pan, BorderLayout.CENTER);
		mainFrame.add(paletteTab(), BorderLayout.EAST);
		mainFrame.setVisible(true);
	}

	/**
	 * Create a row of hues.
	 * 
	 * @param start the starting color.
	 * @return the row of hues.
	 */
	public static JPanel createHueRow(MunsellColor start)
	{
		// JPanel to return
		JPanel row = new JPanel();

		// set grid layout with 10 columns
		row.setLayout(new GridLayout(1, 10));

		// create color set instance
		ColorSetCreator setMkr = new ColorSetCreator(2.5f, 1.0f, 2.0f);

		// set the hue limit
		setMkr.setHueLimit(start.getHueNum() + 9);

		// increment by one
		setMkr.setHueIncrement(1);

		// get the colors
		MunsellColor[] createdSet = setMkr.create1DSet(MunDimension.HUE, start);

		JLabel label;
		Color labelColor;

		// iterate through the colors
		for (int i = 0; i < createdSet.length; i++)
		{
			// create the label
			label = new JLabel(String.valueOf(createdSet[i].getHueNum()) + String.valueOf(createdSet[i].getHueCode()));

			label.setHorizontalAlignment(SwingConstants.CENTER);

			// convert munsell to RGB
			labelColor = converter.munsellToRGB(createdSet[i]);

			label.setBackground(labelColor);

			row.add(label);
		}

		// return the newly created row
		return row;
	}

	/**
	 * Create a row with incrementing chroma values.
	 * 
	 * @param start the starting color.
	 * @return a JPanel row of colors with increasing chroma values.
	 */
	public static JPanel createChromaRow(MunsellColor start)
	{

		// null check start

		// JPanel to return
		JPanel row = new JPanel();
		row.setLayout(new GridLayout(1, COLOR_COLS));

		// create a creator instance
		ColorSetCreator setMkr = new ColorSetCreator(2.5f, 1.0f, 0.0f);

		// set the limit to twenty
		setMkr.setChromaLimit(20f);

		// increment chroma by 22.5R
		setMkr.setChromaIncrement(2.0f);

		// create a new set (row)
		MunsellColor[] createdSet = setMkr.create1DSet(MunDimension.CHROMA, start);

		// color for showing n values
		MunsellColor valueColor = new MunsellColor(0f, 0f, 0f);

		// the overall container for the colors
		JPanel container;

		// the color for the labels
		Color labelColor;

		// variable for showing the colors that can't be
		// represented by rgb
		Color invalidColor = new Color(204, 204, 204);

		// value colors as rgb
		Color valueColorRGB;

		// iterate through the colors
		// use enhanced for loop
		for (int i = 0; i < createdSet.length; i++)
		{

			// create the container
			container = new JPanel();

			// set the layout to grid
			container.setLayout(new GridLayout(2, 1));

			// convert munsell to RGB
			labelColor = converter.munsellToRGB(createdSet[i]);

			// give each panel a blank border
			container.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(229, 229, 229)));

			// create color labels
			// JLabel hueLabel = new JLabel(" " + String.valueOf(createdSet[i].getHueNum())
			// + createdSet[i].getHueCode().toString());
			JLabel valueLabel = new JLabel(" V: " + String.valueOf(createdSet[i].getValue()));
			JLabel chromaLabel = new JLabel(" C: " + String.valueOf(createdSet[i].getChroma()));

			// hueLabel.setForeground(Color.black);

			// check to see if this is a valid color
			// i.e: not black or white
			if (labelColor.getRGB() != -1 && labelColor.getRGB() != -16777216)
			{
				// give each panel a mouse listener
				container.addMouseListener(new HighlightPanel());

				// set background/foreground
				container.setBackground(labelColor);
				valueLabel.setForeground(Color.black);
				chromaLabel.setForeground(Color.black);

				// change foreground colors for contrast
				if (createdSet[i].getValue() < 5)
				{
					valueLabel.setForeground(Color.WHITE);
					chromaLabel.setForeground(Color.WHITE);
				}

				// add labels
				container.add(valueLabel);
				container.add(chromaLabel);
			} else
			{
				if (i == 0)
				{

					container.setLayout(new GridLayout(1, 1));
					// set the 'N' text
					valueLabel.setText("N" + (int) createdSet[i].getValue());
					valueLabel.setHorizontalAlignment(JLabel.CENTER);
					valueLabel.setVerticalAlignment(JLabel.CENTER);

					// set the value for the color
					valueColor.setValue(createdSet[i].getValue());

					// convert munsell to rgb
					valueColorRGB = converter.munsellToRGB(valueColor);

					// set background to new rgb value
					container.setBackground(valueColorRGB);

					// add the label
					container.add(valueLabel);

					// change text to white for contrast
					if (createdSet[i].getValue() < 5)
						valueLabel.setForeground(Color.WHITE);
				} else
				{
					// set background colors for the invalid color panels
					container.setBackground(invalidColor);
					valueLabel.setForeground(invalidColor);
					chromaLabel.setForeground(invalidColor);
				}
			}

			// add labels
			// container.add(hueLabel);

			// add the new label with color to the row
			row.add(container);
		}

		// return the newly created row
		return row;
	}

	/**
	 * Create the base layouts and frames.
	 * 
	 * @return a JFrame
	 */
	public static JTabbedPane createBase()
	{

		// top level panel that can be replaced
		JPanel topContainer = new JPanel();

		// panel to select colors
		JPanel selectorPanel = new JPanel();

		// create the main panel
		selectorPanel.setLayout(new BoxLayout(selectorPanel, BoxLayout.Y_AXIS));

		MunsellColor start = null;

		// uses the reprentableHue to get a Hue that can be displayed
		start = new MunsellColor(Converter.getRepresentableHue(getUserHueNum()), getUserHueCode(), 10.0f, 0.0f);

		// create the bottom panel
		JPanel bottomPanel = new JPanel();

		JTextField hueField = new JTextField(10);
		hueField.setText(getUserHueNum() + getUserHueCode().toString());

		JButton changeHueButton = new JButton("Change Hue");

		// add the action listener
		changeHueButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{

				// get the input text
				String input = hueField.getText();

				// pattern to look for letters
				Pattern pattern = Pattern.compile("[A-Z]|[a-z]");

				// look for the letter
				Matcher matcher = pattern.matcher(input);

				// chMainUI.javaeck for a match
				if (matcher.find())
				{
					// get the index for where the letter(s) starts
					int index = matcher.start();

					setUserHueNum(Float.valueOf(input.substring(0, index)));

					// get the string version of the hueCode
					String hueCode = input.substring(index).toUpperCase();

					// "convert" the string hue code to the enum version
					switch (hueCode)
					{
					case "R":
						setUserHueCode(HueCode.R);
						break;
					case "RP":
						setUserHueCode(HueCode.RP);
						break;
					case "P":
						setUserHueCode(HueCode.P);
						break;
					case "PB":
						setUserHueCode(HueCode.PB);
						break;
					case "B":
						setUserHueCode(HueCode.B);
						break;
					case "BG":
						setUserHueCode(HueCode.BG);
						break;
					case "G":
						setUserHueCode(HueCode.G);
						break;
					case "GY":
						setUserHueCode(HueCode.GY);
						break;
					case "Y":
						setUserHueCode(HueCode.Y);
						break;
					case "YR":
						setUserHueCode(HueCode.YR);
						break;
					default:
						break;
					}
				}

				// redraw everything
				redraw(0);
			}

		});

		// add the hue text field
		bottomPanel.add(hueField);

		// add the change hue button
		bottomPanel.add(changeHueButton);

		// set opaque so colors can be set
		selectorPanel.setOpaque(true);

		// create 9 rows
		for (int i = COLOR_ROWS; i > 0; i--)
		{
			// add all the rows
			selectorPanel.add(createChromaRow(start));

			start.setValue(start.getValue() - 1);
		}

		topContainer.setLayout(new BorderLayout());
		// topContainer.add(createValueColumn(),BorderLayout.WEST);
		// topContainer.add(paletteTab(), BorderLayout.EAST);
		topContainer.add(selectorPanel, BorderLayout.CENTER);
		topContainer.add(bottomPanel, BorderLayout.SOUTH);
		// frame.add(test);

		// create a new tabbed pane
		tabs = new JTabbedPane();

		tabs.addTab("Main Color", null, topContainer);

		// don't show the harmonious colors tab if there isn't a selected color
		if (selectedColor != null)
			tabs.addTab("Harmonious Colors", null, harmoniousTab(selectedColor));

		// add the palette tab
		tabs.addTab("Image", null, imageTab());

		// return the panel
		return tabs;

	}

	/**
	 * Create a formatted panel for each harmonious color.
	 * 
	 * @param harmoniousColors list of harmonious colors
	 * @param title            the title of the type of harmonious colors
	 * @return a formatted JPanel
	 */
	public static JPanel constructHarmoniousPane(MunsellColor[] harmoniousColors, String title)
	{

		// pane to return
		JPanel formattedPane = new JPanel();

		// set box layout
		formattedPane.setLayout(new BoxLayout(formattedPane, BoxLayout.Y_AXIS));

		formattedPane.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

		// set the title
		JLabel harmoniousTitle = new JLabel(title);

		// make font bolder and larger
		harmoniousTitle.setFont(harmoniousTitle.getFont().deriveFont(Font.BOLD, 14f));

		// set horizontal alignment (not working :/)
		harmoniousTitle.setHorizontalAlignment(JLabel.LEFT);

		// add the title
		formattedPane.add(harmoniousTitle);

		// create a container to hold the colors
		JPanel colorContainer = new JPanel();

		// set the grid layout columns to the number of colors
		colorContainer.setLayout(new GridLayout(1, harmoniousColors.length, 10, 0));

		// iterate through the colors
		for (MunsellColor color : harmoniousColors)
		{
			// new color panel
			JPanel colorPanel = new JPanel();

			// colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));

			// set opaque
			colorPanel.setOpaque(true);

			Color labelColor = converter.munsellToRGB(color);

			// panel to hold rgb info
			JPanel rgbPanel = new JPanel();
			rgbPanel.setOpaque(true);

			// panel to hold munsell info
			JPanel munsellPanel = new JPanel();
			munsellPanel.setOpaque(true);

			// set the background to the munsell color
			colorPanel.setBackground(labelColor);

			// add rgb values
			rgbPanel.add(new JLabel("R: " + String.valueOf(labelColor.getRed())));
			rgbPanel.add(new JLabel("G: " + String.valueOf(labelColor.getGreen())));
			rgbPanel.add(new JLabel("B: " + String.valueOf(labelColor.getBlue())));

			// add munsell values
			munsellPanel.add(new JLabel("H: " + String.valueOf(color.getHueNum())));
			munsellPanel.add(new JLabel("V: " + String.valueOf(color.getValue())));
			munsellPanel.add(new JLabel("C: " + String.valueOf(color.getChroma())));

			// add panels
			colorPanel.add(rgbPanel);
			colorPanel.add(munsellPanel);

			// add the new color panel
			colorContainer.add(colorPanel);

			formattedPane.add(colorContainer);
		}

		// return the formatted pane
		return formattedPane;
	}

	/**
	 * Create the harmonious colors tab.
	 * 
	 * @param start the color to start with.
	 * @return a JPanel with harmonious colors
	 */
	public static JPanel harmoniousTab(MunsellColor start)
	{

		// inner pane for scroll pane
		JPanel harmoniousContainer = new JPanel();

		harmoniousContainer.setLayout(new BoxLayout(harmoniousContainer, BoxLayout.Y_AXIS));

		// create the container to return
		// JScrollPane harmoniousContainer = new JScrollPane(innerPane);

		harmoniousContainer.setOpaque(true);

		// get harmonious colors
		MunsellColor[] analogous = MunsellColor.matchAnalogous(start);
		MunsellColor[] square = MunsellColor.matchSquare(start);
		MunsellColor[] triad = MunsellColor.matchTriad(start);

		// matchTetrad returns 2D array, so create a 1D array
		// to copy the values into
		MunsellColor[][] tetradOriginal = MunsellColor.matchTetrad(start, analogous[0]);
		MunsellColor[] tetrad = new MunsellColor[4];

		// get complementary color and add it to an array
		MunsellColor[] complementary = new MunsellColor[1];
		complementary[0] = MunsellColor.matchComplementary(start);

		// count variable
		int count = 0;

		// copy 2D array values into 1D array
		for (int i = 0; i < tetradOriginal.length; i++)
		{
			for (int j = 0; j < tetradOriginal[i].length; j++)
			{
				tetrad[count] = tetradOriginal[i][j];
				count++;
			}
		}

		// create and add the complementary color panes
		JPanel complementaryPane = constructHarmoniousPane(complementary, "Complementary");
		harmoniousContainer.add(complementaryPane);
		JPanel analogousPane = constructHarmoniousPane(analogous, "Analogous");
		harmoniousContainer.add(analogousPane);
		JPanel triadPane = constructHarmoniousPane(triad, "Triad");
		harmoniousContainer.add(triadPane);
		JPanel squarePane = constructHarmoniousPane(square, "Square");
		harmoniousContainer.add(squarePane);
		JPanel tetradPane = constructHarmoniousPane(tetrad, "Tetrad");
		harmoniousContainer.add(tetradPane);

		// return the container
		return harmoniousContainer;
	}

	/**
	 * Populate a scrollpane with colors from the user's palette
	 * 
	 * @param scrollPane JScrollPane to be populated
	 */
	public static JScrollPane createScrollPane()
	{

		// top level panel to hold scroll pane items
		JPanel scrollContainer = new JPanel();
		scrollContainer.setLayout(new BoxLayout(scrollContainer, BoxLayout.Y_AXIS));

		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;

		// pane to be returned
		JScrollPane scrollPane = new JScrollPane(scrollContainer, v, h);

		// get the list of colors
		List<MunsellColor> colorList = palette.getColorList();

		for (MunsellColor color : colorList)
		{
			// color panel to hold color and color info
			JPanel colorPanel = new JPanel();
			colorPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
			colorPanel.setBackground(Color.WHITE);

			colorPanel.addMouseListener(new PaletteListener());

			// colorPanel.setPreferredSize(new Dimension(150, 30));
			colorPanel.setMaximumSize(new Dimension(500, 60));
			colorPanel.setMinimumSize((new Dimension(500, 60)));

			// color properties
			String hueString = color.getHueNum() + "" + color.getHueCode();
			float chroma = color.getChroma();
			float value = color.getValue();

			// create color labels
			JLabel hueLabel = new JLabel("H: " + hueString);
			JLabel valueLabel = new JLabel("V: " + value);
			JLabel chromaLabel = new JLabel("C: " + chroma);

			// create a container for the color labels
			JPanel labelContainer = new JPanel();
			labelContainer.setOpaque(false);
			labelContainer.setLayout(new BoxLayout(labelContainer, BoxLayout.Y_AXIS));
			labelContainer.add(hueLabel);
			labelContainer.add(valueLabel);
			labelContainer.add(chromaLabel);

			// color block that shows the color
			JPanel colorBlock = new JPanel();
			colorBlock.setBackground(converter.munsellToRGB(color));
			colorBlock.setPreferredSize(new Dimension(50, 50));

			// add to color panel
			colorPanel.add(colorBlock);
			colorPanel.add(labelContainer);

			// add everything to the top container
			scrollContainer.add(colorPanel, BorderLayout.CENTER);
		}

		// return the pane
		return scrollPane;

	}

	/**
	 * Create the palette tab.
	 * 
	 * @return a JPanel with a color palette and mixer
	 */
	public static JPanel paletteTab()
	{
		// top level pane to be returned
		JPanel palettePane = new JPanel();
		palettePane.setLayout(new BorderLayout());

		palettePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		// left panel that holds palette colors
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());

		// create a new text label for the palette section
		JLabel paletteLabel = new JLabel("Palette");

		// scroll pane for holding colors
		JScrollPane paletteScroller = createScrollPane();

		// section for holding palette actions
		JPanel actionPanel = new JPanel();
		actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));

		// create buttons
		JButton mixButton = new JButton("Mix Colors");
		JButton clearButton = new JButton("Clear Palette");

		// create a panel to hold the buttons
		JPanel buttonPanel = new JPanel();

		// add the buttons
		buttonPanel.add(mixButton);
		buttonPanel.add(clearButton);

		mixButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{

				// get the active panels for the colors to mix
				List<JPanel> activePanels = PaletteListener.getActivePanels();

				// must have two colors to mix together
				// TODO: add dialog message to let user know that they need
				// to mix two colors
				if (activePanels.size() == 2)
				{
					// get the colors we need to mix
					MunsellColor color1 = PaletteListener.getColor(activePanels.get(0));
					MunsellColor color2 = PaletteListener.getColor(activePanels.get(1));

					// get a reference to mix colors
					MunsellColor mixer = new MunsellColor();

					// mix the colors
					mixedColor = mixer.mixColors(color1, color2);

					redraw(0);

					// reset the active panels
					PaletteListener.updateActivePanels();
				}

			}

		});

		// clear the list on clicked
		clearButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent evt)
			{
				// empty the palette
				MainUI.getPalette().emptyPalette();

				// clear the active panels
				PaletteListener.clearActivePanels();

				// redraw the frame
				redraw(0);
			}

		});

		// container that will hold the mixed color
		// and that colors properties (rgb & hvc)
		JPanel mixedColorContainer = new JPanel();
		mixedColorContainer.setLayout(new GridLayout(3, 1));

		// show mixed color attributes if colors have been mixed
		if (mixedColor != null)
		{

			// get the mixed color in RGB
			Color mixedRGB = converter.munsellToRGB(mixedColor);

			// set the background
			mixedColorContainer.setBackground(mixedRGB);

			// color properties
			String hueString = mixedColor.getHueNum() + "" + mixedColor.getHueCode();
			float chroma = mixedColor.getChroma();
			float value = mixedColor.getValue();

			// create color labels
			JLabel colorLabel = new JLabel("H: " + hueString + "  V: " + value + "  C: " + chroma);
			colorLabel.setHorizontalAlignment(JLabel.CENTER);
			colorLabel.setForeground(Color.WHITE);

			// set the color to black for contrast if needed
			if (mixedColor.getValue() > 5)
			{
				colorLabel.setForeground(Color.BLACK);
			}

			// colorLabel.setOpaque(true);
			// colorLabel.setBackground(Color.WHITE);

			// add labels
			mixedColorContainer.add(Box.createHorizontalGlue());
			mixedColorContainer.add(colorLabel);
			mixedColorContainer.add(Box.createVerticalGlue());

		}

		// add button panel
		actionPanel.add(buttonPanel);

		// add mix color container
		actionPanel.add(mixedColorContainer);

		// add components to left panel
		leftPanel.add(paletteScroller, BorderLayout.CENTER);
		leftPanel.add(actionPanel, BorderLayout.SOUTH);
		leftPanel.add(paletteLabel, BorderLayout.NORTH);

		// add left panel to palettePane
		palettePane.add(leftPanel, BorderLayout.WEST);

		// return the split pane
		return palettePane;
	}

	/**
	 * Makes an imageTab where you can upload an image.
	 * 
	 * @return JPanel for image
	 */
	public static JPanel imageTab()
	{

		// main panel to be returned
		JPanel imagePanel = new JPanel();

		imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));

		Scanner scanner = new Scanner(System.in);

		// create a container to hold the buttons
		JPanel buttonContainer = new JPanel();

		// create the buttons
		JButton imageButton = new JButton("Open Image");
		JButton posterizeButton = new JButton("Posterize Image");

		JTextField posterizeValue = new JTextField(10);

		buttonContainer.add(imageButton);
		buttonContainer.add(posterizeButton);
		buttonContainer.add(posterizeValue);

		imageButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				clicked = true;
				try
				{
					JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

					// did the user select an image
					if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
					{
						// set found flag
						found = true;

						// grab the image that the user selected
						image = ImageIO.read(new File(fileChooser.getSelectedFile().getAbsolutePath()));
					}

					// redraw
					redraw(2);
				} catch (IOException e)
				{
					System.out.println("Image not found.");
				} // maybe use loop for this or enter some input to cancel
			}

		});

		// add image
		imagePanel.add(buttonContainer); // maybe move this?

		// was the image button clicked?
		if (clicked)
		{

			posterizeButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{

					try
					{
						int range = Integer.parseInt(posterizeValue.getText());
						if (range < 0 || range > 255)
						{
							throw new NumberFormatException();
						}
						for (int i = 0; i < image.getWidth(); i++)
						{ // maybe switch height and width
							for (int j = 0; j < image.getHeight(); j++)
							{
								int temp = image.getRGB(i, j) % range;
								image.setRGB(i, j, temp);
							}
						}

						// redraw
						redraw(2);
					} catch (NumberFormatException e)
					{
						System.out.println("Invalid Input.");
					} // maybe use loop for this or enter some input to cancel
				}
			});

			// scale the image to 500 x 500
			Image scaled = image.getScaledInstance(500, 500, Image.SCALE_FAST);

			JLabel imageLabel = new JLabel(new ImageIcon(scaled));
			imagePanel.add(imageLabel); // maybe move this stuff

			imageLabel.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent e)
				{
					int x = e.getX();
					int y = e.getY();
					Color rgb = new Color(image.getRGB(x, y));
					MunsellColor pixelColor = converter.rgbToMunsell(rgb);

					redraw(2);
					// palette.addColor(pixelColor);
					// System.out.println(pixelColor);
				}
			});

		}

		// scanner.close();
		return imagePanel;
	}

	/**
	 * void selectPixel() { //something.addMouseListener ??
	 * 
	 * @Override public void mouseClicked(MouseEvent e) { int x=e.getX(); int
	 *           y=e.getY(); Color rgb = new Color(image.getRGB(x, y)); MunsellColor
	 *           pixelColor = new MunsellColor(rgb); //and then add this to palette?
	 *           }
	 * 
	 *           }
	 **/

}
