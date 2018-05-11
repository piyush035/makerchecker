
package com.rennover.client.framework.widget.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

/**
 * Instances of this class lay out the control children of a
 * <code>Composite</code> in a grid.
 * <p>
 * <code>GridLayout</code> has a number of configuration fields, and the
 * controls it lays out can have an associated layout data object, called
 * <code>GridData</code>. The power of <code>GridLayout</code> lies in the
 * ability to configure <code>GridData</code> for each control in the layout.
 * </p>
 * <p>
 * The following code creates a shell managed by a <code>GridLayout</code>
 * with 3 columns:
 * 
 * <pre>
 * Display display = new Display();
 * Shell shell = new Shell(display);
 * GridLayout gridLayout = new GridLayout();
 * gridLayout.numColumns = 3;
 * shell.setLayout(gridLayout);
 * </pre>
 * 
 * The <code>numColumns</code> field is the most important field in a
 * <code>GridLayout</code>. Widgets are laid out in columns from left to
 * right, and a new row is created when <code>numColumns</code> + 1 controls
 * are added to the <code>Composite<code>.
 * </p>
 *
 * @see GridData
 */
public final class SWTGridLayout extends SWTLayout implements LayoutManager2
{
	/**
	 * marginWidth specifies the number of pixels of horizontal margin that will
	 * be placed along the left and right edges of the layout.
	 * 
	 * The default value is 4.
	 */
	public int m_marginWidth = 4;

	/**
	 * marginHeight specifies the number of pixels of vertical margin that will
	 * be placed along the top and bottom edges of the layout.
	 * 
	 * The default value is 4.
	 */
	public int m_marginHeight = 4;

	/**
	 * numColumns specifies the number of cell columns in the layout.
	 * 
	 * The default value is 1.
	 */
	public int m_numColumns = 1;

	/**
	 * makeColumnsEqualWidth specifies whether all columns in the layout will be
	 * forced to have the same width.
	 * 
	 * The default value is false.
	 */
	public boolean m_makeColumnsEqualWidth = false;

	/**
	 * horizontalSpacing specifies the number of pixels between the right edge
	 * of one cell and the left edge of its neighbouring cell to the right.
	 * 
	 * The default value is 4.
	 */
	public int m_horizontalSpacing = 4;

	/**
	 * verticalSpacing specifies the number of pixels between the bottom edge of
	 * one cell and the top edge of its neighbouring cell underneath.
	 * 
	 * The default value is 4.
	 */
	public int m_verticalSpacing = 4;

	// Private variables. Cached values used to cut down on grid calculations.
	Vector m_grid = new Vector();

	int[] m_pixelColumnWidths;

	int[] m_pixelRowHeights;

	int[] m_expandableColumns;

	int[] m_expandableRows;

	Map m_layoutDataMap = new Hashtable();

	/**
	 * Constructs a new instance of this class.
	 */
	public SWTGridLayout()
	{
	}

	public SWTGridLayout(boolean border)
	{
		if (!border)
		{
			m_marginHeight = 0;
			m_marginWidth = 0;
		}
	}

	public SWTGridLayout(int numColumns, boolean makeColumnsEqualWidth, boolean border)
	{
		this(numColumns, makeColumnsEqualWidth);

		if (!border)
		{
			m_marginHeight = 0;
			m_marginWidth = 0;
		}
	}

	/**
	 * Constructs a new instance of this class given the number of columns, and
	 * whether or not the columns should be forced to have the same width.
	 * 
	 * @param numColumns
	 *            the number of columns in the grid
	 * @param makeColumnsEqualWidth
	 *            whether or not the columns will have equal width
	 * 
	 * @since 2.0
	 */
	public SWTGridLayout(int numColumns, boolean makeColumnsEqualWidth)
	{
		this.m_numColumns = numColumns;
		this.m_makeColumnsEqualWidth = makeColumnsEqualWidth;
	}

	void adjustGridDimensions(Container composite, boolean flushCache)
	{
		// Ensure that controls that span more than one row or column have
		// enough space.
		for (int row = 0; row < m_grid.size(); row++)
		{
			for (int column = 0; column < m_numColumns; column++)
			{
				GridData spec = ((GridData[]) m_grid.elementAt(row))[column];
				if (spec.isItemData())
				{
					// Widgets spanning columns.
					if (spec.m_hSpan > 1)
					{
						Component child = composite.getComponents()[spec.m_childIndex];
						Dimension extent = computeSize(child, spec.m_widthHint, spec.m_heightHint, flushCache);

						// Calculate the size of the control's spanned columns.
						int lastSpanIndex = column + spec.m_hSpan;
						int spannedSize = 0;
						for (int c = column; c < lastSpanIndex; c++)
						{
							spannedSize = spannedSize + m_pixelColumnWidths[c] + m_horizontalSpacing;
						}
						spannedSize = spannedSize - m_horizontalSpacing;

						// If the spanned columns are not large enough to
						// display the control, adjust the column
						// sizes to account for the extra space that is needed.
						if (extent.width + spec.m_horizontalIndent > spannedSize)
						{
							int extraSpaceNeeded = extent.width + spec.m_horizontalIndent - spannedSize;
							int lastColumn = column + spec.m_hSpan - 1;
							int colWidth;
							if (m_makeColumnsEqualWidth)
							{
								// Evenly distribute the extra space amongst all
								// of the columns.
								int columnExtra = extraSpaceNeeded / m_numColumns;
								int columnRemainder = extraSpaceNeeded % m_numColumns;
								for (int i = 0; i < m_pixelColumnWidths.length; i++)
								{
									colWidth = m_pixelColumnWidths[i] + columnExtra;
									m_pixelColumnWidths[i] = colWidth;
								}
								colWidth = m_pixelColumnWidths[lastColumn] + columnRemainder;
								m_pixelColumnWidths[lastColumn] = colWidth;
							} else
							{
								Vector localExpandableColumns = new Vector();
								for (int i = column; i <= lastColumn; i++)
								{
									for (int j = 0; j < m_expandableColumns.length; j++)
									{
										if (m_expandableColumns[j] == i)
										{
											localExpandableColumns.addElement(new Integer(i));
										}
									}
								}
								if (localExpandableColumns.size() > 0)
								{
									// If any of the control's columns grab
									// excess space, allocate the space amongst
									// those columns.
									int columnExtra = extraSpaceNeeded / localExpandableColumns.size();
									int columnRemainder = extraSpaceNeeded % localExpandableColumns.size();
									for (int i = 0; i < localExpandableColumns.size(); i++)
									{
										int expandableCol = ((Integer) localExpandableColumns.elementAt(i)).intValue();
										colWidth = m_pixelColumnWidths[expandableCol] + columnExtra;
										m_pixelColumnWidths[expandableCol] = colWidth;
									}
									colWidth = m_pixelColumnWidths[lastColumn] + columnRemainder;
									m_pixelColumnWidths[lastColumn] = colWidth;
								} else
								{
									// Add the extra space to the control's last
									// column if none of its columns grab excess
									// space.
									colWidth = m_pixelColumnWidths[lastColumn] + extraSpaceNeeded;
									m_pixelColumnWidths[lastColumn] = colWidth;
								}
							}
						}
					}

					// Widgets spanning rows.
					if (spec.m_verticalSpan > 1)
					{
						Component child = composite.getComponents()[spec.m_childIndex];
						Dimension extent = computeSize(child, spec.m_widthHint, spec.m_heightHint, flushCache);

						// Calculate the size of the control's spanned rows.
						int lastSpanIndex = row + spec.m_verticalSpan;
						int spannedSize = 0;
						for (int r = row; r < lastSpanIndex; r++)
						{
							spannedSize = spannedSize + m_pixelRowHeights[r] + m_verticalSpacing;
						}
						spannedSize = spannedSize - m_verticalSpacing;

						// If the spanned rows are not large enough to display
						// the control, adjust the row
						// sizes to account for the extra space that is needed.
						if (extent.height > spannedSize)
						{
							int extraSpaceNeeded = extent.height - spannedSize;
							int lastRow = row + spec.m_verticalSpan - 1;
							int rowHeight;
							Vector localExpandableRows = new Vector();
							for (int i = row; i <= lastRow; i++)
							{
								for (int j = 0; j < m_expandableRows.length; j++)
								{
									if (m_expandableRows[j] == i)
									{
										localExpandableRows.addElement(new Integer(i));
									}
								}
							}
							if (localExpandableRows.size() > 0)
							{
								// If any of the control's rows grab excess
								// space, allocate the space amongst those rows.
								int rowExtra = extraSpaceNeeded / localExpandableRows.size();
								int rowRemainder = extraSpaceNeeded % localExpandableRows.size();
								for (int i = 0; i < localExpandableRows.size(); i++)
								{
									int expandableRow = ((Integer) localExpandableRows.elementAt(i)).intValue();
									rowHeight = m_pixelRowHeights[expandableRow] + rowExtra;
									m_pixelRowHeights[expandableRow] = rowHeight;
								}
								rowHeight = m_pixelRowHeights[lastRow] + rowRemainder;
								m_pixelRowHeights[lastRow] = rowHeight;
							} else
							{
								// Add the extra space to the control's last row
								// if no rows grab excess space.
								rowHeight = m_pixelRowHeights[lastRow] + extraSpaceNeeded;
								m_pixelRowHeights[lastRow] = rowHeight;
							}
						}
					}
				}
			}
		}
	}

	void calculateGridDimensions(Container composite, boolean flushCache)
	{
		int maxWidth;
		int childWidth;
		int maxHeight;
		int childHeight;

		//
		Component[] children = composite.getComponents();
		Dimension[] childSizes = new Dimension[children.length];
		m_pixelColumnWidths = new int[m_numColumns];
		m_pixelRowHeights = new int[m_grid.size()];

		// Loop through the grid by column to get the width that each column
		// needs to be.
		// Each column will be as wide as its widest control.
		for (int column = 0; column < m_numColumns; column++)
		{
			maxWidth = 0;
			for (int row = 0; row < m_grid.size(); row++)
			{
				GridData spec = ((GridData[]) m_grid.elementAt(row))[column];
				if (spec.isItemData())
				{
					Component child = children[spec.m_childIndex];
					childSizes[spec.m_childIndex] = computeSize(child, spec.m_widthHint, spec.m_heightHint, flushCache);
					childWidth = childSizes[spec.m_childIndex].width + spec.m_horizontalIndent;
					if (spec.m_hSpan == 1)
					{
						maxWidth = Math.max(maxWidth, childWidth);
					}
				}
			}

			// Cache the values for later use.
			m_pixelColumnWidths[column] = maxWidth;
		}

		// 
		if (m_makeColumnsEqualWidth)
		{
			maxWidth = 0;
			// Find the largest column size that is necessary and make each
			// column that size.
			for (int i = 0; i < m_numColumns; i++)
			{
				maxWidth = Math.max(maxWidth, m_pixelColumnWidths[i]);
			}
			for (int i = 0; i < m_numColumns; i++)
			{
				m_pixelColumnWidths[i] = maxWidth;
			}
		}

		// Loop through the grid by row to get the height that each row needs to
		// be.
		// Each row will be as high as its tallest control.
		for (int row = 0; row < m_grid.size(); row++)
		{
			maxHeight = 0;
			for (int column = 0; column < m_numColumns; column++)
			{
				GridData spec = ((GridData[]) m_grid.elementAt(row))[column];
				if (spec.isItemData())
				{
					childHeight = childSizes[spec.m_childIndex].height;
					if (spec.m_verticalSpan == 1)
					{
						maxHeight = Math.max(maxHeight, childHeight);
					}
				}
			}

			// Cache the values for later use.
			m_pixelRowHeights[row] = maxHeight;
		}
	}

	void computeExpandableCells()
	{
		// If a control grabs excess horizontal space, the last column that the
		// control spans
		// will be expandable. Similarly, if a control grabs excess vertical
		// space, the
		// last row that the control spans will be expandable.
		Hashtable growColumns = new Hashtable();
		Hashtable growRows = new Hashtable();
		for (int col = 0; col < m_numColumns; col++)
		{
			for (int row = 0; row < m_grid.size(); row++)
			{
				GridData spec = ((GridData[]) m_grid.elementAt(row))[col];
				if (spec.m_grabExcessHorizontalSpace)
				{
					growColumns.put(new Integer(col + spec.m_hSpan - 1), new Object());
				}
				if (spec.m_grabExcessVerticalSpace)
				{
					growRows.put(new Integer(row + spec.m_verticalSpan - 1), new Object());
				}
			}
		}

		// Cache the values. These values are used later during children layout.
		int i = 0;
		Enumeration enum1 = growColumns.keys();
		m_expandableColumns = new int[growColumns.size()];
		while (enum1.hasMoreElements())
		{
			m_expandableColumns[i] = ((Integer) enum1.nextElement()).intValue();
			i = i + 1;
		}
		i = 0;
		enum1 = growRows.keys();
		m_expandableRows = new int[growRows.size()];
		while (enum1.hasMoreElements())
		{
			m_expandableRows[i] = ((Integer) enum1.nextElement()).intValue();
			i = i + 1;
		}
	}

	Dimension computeLayoutSize(Container composite, int wHint, int hHint, boolean flushCache)
	{
		int totalMarginHeight;
		int totalMarginWidth;
		int totalWidth;
		int totalHeight;
		int cols;
		int rows;

		// Initialize the grid and other cached information that help with the
		// grid layout.
		if (m_grid.size() == 0)
		{
			createGrid(composite);
			calculateGridDimensions(composite, flushCache);
			computeExpandableCells();
			adjustGridDimensions(composite, flushCache);
		}

		//
		cols = m_numColumns;
		rows = m_grid.size();
		totalMarginHeight = m_marginHeight;
		totalMarginWidth = m_marginWidth;

		// The total width is the margin plus border width plus space between
		// each column,
		// plus the width of each column.
		totalWidth = (totalMarginWidth * 2) + ((cols - 1) * m_horizontalSpacing);

		// Add up the width of each column.
		for (int i = 0; i < m_pixelColumnWidths.length; i++)
		{
			totalWidth = totalWidth + m_pixelColumnWidths[i];
		}

		// The total height is the margin plus border height, plus space between
		// each row,
		// plus the height of the tallest child in each row.
		totalHeight = (totalMarginHeight * 2) + ((rows - 1) * m_verticalSpacing);

		// Add up the height of each row.
		for (int i = 0; i < m_pixelRowHeights.length; i++)
		{
			totalHeight = totalHeight + m_pixelRowHeights[i];
		}

		if (wHint != SWTConstants.DEFAULT)
		{
			totalWidth = wHint;
		}
		;
		if (hHint != SWTConstants.DEFAULT)
		{
			totalHeight = hHint;
		}
		;
		// The preferred extent is the width and height that will accomodate the
		// grid's controls.
		return new Dimension(totalWidth, totalHeight);
	}

	protected Dimension computeSize(Container composite, int wHint, int hHint, boolean flushCache)
	{
		Component[] children = composite.getComponents();
		int numChildren = children.length;

		if (numChildren == 0)
		{
			return new Dimension(0, 0);
		}

		if (flushCache)
		{
			// Cause the grid and its related information to be calculated
			// again.
			m_grid.removeAllElements();
		}
		return computeLayoutSize(composite, wHint, hHint, flushCache);
	}

	Point getFirstEmptyCell(int row, int column)
	{
		GridData[] rowData = (GridData[]) m_grid.elementAt(row);
		while (column < m_numColumns && rowData[column] != null)
		{
			column++;
		}
		if (column == m_numColumns)
		{
			row++;
			column = 0;
			if (row == m_grid.size())
			{
				m_grid.addElement(emptyRow());
			}
			return getFirstEmptyCell(row, column);
		}
		return new Point(row, column);
	}

	Point getLastEmptyCell(int row, int column)
	{
		GridData[] rowData = (GridData[]) m_grid.elementAt(row);
		while (column < m_numColumns && rowData[column] == null)
		{
			column++;
		}
		return new Point(row, column - 1);
	}

	Point getCell(int row, int column, int width, int height)
	{
		Point start = getFirstEmptyCell(row, column);
		Point end = getLastEmptyCell(start.x, start.y);
		if (end.y + 1 - start.y >= width)
		{
			return start;
		}
		GridData[] rowData = (GridData[]) m_grid.elementAt(start.x);
		for (int j = start.y; j < end.y + 1; j++)
		{
			GridData spacerSpec = new GridData();
			spacerSpec.m_isItemData = false;
			rowData[j] = spacerSpec;
		}
		return getCell(end.x, end.y, width, height);
	}

	void createGrid(Container composite)
	{
		int row;
		int column;
		int rowFill;
		int columnFill;
		Component[] children;
		GridData spacerSpec;

		// 
		children = composite.getComponents();

		// 
		m_grid.addElement(emptyRow());
		row = 0;
		column = 0;

		// Loop through the children and place their associated layout specs in
		// the
		// grid. Placement occurs left to right, top to bottom (i.e., by row).
		for (int i = 0; i < children.length; i++)
		{
			// Find the first available spot in the grid.
			Component child = children[i];

			GridData spec = getLayoutData(child);
			if (spec == null)
			{
				spec = new GridData();
				setLayoutData(child, spec);
			}
			spec.m_hSpan = Math.min(spec.m_horizontalSpan, m_numColumns);
			Point p = getCell(row, column, spec.m_hSpan, spec.m_verticalSpan);
			row = p.x;
			column = p.y;

			// The vertical span for the item will be at least 1. If it is > 1,
			// add other rows to the grid.
			for (int j = 2; j <= spec.m_verticalSpan; j++)
			{
				if (row + j > m_grid.size())
				{
					m_grid.addElement(emptyRow());
				}
			}

			((GridData[]) m_grid.elementAt(row))[column] = spec;
			spec.m_childIndex = i;

			// Put spacers in the grid to account for the item's vertical and
			// horizontal
			// span.
			rowFill = spec.m_verticalSpan - 1;
			columnFill = spec.m_hSpan - 1;
			for (int r = 1; r <= rowFill; r++)
			{
				for (int c = 0; c < spec.m_hSpan; c++)
				{
					spacerSpec = new GridData();
					spacerSpec.m_isItemData = false;
					((GridData[]) m_grid.elementAt(row + r))[column + c] = spacerSpec;
				}
			}
			for (int c = 1; c <= columnFill; c++)
			{
				for (int r = 0; r < spec.m_verticalSpan; r++)
				{
					spacerSpec = new GridData();
					spacerSpec.m_isItemData = false;
					((GridData[]) m_grid.elementAt(row + r))[column + c] = spacerSpec;
				}
			}
			column = column + spec.m_hSpan - 1;
		}

		// Fill out empty grid cells with spacers.
		for (int r = row; r < m_grid.size(); r++)
		{
			GridData[] rowData = (GridData[]) m_grid.elementAt(r);
			for (int c = 0; c < m_numColumns; c++)
			{
				if (rowData[c] == null)
				{
					spacerSpec = new GridData();
					spacerSpec.m_isItemData = false;
					rowData[c] = spacerSpec;
				}
			}
		}
	}

	GridData[] emptyRow()
	{
		GridData[] row = new GridData[m_numColumns];
		for (int i = 0; i < m_numColumns; i++)
		{
			row[i] = null;
		}
		return row;
	}

	protected void layout(Container composite, boolean flushCache)
	{
		int[] columnWidths;
		int[] rowHeights;
		int rowSize;
		int rowY;
		int columnX;
		int compositeWidth;
		int compositeHeight;
		int excessHorizontal;
		int excessVertical;
		Component[] children;
		if (flushCache)
		{
			// Cause the grid and its related information to be calculated
			// again.
			m_grid.removeAllElements();
		}

		children = composite.getComponents();
		if (children.length == 0)
		{
			return;
		}

		//
		Dimension extent = computeSize(composite, SWTConstants.DEFAULT, SWTConstants.DEFAULT, flushCache);
		Rectangle clientArea = getClientArea(composite);

		columnWidths = new int[m_numColumns];
		for (int i = 0; i < m_pixelColumnWidths.length; i++)
		{
			columnWidths[i] = m_pixelColumnWidths[i];
		}
		rowHeights = new int[m_grid.size()];
		for (int i = 0; i < m_pixelRowHeights.length; i++)
		{
			rowHeights[i] = m_pixelRowHeights[i];
		}
		int columnWidth = 0;
		rowSize = Math.max(1, m_grid.size());

		// 
		compositeWidth = extent.width;
		compositeHeight = extent.height;

		// Calculate whether or not there is any extra space or not enough space
		// due to a resize
		// operation. Then allocate/deallocate the space to columns and rows
		// that are expandable.
		// If a control grabs excess space, its last column or row will be
		// expandable.
		excessHorizontal = clientArea.width - compositeWidth;
		excessVertical = clientArea.height - compositeHeight;

		if (excessHorizontal >= 0)
		{
			// Case when the horizontal excess space is positive
			// Allocate/deallocate horizontal space.
			if (m_expandableColumns.length != 0)
			{
				int excess;
				int remainder;
				int last;
				int colWidth;
				excess = excessHorizontal / m_expandableColumns.length;
				remainder = excessHorizontal % m_expandableColumns.length;
				last = 0;
				for (int i = 0; i < m_expandableColumns.length; i++)
				{
					int expandableCol = m_expandableColumns[i];
					colWidth = columnWidths[expandableCol];
					colWidth = colWidth + excess;
					columnWidths[expandableCol] = colWidth;
					last = Math.max(last, expandableCol);
				}
				colWidth = columnWidths[last];
				colWidth = colWidth + remainder;
				columnWidths[last] = colWidth;
			}

			// Go through all specs in each expandable column and get the
			// maximum specified
			// widthHint. Use this as the minimumWidth for the column.
			for (int i = 0; i < m_expandableColumns.length; i++)
			{
				int expandableCol = m_expandableColumns[i];
				int colWidth = columnWidths[expandableCol];
				int minWidth = 0;
				for (int j = 0; j < m_grid.size(); j++)
				{
					GridData[] row = (GridData[]) m_grid.elementAt(j);
					GridData spec = row[expandableCol];
					if (spec.m_hSpan == 1)
					{
						minWidth = Math.max(minWidth, spec.m_widthHint);
					}
				}
				columnWidths[expandableCol] = Math.max(colWidth, minWidth);
			}
		} else
		{
			// Case when the horizontal excess space is negative
			// Allocate/deallocate horizontal space.
			if (m_expandableColumns.length != 0)
			{
				int excess;
				int remainder;
				int last;
				int colWidth;
				excess = excessHorizontal / m_expandableColumns.length;
				remainder = excessHorizontal % m_expandableColumns.length;
				last = 0;
				for (int i = 0; i < m_expandableColumns.length; i++)
				{
					int expandableCol = m_expandableColumns[i];
					colWidth = columnWidths[expandableCol];
					colWidth = colWidth + excess;
					columnWidths[expandableCol] = colWidth;
					last = Math.max(last, expandableCol);
				}
				colWidth = columnWidths[last];
				colWidth = colWidth + remainder;
				columnWidths[last] = colWidth;
			}

			int newExcessHorizontal = 0;

			// Go through all specs in each expandable column and get the
			// maximum specified
			// widthHint. Use this as the minimumWidth for the column.
			int[] resizableColumns = new int[m_expandableColumns.length];
			int nbResizableColumns = 0;

			for (int i = 0; i < m_expandableColumns.length; i++)
			{
				int expandableCol = m_expandableColumns[i];
				int colWidth = columnWidths[expandableCol];
				int minWidth = 0;
				for (int j = 0; j < m_grid.size(); j++)
				{
					GridData[] row = (GridData[]) m_grid.elementAt(j);
					GridData spec = row[expandableCol];
					if (spec.m_hSpan == 1)
					{
						minWidth = Math.max(minWidth, spec.m_widthHint);
					}
				}

				int newColWidth = Math.max(colWidth, minWidth);
				boolean resizable = colWidth > minWidth;
				if (!resizable)
				{
					// the column is not resizable, the last allocation is wrong
					// we need to keep the new excess space
					newExcessHorizontal += colWidth - newColWidth;
				} else
				{
					resizableColumns[nbResizableColumns++] = expandableCol;
				}

				columnWidths[expandableCol] = newColWidth;
			}

			// Allocate/deallocate horizontal space due to witdhHint.
			if (nbResizableColumns != 0 && m_expandableColumns.length != 0)
			{
				int excess;
				int remainder;
				int last;
				int colWidth;
				excess = newExcessHorizontal / nbResizableColumns;
				remainder = newExcessHorizontal % nbResizableColumns;
				last = 0;
				for (int i = 0; i < nbResizableColumns; i++)
				{
					int expandableCol = resizableColumns[i];
					colWidth = columnWidths[expandableCol];
					colWidth = colWidth + excess;
					columnWidths[expandableCol] = colWidth;
					last = Math.max(last, expandableCol);
				}
				colWidth = columnWidths[last];
				colWidth = colWidth + remainder;
				columnWidths[last] = colWidth;
			}
		}

		if (excessVertical >= 0)
		{
			// case when the vertical excess space is positive
			// Allocate/deallocate vertical space.
			if (m_expandableRows.length != 0)
			{
				int excess;
				int remainder;
				int last;
				int rowHeight;
				excess = excessVertical / m_expandableRows.length;
				remainder = excessVertical % m_expandableRows.length;
				last = 0;
				for (int i = 0; i < m_expandableRows.length; i++)
				{
					int expandableRow = m_expandableRows[i];
					rowHeight = rowHeights[expandableRow];
					rowHeight = rowHeight + excess;
					rowHeights[expandableRow] = rowHeight;
					last = Math.max(last, expandableRow);
				}
				rowHeight = rowHeights[last];
				rowHeight = rowHeight + remainder;
				rowHeights[last] = rowHeight;
			}

			// Go through all specs in each expandable row and get the maximum
			// specified
			// heightHint. Use this as the minimumHeight for the row.
			for (int i = 0; i < m_expandableRows.length; i++)
			{
				int expandableRow = m_expandableRows[i];
				int rowHeight = rowHeights[expandableRow];
				int minHeight = 0;
				GridData[] row = (GridData[]) m_grid.elementAt(expandableRow);
				for (int j = 0; j < m_numColumns; j++)
				{
					GridData spec = row[j];
					if (spec.m_verticalSpan == 1)
					{
						minHeight = Math.max(minHeight, spec.m_heightHint);
					}
				}
				rowHeights[expandableRow] = Math.max(rowHeight, minHeight);
			}
		} else
		{
			// case when the vertical excess space is negative
			// Allocate/deallocate vertical space.
			if (m_expandableRows.length != 0)
			{
				int excess;
				int remainder;
				int last;
				int rowHeight;
				excess = excessVertical / m_expandableRows.length;
				remainder = excessVertical % m_expandableRows.length;
				last = 0;
				for (int i = 0; i < m_expandableRows.length; i++)
				{
					int expandableRow = m_expandableRows[i];
					rowHeight = rowHeights[expandableRow];
					rowHeight = rowHeight + excess;
					rowHeights[expandableRow] = rowHeight;
					last = Math.max(last, expandableRow);
				}
				rowHeight = rowHeights[last];
				rowHeight = rowHeight + remainder;
				rowHeights[last] = rowHeight;
			}

			// Go through all specs in each expandable row and get the maximum
			// specified
			// heightHint. Use this as the minimumHeight for the row.
			int newExcessVertical = 0;
			int[] resizableRows = new int[m_expandableRows.length];
			int nbResizableRows = 0;
			for (int i = 0; i < m_expandableRows.length; i++)
			{
				int expandableRow = m_expandableRows[i];
				int rowHeight = rowHeights[expandableRow];
				int minHeight = 0;
				GridData[] row = (GridData[]) m_grid.elementAt(expandableRow);
				for (int j = 0; j < m_numColumns; j++)
				{
					GridData spec = row[j];
					if (spec.m_verticalSpan == 1)
					{
						minHeight = Math.max(minHeight, spec.m_heightHint);
					}
				}

				rowHeights[expandableRow] = Math.max(rowHeight, minHeight);

				int newRowHeight = Math.max(rowHeight, minHeight);
				boolean resizable = rowHeight > minHeight;
				if (!resizable)
				{
					// the column is not resizable, the last allocation is wrong
					// we need to keep the new excess space
					newExcessVertical += rowHeight - newRowHeight;
				} else
				{
					resizableRows[nbResizableRows++] = expandableRow;
				}

				rowHeights[expandableRow] = newRowHeight;
			}

			// Allocate/deallocate vertical space due to the height.
			if (nbResizableRows != 0 && m_expandableRows.length != 0)
			{
				int excess;
				int remainder;
				int last;
				int rowHeight;
				excess = newExcessVertical / nbResizableRows;
				remainder = newExcessVertical % nbResizableRows;
				last = 0;
				for (int i = 0; i < nbResizableRows; i++)
				{
					int expandableRow = resizableRows[i];
					rowHeight = rowHeights[expandableRow];
					rowHeight = rowHeight + excess;
					rowHeights[expandableRow] = rowHeight;
					last = Math.max(last, expandableRow);
				}
				rowHeight = rowHeights[last];
				rowHeight = rowHeight + remainder;
				rowHeights[last] = rowHeight;
			}
		}

		// Get the starting x and y.
		columnX = m_marginWidth + clientArea.x;
		rowY = m_marginHeight + clientArea.y;

		// Layout the control left to right, top to bottom.
		for (int r = 0; r < rowSize; r++)
		{
			int rowHeight = rowHeights[r];
			GridData[] row = (GridData[]) m_grid.elementAt(r);

			// 
			for (int c = 0; c < row.length; c++)
			{
				int spannedWidth = 0;
				int spannedHeight = 0;
				int hAlign = 0;
				int vAlign = 0;
				int widgetX = 0;
				int widgetY = 0;
				int widgetW = 0;
				int widgetH = 0;

				//
				GridData spec = (GridData) row[c];
				if (m_makeColumnsEqualWidth)
				{
					columnWidth = clientArea.width - 2 * m_marginWidth - ((m_numColumns - 1) * m_horizontalSpacing);
					columnWidth = columnWidth / m_numColumns;
					for (int i = 0; i < columnWidths.length; i++)
					{
						columnWidths[i] = columnWidth;
					}
				} else
				{
					columnWidth = columnWidths[c];
				}

				//
				spannedWidth = columnWidth;
				for (int k = 1; k < spec.m_hSpan; k++)
				{
					if ((c + k) <= m_numColumns)
					{
						if (!m_makeColumnsEqualWidth)
						{
							columnWidth = columnWidths[c + k];
						}
						spannedWidth = spannedWidth + columnWidth + m_horizontalSpacing;
					}
				}

				//
				spannedHeight = rowHeight;
				for (int k = 1; k < spec.m_verticalSpan; k++)
				{
					if ((r + k) <= m_grid.size())
					{
						spannedHeight = spannedHeight + rowHeights[r + k] + m_verticalSpacing;
					}
				}

				//
				if (spec.isItemData())
				{
					Component child = children[spec.m_childIndex];
					Dimension childExtent = computeSize(child, spec.m_widthHint, spec.m_heightHint, flushCache);
					hAlign = spec.m_horizontalAlignment;
					widgetX = columnX;

					// Calculate the x and width values for the control.
					if (hAlign == GridData.CENTER)
					{
						widgetX = widgetX + (spannedWidth / 2) - (childExtent.width / 2);
					} else if (hAlign == GridData.END)
					{
						widgetX = widgetX + spannedWidth - childExtent.width - spec.m_horizontalIndent;
					} else
					{
						widgetX = widgetX + spec.m_horizontalIndent;
					}
					if (hAlign == GridData.FILL)
					{
						widgetW = spannedWidth - spec.m_horizontalIndent;
						widgetX = columnX + spec.m_horizontalIndent;
					} else
					{
						widgetW = childExtent.width;
					}

					// Calculate the y and height values for the control.
					vAlign = spec.m_verticalAlignment;
					widgetY = rowY;
					if (vAlign == GridData.CENTER)
					{
						widgetY = widgetY + (spannedHeight / 2) - (childExtent.height / 2);
					} else if (vAlign == GridData.END)
					{
						widgetY = widgetY + spannedHeight - childExtent.height;
					}
					if (vAlign == GridData.FILL)
					{
						widgetH = spannedHeight;
						widgetY = rowY;
					} else
					{
						widgetH = childExtent.height;
					}

					// Place the control.
					setBounds(child, widgetX, widgetY, widgetW, widgetH);
				}

				// Update the starting x value.
				columnX = columnX + columnWidths[c] + m_horizontalSpacing;
			}

			// Update the starting y value and since we're starting a new row,
			// reset the starting x value.
			rowY = rowY + rowHeights[r] + m_verticalSpacing;
			columnX = m_marginWidth + clientArea.x;
		}
	}

	public GridData getLayoutData(Component control)
	{
		return (GridData) m_layoutDataMap.get(control);
	}

	public void setLayoutData(Component control, GridData data)
	{
		m_layoutDataMap.put(control, data);
	}

	protected void removeLayoutData(Component control)
	{
		m_layoutDataMap.remove(control);
	}

	// Interface LayoutManager2

	/**
	 * @see java.awt.LayoutManager2#getLayoutAlignmentX(Container)
	 */
	public float getLayoutAlignmentX(Container target)
	{
		return 0;
	}

	/**
	 * @see java.awt.LayoutManager2#getLayoutAlignmentY(Container)
	 */
	public float getLayoutAlignmentY(Container target)
	{
		return 0;
	}

	/**
	 * @see java.awt.LayoutManager#preferredLayoutSize(Container)
	 */
	public Dimension preferredLayoutSize(Container parent)
	{
		Dimension size = this.computeSize(parent, -1, -1, true);
		Insets insets = parent.getInsets();
		size.width += insets.left + insets.right;
		size.height += insets.top + insets.bottom;
		return size;
	}

	/**
	 * @see java.awt.LayoutManager#minimumLayoutSize(Container)
	 */
	public Dimension minimumLayoutSize(Container parent)
	{
		return preferredLayoutSize(parent);
	}

	/**
	 * @see java.awt.LayoutManager2#maximumLayoutSize(Container)
	 */
	public Dimension maximumLayoutSize(Container target)
	{
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	/**
	 * @see java.awt.LayoutManager2#addLayoutComponent(Component, Object)
	 */
	public void addLayoutComponent(Component comp, Object constraints)
	{
		if (constraints == null)
		{
			constraints = new GridData();
		}

		if (!(constraints instanceof GridData))
		{
			throw new IllegalArgumentException("Constraints must be a GridData class");
		}

		setLayoutData(comp, (GridData) constraints);
	}

	/**
	 * @see java.awt.LayoutManager#addLayoutComponent(String, Component)
	 */
	public void addLayoutComponent(String name, Component comp)
	{
		throw new IllegalArgumentException();
	}

	/**
	 * @see java.awt.LayoutManager2#invalidateLayout(Container)
	 */
	public void invalidateLayout(Container target)
	{
		// rien
	}

	/**
	 * @see java.awt.LayoutManager#layoutContainer(Container)
	 */
	public void layoutContainer(Container parent)
	{
		layout(parent, true);
	}

	/**
	 * @see java.awt.LayoutManager#removeLayoutComponent(Component)
	 */
	public void removeLayoutComponent(Component comp)
	{
		removeLayoutData(comp);
	}

	/**
	 * @return
	 */
	public int[] getPixelColumnWidths()
	{
		return m_pixelColumnWidths;
	}

	/**
	 * @return
	 */
	public int[] getPixelRowHeights()
	{
		return m_pixelRowHeights;
	}
}
