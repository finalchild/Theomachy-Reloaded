package com.naver.cafe.craftproducer.heptagram.theomachy.Utility;

import java.util.Random;

import com.naver.cafe.craftproducer.heptagram.theomachy.Theomachy;
import com.naver.cafe.craftproducer.heptagram.theomachy.DB.AbilityData;

public class RandomNumberConstuctor
{
	public static int[] nonDuplicate()
	{
		Random random=new Random();
		int[] rn=new int[AbilityData.ABILITY_NUMBER];
		for(int i=0; i<AbilityData.GOD_ABILITY_NUMBER; i++)//초기화
			rn[i]=i+1;
		int a=101;
		for(int i=AbilityData.GOD_ABILITY_NUMBER; i<AbilityData.ABILITY_NUMBER; i++)
			rn[i]=a++;
		for(int i=0; i<rn.length; i++)//섞기
		{
			int r=random.nextInt(rn.length-1);
			int temp=rn[i];
			rn[i]=rn[r];
			rn[r]=temp;
		}
		StringBuilder sb = new StringBuilder();
		for (int num : rn)
			sb.append(num).append(" ");
		Theomachy.log.info(String.valueOf(sb));
		return rn;
	}
}
