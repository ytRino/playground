package net.nessness.playground.data.repository

import net.nessness.playground.data.api.entity.TimelineItem
import net.nessness.playground.data.api.entity.TimelineMaster
import net.nessness.playground.data.db.entity.TimelineItemEntity
import net.nessness.playground.data.db.entity.TimelineMasterEntity

internal fun TimelineMaster.toDbEntity() = TimelineMasterEntity(
    name = name,
    data = data
)

internal fun TimelineItem.toDbEntity() = TimelineItemEntity(
    id = id,
    name = name,
    status = status,
    numLikes = numLikes,
    numComments = numComments,
    price = price,
    photo = photo
)

internal fun TimelineMasterEntity.toApiEntity() = TimelineMaster(
    name = name,
    data = data
)

internal fun TimelineItemEntity.toApiEntity() = TimelineItem(
    id = id,
    name = name,
    status = status,
    numLikes = numLikes,
    numComments = numComments,
    price = price,
    photo = photo
)
