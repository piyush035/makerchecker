package com.rennover.client.framework.widget.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.awt.Rectangle;
import java.util.Hashtable;
import java.util.Map;

/**
 * Instances of this class control the position and size of the children of a
 * composite control by using <code>FormAttachments</code> to optionally
 * configure the left, top, right and bottom edge of each child.
 * <p>
 * The following example code creates a <code>FormLayout</code> and then sets
 * it into a <code>Shell</code>:
 * 
 * <pre>
 * Display display = new Display();
 * Shell shell = new Shell(display);
 * FormLayout layout = new FormLayout();
 * layout.marginWidth = 3;
 * layout.marginHeight = 3;
 * shell.setLayout(layout);
 * </pre>
 * 
 * </p>
 * <p>
 * To use a <code>FormLayout</code>, create a <code>FormData</code> with
 * <code>FormAttachment</code> for each child of <code>Composite</code>.
 * The following example code attaches <code>button1</code> to the top and
 * left edge of the composite and <code>button2</code> to the right edge of
 * <code>button1</code> and the top and right edges of the composite:
 * 
 * <pre>
 * FormData data1 = new FormData();
 * data1.left = new FormAttachment(0, 0);
 * data1.top = new FormAttachment(0, 0);
 * button1.setLayoutData(data1);
 * FormData data2 = new FormData();
 * data2.left = new FormAttachment(button1);
 * data2.top = new FormAttachment(0, 0);
 * data2.right = new FormAttachment(100, 0);
 * button2.setLayoutData(data2);
 * </pre>
 * 
 * </p>
 * <p>
 * Each side of a child control can be attached to a position in the parent
 * composite, or to other controls within the <code>Composite</code> by
 * creating instances of <code>FormAttachment</code> and setting them into the
 * top, bottom, left, and right fields of the child's <code>FormData</code>.
 * </p>
 * <p>
 * If a side is not given an attachment, it is defined as not being attached to
 * anything, causing the child to remain at it's preferred size. If a child is
 * given no attachments on either the left or the right or top or bottom, it is
 * automatically attached to the left and top of the composite respectively. The
 * following code positions <code>button1</code> and <code>button2</code>
 * but relies on default attachments:
 * 
 * <pre>
 * FormData data2 = new FormData();
 * data2.left = new FormAttachment(button1);
 * data2.right = new FormAttachment(100, 0);
 * button2.setLayoutData(data2);
 * </pre>
 * 
 * </p>
 * <p>
 * IMPORTANT: Do not define circular attachments. For example, do not attach the
 * right edge of <code>button1</code> to the left edge of <code>button2</code>
 * and then attach the left edge of <code>button2</code> to the right edge of
 * <code>button1</code>. This will over constrain the layout, causing
 * undefined behavior. The algorithm will terminate, but the results are
 * undefined.
 * </p>
 * 
 * @see FormData
 * @see FormAttachment
 * 
 * @since 2.0
 * 
 */
public final class SWTFormLayout extends SWTLayout implements LayoutManager2
{
	/**
	 * marginWidth specifies the number of pixels of horizontal margin that will
	 * be placed along the left and right edges of the layout.
	 * 
	 * The default value is 0.
	 */
	public int m_marginWidth = 0;

	/**
	 * marginHeight specifies the number of pixels of vertical margin that will
	 * be placed along the top and bottom edges of the layout.
	 * 
	 * The default value is 0.
	 */
	public int m_marginHeight = 0;

	private Map m_layoutDataMap = new Hashtable();

	/**
	 * Constructs a new instance of this class.
	 */
	public SWTFormLayout()
	{
	}

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
		Dimension size = computeSize(parent, -1, -1, true);
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
		if (!(constraints instanceof FormData))
		{
			throw new IllegalArgumentException("constraints must be a FormData class");
		}
		setLayoutData(comp, (FormData) constraints);
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
		setLayoutData(comp, null);
	}

	/**
	 * Computes the preferred height of the form with respect to the preferred
	 * height of the control.
	 * 
	 * Given that the equations for top (T) and bottom (B) of the control in
	 * terms of the height of the form (X) are: T = AX + B B = CX + D
	 * 
	 * The equation for the height of the control (H) is bottom (B) minus top
	 * (T) or (H = B - T) or:
	 * 
	 * H = (CX + D) - (AX + B)
	 * 
	 * Solving for (X), the height of the form, we get:
	 * 
	 * X = (H + B - D) / (C - A)
	 * 
	 * When (A = C), (C - A = 0) and the equation has no solution for X. This is
	 * a special case meaning that the control does not constrain the height of
	 * the form. In this case, we need to arbitrarily define the height of the
	 * form (X):
	 * 
	 * Case 1: A = C, A = 0, C = 0
	 * 
	 * Let X = D, the distance from the top of the form to the bottom edge of
	 * the control. In this case, the control was attatched to the top of the
	 * form and the form needs to be large enough to show the bottom edge of the
	 * control.
	 * 
	 * Case 2: A = C, A = 1, C = 1
	 * 
	 * Let X = -B, the distance from the bottom of the form to the top edge of
	 * the control. In this case, the control was attached to the bottom of the
	 * form and the only way that the control would be visible is if the offset
	 * is negative. If the offset is positive, there is no possible height for
	 * the form that will show the control as it will always be below the bottom
	 * edge of the form.
	 * 
	 * Case 3: A = C, A != 0, C != 0 and A != 1, C != 0
	 * 
	 * Let X = D / (1 - C), the distance from the top of the form to the bottom
	 * edge of the control. In this case, since C is not 0 or 1, it must be a
	 * fraction, U / V. The offset D is the distance from CX to the bottom edge
	 * of the control. This represents a fraction of the form (1 - C)X. Since
	 * the height of a fraction of the form is known, the height of the entire
	 * form can be found by setting (1 - C)X = D. We solve this equation for X
	 * in terms of U and V, giving us X = (U * D) / (U - V). Similarily, if the
	 * offset D is negative, the control is positioned above CX. The offset -B
	 * is the distance from the top edge of the control to CX. We can find the
	 * height of the entire form by setting CX = -B. Solving in terms of U and V
	 * gives us X = (-B * V) / U.
	 */
	int computeHeight(FormData data)
	{
		FormAttachment top = data.getTopAttachment();
		FormAttachment bottom = data.getBottomAttachment();
		FormAttachment height = bottom.minus(top);
		if (height.m_numerator == 0)
		{
			if (bottom.m_numerator == 0)
			{
				return bottom.m_offset;
			}
			if (bottom.m_numerator == bottom.m_denominator)
			{
				return -top.m_offset;
			}
			if (bottom.m_offset <= 0)
			{
				return -top.m_offset * top.m_denominator / bottom.m_numerator;
			}
			int divider = bottom.m_denominator - bottom.m_numerator;
			return bottom.m_denominator * bottom.m_offset / divider;
		}
		return height.solveY(data.m_cacheHeight);
	}

	protected Dimension computeSize(Container composite, int wHint, int hHint, boolean flushCache)
	{
		Dimension size = layout(composite, false, 0, 0, 0, 0, flushCache);
		size.width += m_marginWidth * 2;
		size.height += m_marginHeight * 2;
		return size;
	}

	/**
	 * Computes the preferred height of the form with respect to the preferred
	 * height of the control.
	 */
	int computeWidth(FormData data)
	{
		FormAttachment left = data.getLeftAttachment();
		FormAttachment right = data.getRightAttachment();
		FormAttachment width = right.minus(left);
		if (width.m_numerator == 0)
		{
			if (right.m_numerator == 0)
			{
				return right.m_offset;
			}
			if (right.m_numerator == right.m_denominator)
			{
				return -left.m_offset;
			}
			if (right.m_offset <= 0)
			{
				return -left.m_offset * left.m_denominator / left.m_numerator;
			}
			int divider = right.m_denominator - right.m_numerator;
			return right.m_denominator * right.m_offset / divider;
		}
		return width.solveY(data.m_cacheWidth);
	}

	Dimension getSize(Component control, boolean flushCache)
	{
		int wHint = SWTConstants.DEFAULT;
		int hHint = SWTConstants.DEFAULT;

		FormData data = getLayoutData(control);
		if (data != null)
		{
			wHint = data.m_width;
			hHint = data.m_height;
		}

		return computeSize(control, wHint, hHint, flushCache);
	}

	protected void layout(Container composite, boolean flushCache)
	{
		Rectangle rect = getClientArea(composite);
		int x = rect.x + m_marginWidth;
		int y = rect.y + m_marginHeight;
		int width = Math.max(0, rect.width - 2 * m_marginWidth);
		int height = Math.max(0, rect.height - 2 * m_marginHeight);
		layout(composite, true, x, y, width, height, flushCache);
	}

	Dimension layout(Container composite, boolean move, int x, int y, int width, int height, boolean flushCache)
	{
		Component[] children = composite.getComponents();
		for (int i = 0; i < children.length; i++)
		{
			Component child = children[i];
			Dimension pt = getSize(child, false);
			FormData data = getLayoutData(child);
			if (data == null)
			{
				data = new FormData();
				setLayoutData(child, data);
			}
			data.m_cacheWidth = pt.width;
			data.m_cacheHeight = pt.height;
		}
		for (int i = 0; i < children.length; i++)
		{
			Component child = children[i];
			FormData data = getLayoutData(child);
			if (move)
			{
				int x1 = data.getLeftAttachment().solveX(width);
				int y1 = data.getTopAttachment().solveX(height);
				int x2 = data.getRightAttachment().solveX(width);
				int y2 = data.getBottomAttachment().solveX(height);
				child.setBounds(x + x1, y + y1, x2 - x1, y2 - y1);
			} else
			{
				width = Math.max(computeWidth(data), width);
				height = Math.max(computeHeight(data), height);
			}
		}
		return move ? null : new Dimension(width, height);
	}

	protected FormData getLayoutData(Component control)
	{
		return (FormData) m_layoutDataMap.get(control);
	}

	protected void setLayoutData(Component control, FormData data)
	{
		if (data != null)
		{
			data.setLayout(this);
			m_layoutDataMap.put(control, data);
		}
	}
}