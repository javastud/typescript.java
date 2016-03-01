package ts.eclipse.ide.internal.ui.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

import ts.eclipse.ide.core.TypeScriptCorePlugin;
import ts.eclipse.ide.ui.preferences.PropertyAndPreferencePage;

/**
 * Node.js preferences page
 *
 */
public class NodejsPreferencePage extends PropertyAndPreferencePage {

	public static final String PREF_ID = "ts.eclipse.ide.ui.preference.NodejsPreferencePage"; //$NON-NLS-1$
	public static final String PROP_ID = "ts.eclipse.ide.ui.property.NodejsPreferencePage"; //$NON-NLS-1$

	private NodejsConfigurationBlock configurationBlock;

	public NodejsPreferencePage() {
	}
	
	@Override
	public void createControl(Composite parent) {
		IWorkbenchPreferenceContainer container = (IWorkbenchPreferenceContainer) getContainer();
		configurationBlock = new NodejsConfigurationBlock(getNewStatusChangedListener(), getProject(), container);
		super.createControl(parent);
	}

	@Override
	protected Control createPreferenceContent(Composite composite) {
		return configurationBlock.createContents(composite);
	}

	@Override
	protected boolean hasProjectSpecificOptions(IProject project) {
		return configurationBlock.hasProjectSpecificOptions(project);
	}

	@Override
	protected String getPreferencePageID() {
		return PREF_ID;
	}

	@Override
	protected String getPropertyPageID() {
		return PROP_ID;
	}

	@Override
	protected void enablePreferenceContent(boolean enable) {
		if (configurationBlock != null) {
			configurationBlock.enablePreferenceContent(enable);
		}
	}

	@Override
	protected void enableProjectSpecificSettings(boolean useProjectSpecificSettings) {
		super.enableProjectSpecificSettings(useProjectSpecificSettings);
		if (configurationBlock != null) {
			configurationBlock.useProjectSpecificSettings(useProjectSpecificSettings);
		}
	}

	@Override
	public void dispose() {
		if (configurationBlock != null) {
			configurationBlock.dispose();
		}
		super.dispose();
	}

	protected void performDefaults() {
		super.performDefaults();
		if (configurationBlock != null) {
			configurationBlock.performDefaults();
		}
	}

	@Override
	public boolean performOk() {
		if (configurationBlock != null && !configurationBlock.performOk()) {
			return false;
		}
		return super.performOk();
	}

	@Override
	public void performApply() {
		if (configurationBlock != null) {
			configurationBlock.performApply();
		}
	}

}