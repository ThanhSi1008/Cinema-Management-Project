package gui.menu;

import java.util.HashMap;

import com.formdev.flatlaf.util.Animator;

public class ManagerMenuAnimation {

	private static HashMap<ManagerMenuItem, Animator> hash = new HashMap<>();
	private static HashMap<EmployeeMenuItem, Animator> hashEmp = new HashMap<>();

	public static void animate(ManagerMenuItem menu, boolean show) {
		if (hash.containsKey(menu) && hash.get(menu).isRunning()) {
			hash.get(menu).stop();
		}
		menu.setMenuShow(show);
		Animator animator = new Animator(400, new Animator.TimingTarget() {
			@Override
			public void timingEvent(float f) {
				if (show) {
					menu.setAnimate(f);
				} else {
					menu.setAnimate(1f - f);
				}
				menu.revalidate();
			}

			@Override
			public void end() {
				hash.remove(menu);
			}
		});
		animator.setResolution(1);
		animator.setInterpolator((float f) -> (float) (1 - Math.pow(1 - f, 3)));
		animator.start();
		hash.put(menu, animator);
	}

	public static void animate(EmployeeMenuItem eployeeMenuItem, boolean show) {
		if (hashEmp.containsKey(eployeeMenuItem) && hashEmp.get(eployeeMenuItem).isRunning()) {
			hashEmp.get(eployeeMenuItem).stop();
		}
		eployeeMenuItem.setMenuShow(show);
		Animator animator = new Animator(400, new Animator.TimingTarget() {
			@Override
			public void timingEvent(float f) {
				if (show) {
					eployeeMenuItem.setAnimate(f);
				} else {
					eployeeMenuItem.setAnimate(1f - f);
				}
				eployeeMenuItem.revalidate();
			}

			@Override
			public void end() {
				hashEmp.remove(eployeeMenuItem);
			}
		});
		animator.setResolution(1);
		animator.setInterpolator((float f) -> (float) (1 - Math.pow(1 - f, 3)));
		animator.start();
		hashEmp.put(eployeeMenuItem, animator);
	}
}
