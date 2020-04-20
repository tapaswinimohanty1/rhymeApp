/**
 * 
 */
package com.sc.dto;

import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author tapaswini
 *
 */
public class Statistic {
	@JsonProperty("hit_count")
	private AtomicLong hit_count;
	public Statistic() {
	
	}

	
	public Statistic(AtomicLong hit_count) {
		super();
		this.hit_count = hit_count;
	}

	public AtomicLong getHit_count() {
		return hit_count;
	}

	public void setHit_count(AtomicLong hit_count) {
		this.hit_count = hit_count;
	}

	
}
