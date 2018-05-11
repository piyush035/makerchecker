package com.rennover.client.framework.widget.panel;


import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class SGLayout implements LayoutManager, java.io.Serializable
{
	public static final int LEFT = 0;

	public static final int CENTER = 1;

	public static final int RIGHT = 2;

	public static final int FILL = 4;

	public static final int TOP = 8;

	public static final int BOTTOM = 16;

	protected int m_rows;

	protected int m_cols;

	protected int m_vgap;

	protected int m_hgap;

	protected int m_topBorder = 0;

	protected int m_leftBorder = 0;

	protected int m_bottomBorder = 0;

	protected int m_rightBorder = 0;

	protected int m_minW = 10; // to handle JTextField sensibly

	protected int m_minH = 10; // to handle JTextField sensibly

	protected double[] m_rowScale;

	protected double[] m_columnScale;

	protected int m_hAlignment = FILL;

	protected int m_vAlignment = FILL;

	protected int[][] m_hAlignments;

	protected int[][] m_vAlignments;

	protected int[] m_rowSizes;

	protected int[] m_columnSizes;

	/**
	 * Creates a default (2 x 2) layout with the specified number of rows and
	 * columns.
	 * <p>
	 * horizontal and vertical gaps are set to 0 and X- and Y-alignments are set
	 * to FILL.
	 */
	public SGLayout()
	{
		this(2, 2, FILL, FILL, 0, 0);
	}

	/**
	 * Creates a layout with the specified number of rows and columns.
	 * <p>
	 * horizontal and vertical gaps are set to 0 and X- and Y-alignments are set
	 * to FILL.
	 * 
	 * @param rows
	 *            the rows.
	 * @param cols
	 *            the columns.
	 */
	public SGLayout(int rows, int cols)
	{
		this(rows, cols, FILL, FILL, 0, 0);
	}

	/**
	 * Creates a layout with the specified number of rows and columns and
	 * specified gaps.
	 * <p>
	 * horizontal and vertical gaps are set to 0 and X- and Y-alignments are set
	 * to FILL.
	 * 
	 * @param rows
	 *            the rows.
	 * @param cols
	 *            the columns.
	 * @param hgap
	 *            the horizontal gap, in pixels.
	 * @param vgap
	 *            the vertical gap, in pixels.
	 */
	public SGLayout(int rows, int cols, int hgap, int vgap)
	{
		this(rows, cols, FILL, FILL, hgap, vgap);
	}

	/**
	 * Creates a layout with the specified number of rows and columns and
	 * specified gaps and alignments.
	 * <p>
	 * horizontal and vertical gaps are set to 0 and X- and Y-alignments are set
	 * to FILL.
	 * 
	 * @param rows
	 *            the rows.
	 * @param cols
	 *            the columns.
	 * @param hAlignment
	 *            the X-alignment.
	 * @param vAlignment
	 *            the Y-alignment.
	 * @param hgap
	 *            the horizontal gap, in pixels.
	 * @param vgap
	 *            the vertical gap, in pixels.
	 */
	public SGLayout(int rows, int cols, int hAlignment, int vAlignment, int hgap, int vgap)
	{
		this.m_hgap = hgap;
		this.m_vgap = vgap;
		this.m_hAlignment = hAlignment;
		this.m_vAlignment = vAlignment;

		setDimensions(rows, cols);
	}

	private void setScaleValues()
	{
		m_rowScale = new double[m_rows];
		m_columnScale = new double[m_cols];
		for (int i = 0; i < m_rows; i++)
		{
			m_rowScale[i] = 1.0;
		}
		for (int j = 0; j < m_cols; j++)
		{
			m_columnScale[j] = 1.0;
		}
	}

	private void setAlignments()
	{
		m_hAlignments = new int[m_rows][m_cols];
		m_vAlignments = new int[m_rows][m_cols];
		for (int i = 0; i < m_rows; i++)
		{
			for (int j = 0; j < m_cols; j++)
			{
				m_hAlignments[i][j] = m_hAlignment;
				m_vAlignments[i][j] = m_vAlignment;
			}
		}
	}

	/**
	 * Set up scale values and alignments for the whole layout.
	 * <p>
	 * 
	 * @param rows
	 *            the rows.
	 * @param cols
	 *            the columns.
	 */
	private void setDimensions(int rows, int cols)
	{
		this.m_rows = rows;
		this.m_cols = cols;

		setScaleValues();
		setAlignments();
	}

	/**
	 * Set up scale values and alignments for the whole layout.
	 * <p>
	 * 
	 * @param topBorder
	 *            the top border (in pixels).
	 * @param leftBorder
	 *            the left border (in pixels).
	 * @param bottomBorder
	 *            the bottom border (in pixels).
	 * @param rightBorder
	 *            the right border (in pixels).
	 */
	public void setMargins(int topBorder, int leftBorder, int bottomBorder, int rightBorder)
	{
		this.m_topBorder = topBorder;
		this.m_leftBorder = leftBorder;
		this.m_bottomBorder = bottomBorder;
		this.m_rightBorder = rightBorder;
	}

	/**
	 * Set up scale value for a specific row.
	 * <p>
	 * 
	 * @param index
	 *            the row number.
	 * @param prop
	 *            the scale value for the row.
	 */
	public void setRowScale(int index, double prop)
	{
		if (index >= 0 && index < m_rows)
		{
			m_rowScale[index] = prop;
		}
	}

	/**
	 * Set up scale value for a specific column.
	 * <p>
	 * 
	 * @param index
	 *            the column number.
	 * @param prop
	 *            the scale value for the column.
	 */
	public void setColumnScale(int index, double prop)
	{
		if (index >= 0 && index < m_cols)
		{
			m_columnScale[index] = prop;
		}
	}

	// removed the commented method setAlignment

	/**
	 * Set up alignment for a specific cell.
	 * <p>
	 * 
	 * @param row
	 *            the row number.
	 * @param column
	 *            the column number.
	 * @param h
	 *            the X-alignment.
	 * @param v
	 *            the Y-alignment.
	 */
	public void setCellAlignment(int row, int column, int h, int v)
	{
		if (row < m_rows && column < m_cols)
		{
			m_hAlignments[row][column] = h;
			m_vAlignments[row][column] = v;
		}
	}

	/**
	 * Set up alignment for a specific row.
	 * <p>
	 * 
	 * @param row
	 *            the row number.
	 * @param h
	 *            the X-alignment.
	 * @param v
	 *            the Y-alignment.
	 */
	public void setRowAlignment(int row, int h, int v)
	{
		if (row < m_rows)
		{
			for (int column = 0; column < m_cols; column++)
			{
				m_hAlignments[row][column] = h;
				m_vAlignments[row][column] = v;
			}
		}
	}

	/**
	 * Set up alignment for a specific column.
	 * <p>
	 * 
	 * @param column
	 *            the column number.
	 * @param h
	 *            the X-alignment.
	 * @param v
	 *            the Y-alignment.
	 */
	public void setColumnAlignment(int column, int h, int v)
	{
		if (column < m_cols)
		{
			for (int row = 0; row < m_rows; row++)
			{
				m_hAlignments[row][column] = h;
				m_vAlignments[row][column] = v;
			}
		}
	}

	public void addLayoutComponent(String name, Component comp)
	{
	}

	public void removeLayoutComponent(Component comp)
	{
	}

	/**
	 * Determines the preferred size of the container argument using this grid
	 * layout.
	 * <p>
	 * The preferred width is the width of the largest row of children, which is
	 * the largest sum of preferred widths.
	 * <p>
	 * The preferred height is the sum of the the largest heights of the rows,
	 * which is the largest preferred height in each row.
	 * 
	 * @param target
	 *            the container in which to do the layout.
	 * @return the preferred dimensions to lay out the subcomponents of the
	 *         specified container.
	 */
	public Dimension preferredLayoutSize(Container parent)
	{
		synchronized (parent.getTreeLock())
		{
			int ncomponents = parent.getComponentCount();
			int nrows = m_rows;
			int ncols = m_cols;

			if (nrows > 0)
			{
				ncols = (ncomponents + nrows - 1) / nrows;
			} else
			{
				nrows = (ncomponents + ncols - 1) / ncols;
			}
			int totalWidth = 0;
			int totalHeight = 0;

			for (int i = 0; i < nrows; i++)
			{
				int prefWidth = 0;
				int prefHeight = 0;
				// get max preferred height for a row
				for (int j = 0; j < ncols; j++)
				{
					int index = i * ncols + j;
					if (index >= ncomponents)
					{
						continue;
					}

					Component comp = parent.getComponent(index);
					Dimension d = comp.getPreferredSize();
					if (d.width < m_minW)
					{
						prefWidth += m_minW; // add minimum width
					} else
					{
						prefWidth += d.width; // increment total preferred
												// width
					}
					if (d.height > prefHeight)
					{
						prefHeight = d.height;
					}
				}
				if (prefWidth > totalWidth)
				{
					totalWidth = prefWidth;
				}
				totalHeight += prefHeight;
			}
			return new Dimension(totalWidth + m_leftBorder + m_rightBorder + (ncols - 1) * m_hgap, totalHeight + m_topBorder
			        + m_bottomBorder + (nrows - 1) * m_vgap);
		}
	}

	/**
	 * Determines the minimum size of the container argument using this grid
	 * layout.
	 * <p>
	 * The preferred width is the width of the largest row of children, which is
	 * the largest sum of minimum widths.
	 * <p>
	 * The preferred height is the sum of the the largest heights of the rows,
	 * which is the largest minimum height in each row.
	 * 
	 * @param target
	 *            the container in which to do the layout.
	 * @return the preferred dimensions to lay out the subcomponents of the
	 *         specified container.
	 */
	public Dimension minimumLayoutSize(Container parent)
	{
		synchronized (parent.getTreeLock())
		{
			int ncomponents = parent.getComponentCount();
			int nrows = m_rows;
			int ncols = m_cols;

			if (nrows > 0)
			{
				ncols = (ncomponents + nrows - 1) / nrows;
			} else
			{
				nrows = (ncomponents + ncols - 1) / ncols;
			}
			int totalWidth = 0;
			int totalHeight = 0;

			for (int i = 0; i < nrows; i++)
			{
				int minWidth = 0;
				int minHeight = 0;
				for (int j = 0; j < ncols; j++)
				{
					int index = i * ncols + j;
					if (index >= ncomponents)
					{
						continue;
					}

					Component comp = parent.getComponent(index);
					Dimension d = comp.getMinimumSize();
					int width = d.width;
					if (width < m_minW)
					{
						width = m_minW;
					}
					minWidth += width;
					if (minHeight > d.height)
					{
						minHeight = d.height;
					}
				}
				if (totalWidth > minWidth)
				{
					totalWidth = minWidth;
				}
				if (minHeight < m_minH)
				{
					minHeight = m_minH; // enough room for text?
				}
				totalHeight += minHeight;
			}

			return new Dimension(totalWidth + m_leftBorder + m_rightBorder + (ncols - 1) * m_hgap, totalHeight + m_topBorder
			        + m_bottomBorder + (nrows - 1) * m_vgap);
		}
	}

	/**
	 * Lay out the specified container using this layout within the calculated
	 * grids.
	 * <p>
	 * 
	 * @param parent
	 *            the container to be laid out.
	 */
	public void layoutContainer(Container parent)
	{
		int nComps = parent.getComponentCount();

		int x;
		int y = m_topBorder;

		allocateMaxSizes(parent);

		for (int i = 0; i < m_rows; i++)
		{
			x = m_leftBorder;
			for (int j = 0; j < m_cols; j++)
			{
				int componentIndex = i * m_cols + j;
				if (componentIndex > nComps - 1)
				{
					continue;
				}

				Component c = parent.getComponent(componentIndex);
				if (c.isVisible())
				{
					setComponentBounds(c, i, j, x, y);
				}
				x += m_columnSizes[j] + m_hgap;
			}
			y += m_rowSizes[i] + m_vgap;
		}
	}

	/**
	 * Set the bounds for a component of specified coordinates. given the cell
	 * coordinates and the origin of the cell.
	 * <p>
	 * 
	 * @param row
	 *            the grid row
	 * @param column
	 *            the grid column
	 * @param left
	 *            the x=coord of the grid origin.
	 * @param top
	 *            the y-coord of the grid origin.
	 */
	void setComponentBounds(Component c, int row, int column, int left, int top)
	{
		Dimension d = c.getPreferredSize();
		int finalWidth = m_columnSizes[column]; // max
		int finalHeight = m_rowSizes[row]; // max

		int xSpace = finalWidth - d.width;
		if (xSpace > 0)
		{
			int alignment = m_hAlignments[row][column];
			if (alignment == RIGHT)
			{
				left += xSpace;
			} else if (alignment == CENTER)
			{
				left += xSpace / 2;
			}

			if (alignment != FILL)
			{
				finalWidth = d.width; // reduce width to preferred val
			}
		}

		int ySpace = finalHeight - d.height;
		if (ySpace > 0)
		{
			int vAlignment = m_vAlignments[row][column];
			if (vAlignment == BOTTOM)
			{
				top += ySpace;
			} else if (vAlignment == CENTER)
			{
				top += ySpace / 2;
			}

			if (vAlignment != FILL)
			{
				finalHeight = d.height; // reduce height to pref val
			}
		}
		c.setBounds(left, top, finalWidth, finalHeight);
	}

	/**
	 * Update out the maximum sizes for each of the grid cells using the
	 * specified scale values for rows and columns.
	 * 
	 * @param parent
	 *            the container to be laid out.
	 */
	protected void allocateMaxSizes(Container parent)
	{
		m_rowSizes = new int[m_rows];
		m_columnSizes = new int[m_cols];
		Dimension thisSize = parent.getSize();
		int width = thisSize.width - m_leftBorder - m_rightBorder - (m_cols - 1) * m_hgap;
		int height = thisSize.height - m_topBorder - m_bottomBorder - (m_rows - 1) * m_vgap;

		double totalRowProps = 0.0;
		for (int i = 0; i < m_rows; i++)
		{
			totalRowProps += m_rowScale[i];
		}

		double totalColumnProps = 0.0;
		for (int j = 0; j < m_cols; j++)
		{
			totalColumnProps += m_columnScale[j];
		}

		for (int p = 0; p < m_rows; p++)
		{
			m_rowSizes[p] = (int) (m_rowScale[p] * height / totalRowProps);
		}
		for (int q = 0; q < m_cols; q++)
		{
			m_columnSizes[q] = (int) (m_columnScale[q] * width / totalColumnProps);
		}
	}
}