package com.talkka.server.review.dao;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.talkka.server.bus.dao.BusRouteEntity;
import com.talkka.server.bus.dao.BusRouteStationEntity;
import com.talkka.server.common.enums.TimeSlot;
import com.talkka.server.review.util.RatingDbConverter;
import com.talkka.server.review.util.ReviewContentDbConverter;
import com.talkka.server.review.vo.Rating;
import com.talkka.server.review.vo.ReviewContent;
import com.talkka.server.user.dao.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "bus_review")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class BusReviewEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bus_review_id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity writer;

	@ManyToOne
	@JoinColumn(name = "bus_route_station_id")
	private BusRouteStationEntity station;

	@ManyToOne
	@JoinColumn(name = "route_id")
	private BusRouteEntity route;

	@Column(name = "content", length = 400)
	@Convert(converter = ReviewContentDbConverter.class)
	private ReviewContent content;

	@Column(name = "time_slot", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private TimeSlot timeSlot;

	@Column(name = "rating", nullable = false)
	@Convert(converter = RatingDbConverter.class)
	private Rating rating;

	@CreatedDate
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BusReviewEntity that = (BusReviewEntity)o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	public void updateReview(ReviewContent content, TimeSlot timeSlot, Rating rating) {
		this.content = content;
		this.timeSlot = timeSlot;
		this.rating = rating;
	}
}
