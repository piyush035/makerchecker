package com.rennover.client.framework.widget.layout;

/**
 * Instances of this class are used to define the attachments of a control in a
 * <code>FormLayout</code>.
 * <p>
 * To set a <code>FormData</code> object into a control, you use the
 * <code>setLayoutData ()</code> method. To define attachments for the
 * <code>FormData</code>, set the fields directly, like this:
 * 
 * <pre>
 * FormData data = new FormData();
 * data.left = new FormAttachment(0, 5);
 * data.right = new FormAttachment(100, -5);
 * button.setLayoutData(formData);
 * </pre>
 * 
 * </p>
 * <p>
 * <code>FormData</code> contains the <code>FormAttachments</code> for each
 * edge of the control that the <code>FormLayout</code> uses to determine the
 * size and position of the control. <code>FormData</code> objects also allow
 * you to set the width and height of controls within a <code>FormLayout</code>.
 * </p>
 * 
 * @see FormLayout
 * @see FormAttachment
 * 
 * @since 2.0
 */
public final class FormData
{
	/**
	 * height specifies the desired height in pixels
	 */
	public int m_height;

	/**
	 * width specifies the desired width in pixels
	 */
	public int m_width;

	/**
	 * left specifies the attachment of the left side of the control.
	 */
	public FormAttachment m_left;

	/**
	 * right specifies the attachment of the right side of the control.
	 */
	public FormAttachment m_right;

	/**
	 * top specifies the attachment of the top of the control.
	 */
	public FormAttachment m_top;

	/**
	 * bottom specifies the attachment of the bottom of the control.
	 */
	public FormAttachment m_bottom;

	int m_cacheHeight;

	int m_cacheWidth;

	boolean m_isVisited;

	private SWTFormLayout m_layout = null;

	public FormData()
	{
		this(SWTConstants.DEFAULT, SWTConstants.DEFAULT);
	}

	public FormData(int width, int height)
	{
		this.m_width = width;
		this.m_height = height;
	}

	FormAttachment getBottomAttachment()
	{
		if (m_isVisited)
		{
			return new FormAttachment(0, m_cacheHeight);
		}
		if (m_bottom == null)
		{
			if (m_top == null)
			{
				return new FormAttachment(0, m_cacheHeight);
			}
			return getTopAttachment().plus(m_cacheHeight);
		}
		if (m_bottom.m_control == null)
		{
			return m_bottom;
		}
		m_isVisited = true;
		FormData bottomData = getLayout().getLayoutData(m_bottom.m_control);
		FormAttachment topAttachment = bottomData.getTopAttachment();
		FormAttachment bottomAttachment = bottomData.getBottomAttachment();
		m_isVisited = false;
		if (m_bottom.m_alignment == SWTConstants.BOTTOM)
		{
			return bottomAttachment.plus(m_bottom.m_offset);
		}
		if (m_bottom.m_alignment == SWTConstants.CENTER)
		{
			FormAttachment bottomHeight = bottomAttachment.minus(topAttachment);
			return bottomAttachment.minus(bottomHeight.minus(m_cacheHeight).divide(2));
		}
		return topAttachment.plus(m_bottom.m_offset);
	}

	FormAttachment getLeftAttachment()
	{
		if (m_isVisited)
		{
			return new FormAttachment(0, 0);
		}
		if (m_left == null)
		{
			if (m_right == null)
			{
				return new FormAttachment(0, 0);
			}
			return getRightAttachment().minus(m_cacheWidth);
		}
		if (m_left.m_control == null)
		{
			return m_left;
		}
		m_isVisited = true;
		FormData leftData = getLayout().getLayoutData(m_left.m_control);
		FormAttachment rightAttachment = leftData.getRightAttachment();
		FormAttachment leftAttachment = leftData.getLeftAttachment();
		m_isVisited = false;
		if (m_left.m_alignment == SWTConstants.LEFT)
		{
			return leftAttachment.plus(m_left.m_offset);
		}
		if (m_left.m_alignment == SWTConstants.CENTER)
		{
			FormAttachment leftWidth = rightAttachment.minus(leftAttachment);
			return leftAttachment.plus(leftWidth.minus(m_cacheWidth).divide(2));
		}
		return rightAttachment.plus(m_left.m_offset);
	}

	FormAttachment getRightAttachment()
	{
		if (m_isVisited)
		{
			return new FormAttachment(0, m_cacheWidth);
		}
		if (m_right == null)
		{
			if (m_left == null)
			{
				return new FormAttachment(0, m_cacheWidth);
			}
			return getLeftAttachment().plus(m_cacheWidth);
		}
		if (m_right.m_control == null)
		{
			return m_right;
		}
		m_isVisited = true;
		FormData rightData = getLayout().getLayoutData(m_right.m_control);
		FormAttachment leftAttachment = rightData.getLeftAttachment();
		FormAttachment rightAttachment = rightData.getRightAttachment();
		m_isVisited = false;
		if (m_right.m_alignment == SWTConstants.RIGHT)
		{
			return rightAttachment.plus(m_right.m_offset);
		}
		if (m_right.m_alignment == SWTConstants.CENTER)
		{
			FormAttachment rightWidth = rightAttachment.minus(leftAttachment);
			return rightAttachment.minus(rightWidth.minus(m_cacheWidth).divide(2));
		}
		return leftAttachment.plus(m_right.m_offset);
	}

	FormAttachment getTopAttachment()
	{
		if (m_isVisited)
		{
			return new FormAttachment(0, 0);
		}
		if (m_top == null)
		{
			if (m_bottom == null)
			{
				return new FormAttachment(0, 0);
			}
			return getBottomAttachment().minus(m_cacheHeight);
		}
		if (m_top.m_control == null)
		{
			return m_top;
		}
		m_isVisited = true;
		FormData topData = getLayout().getLayoutData(m_top.m_control);
		FormAttachment topAttachment = topData.getTopAttachment();
		FormAttachment bottomAttachment = topData.getBottomAttachment();
		m_isVisited = false;
		if (m_top.m_alignment == SWTConstants.TOP)
		{
			return topAttachment.plus(m_top.m_offset);
		}
		if (m_top.m_alignment == SWTConstants.CENTER)
		{
			FormAttachment topHeight = bottomAttachment.minus(topAttachment);
			return topAttachment.plus(topHeight.minus(m_cacheHeight).divide(2));
		}
		return bottomAttachment.plus(m_top.m_offset);
	}

	/**
	 * @param layout
	 */
	public void setLayout(SWTFormLayout layout)
	{
		m_layout = layout;
	}

	public SWTFormLayout getLayout()
	{
		return m_layout;
	}
}