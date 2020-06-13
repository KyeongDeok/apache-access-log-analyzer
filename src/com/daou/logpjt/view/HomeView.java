package com.daou.logpjt.view;

import com.daou.logpjt.model.LogDataModel;

/*
 * view�� ����ϴ� Ŭ����
 * command Ŭ������ ���� ���� �޾� ����ȴ�.
 * view �޼ҵ� ���� �ٲ�� ��
 */
public class HomeView extends AbstractView {
    public LogDataModel show () {
		System.out.println("");
		System.out.println("���ϴ� ������ �����ϼ���. (1-6)");
		System.out.println("���� 1. Ư�� �ð����� ����ð��� ���� �ɸ� Request API ���� (���� �ɸ��ٴ� ������ �ٲ� �� �־�� ��. ��)1���̻�, 2���̻� ....)");
		System.out.println("���� 2. Ư�� �ð����� HTTP �����ڵ�(200, 500, 304 ��)�� METHOD(POST, GET, PUT, DELETE) �������� ���� ���ҽ�(js, image, css)�� �ƴ� Request API �˻� (������ ȣ��� API �Ǽ��� ���� ������������ ����)");
		System.out.println("���� 3. �ð��뺰 ��û Ƚ�� ī��Ʈ");
		System.out.println("���� 4. �ð��뺰 HTTP �����ڵ� ���� (1�ð� ����)");
		System.out.println("���� 5. �ð��뺰 Request �������� Client-Agent ������ �����Ͽ� � ������(����̽�)���� ���� �ߴ��� ����");
		System.out.println("���� 6. ����...");
		return null;
    }
	public static class InnerHomeView {
        private static final HomeView instance = new HomeView();
    }
    public static HomeView getInstance() {
        return InnerHomeView.instance;
    }
}
